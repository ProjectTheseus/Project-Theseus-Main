/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx.g3d;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import edu.virginia.cs.sgd.libgdx.camera.MyCameraInputController;
import edu.virginia.cs.sgd.libgdx.game.Game;
import edu.virginia.cs.sgd.libgdx.path.Path;
import edu.virginia.cs.sgd.libgdx.screen.AbstractScreen;
import edu.virginia.cs.sgd.libgdx.util.SingletonAssetManager;

/**
 * @author Dylan
 * 
 *         MazeBuilder class - creates 3D environment and builds Maze
 */
public class MazeBuilder extends AbstractScreen {
	private Maze m; // holds maze object
	private Path shortPath; // holds path object
	private Game game; // holds game object
	private int x; // holds given x dimension
	private int y; // holds given y dimension
	public final static int spacing = 8; // spacing between walls
	public final static int wallDepth = 2; // depth of walls
	public final static int spacingDiff = (spacing / 2); // spacing from center
															// of node to wall

	// libgdx objects used for 3D games
	public Environment environment;
	public PointLight light;
	public PerspectiveCamera cam;
	public MyCameraInputController camController;
	public ModelBatch modelBatch;
	public ArrayList<Model> models;
	public ArrayList<ModelInstance> instances;
	public SpriteBatch spriteBatch;
	public BitmapFont font;
	public SingletonAssetManager sam;

	/**
	 * Creates app
	 */
	@Override
	public void show() {
		super.show();

		x = Game.getLevel() + 2;
		y = Game.getLevel() + 2;

		// generates Maze with correct dimensions
		m = new Maze(x, y);
		// generates Path given the generated Maze
		shortPath = new Path(m, m.getStart(), m.getEnd());

		sam = SingletonAssetManager.getInstance();
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f,
				0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f,
				-0.8f, -0.2f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, 1f, 0.8f,
				0.2f));
		// light = new PointLight();
		// environment.add(light);

		modelBatch = new ModelBatch();

		cam = new PerspectiveCamera(90, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.rotate(90, 1, 0, 0);

		// sets camera location based on Maze start node
		switch (m.getStartSide()) {

		case 0:
			cam.position.set((m.getStart().getX() * spacing), (m.getStart()
					.getY() * spacing) - spacing * 2,
					(m.getStart().getZ() * spacing));
			cam.lookAt((m.getStart().getX() * spacing),
					((m.getStart().getY() * spacing)) * -1, (m.getStart()
							.getZ() * spacing));
			break;

		case 1:
			cam.position.set((m.getStart().getX() * spacing) + spacing * 2, (m
					.getStart().getY() * spacing),
					(m.getStart().getZ() * spacing));
			cam.lookAt(((m.getStart().getX() * spacing)) * -1, (m.getStart()
					.getY() * spacing), (m.getStart().getZ() * spacing));
			break;

		case 2:
			cam.position.set((m.getStart().getX() * spacing), (m.getStart()
					.getY() * spacing) + spacing * 2,
					(m.getStart().getZ() * spacing));
			cam.lookAt((m.getStart().getX() * spacing),
					((m.getStart().getY() * spacing)) * -1, (m.getStart()
							.getZ() * spacing));
			break;

		case 3:
			cam.position.set((m.getStart().getX() * spacing) - spacing * 2, (m
					.getStart().getY() * spacing),
					(m.getStart().getZ() * spacing));
			cam.lookAt(((m.getStart().getX() * spacing)) * -1, (m.getStart()
					.getY() * spacing), (m.getStart().getZ() * spacing));
			break;

		default:
			break;

		}
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		ModelBuilder modelBuilder = new ModelBuilder();
		models = new ArrayList<Model>();
		instances = new ArrayList<ModelInstance>();

		Material wallM = new Material();
		wallM.set(TextureAttribute.createDiffuse((Texture) sam.get("Wall")),
				ColorAttribute.createDiffuse(Color.GRAY));
		Material minM = new Material();
		minM.set(TextureAttribute.createDiffuse((Texture) sam.get("Minotaur")));

		// builds walls and fills 3D space with Maze
		int count = -1;
		for (int y = 0; y < (m.getY_Dim() * spacing); y += spacing) {
			for (int x = 0; x < (m.getX_Dim() * spacing); x += spacing) {
				for (int z = 0; z < (m.getZ_Dim() * spacing); z += spacing) {
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.getWalls()[0]) {
						models.add(modelBuilder.createBox(spacing, wallDepth,
								spacing, wallM, Usage.Position | Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), x, y
								- spacingDiff, z));
					}
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.getWalls()[1]) {
						models.add(modelBuilder.createBox(wallDepth, spacing,
								spacing, wallM, Usage.Position | Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), x
								+ spacingDiff, y, z));
					}
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.getWalls()[2]) {
						models.add(modelBuilder.createBox(spacing, wallDepth,
								spacing, wallM, Usage.Position | Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), x, y
								+ spacingDiff, z));
					}
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.getWalls()[3]) {
						models.add(modelBuilder.createBox(wallDepth, spacing,
								spacing, wallM, Usage.Position | Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), x
								- spacingDiff, y, z));
					}
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.getWalls()[4]) {
						models.add(modelBuilder.createBox(spacing, spacing,
								wallDepth / 2, wallM, Usage.Position
										| Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), x,
								y, z - spacingDiff));
					}
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.getWalls()[5]) {
						models.add(modelBuilder.createBox(spacing, spacing,
								wallDepth / 2, wallM, Usage.Position
										| Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), x,
								y, z + spacingDiff));
					}
					if (m.getGrid()[x / spacing][y / spacing][z / spacing]
							.equals(m.getEnd())) {
						models.add(modelBuilder.createBox(3f, 3f, 6f, minM,
								Usage.Position | Usage.Normal
										| Usage.TextureCoordinates));
						count++;
						instances.add(new ModelInstance(models.get(count), (m
								.getEnd().getX() * spacing),
								(m.getEnd().getY() * spacing), z));
					}
				}
			}
		}

		camController = new MyCameraInputController(cam, m, this);
		Gdx.input.setInputProcessor(camController);

		game = new Game(camController, this);
		game.getCreatures().get(0).setBox(instances.get(count));
	}

	/**
	 * Renders 3D objects
	 */
	@Override
	public void render(float delta) {
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// environment.pointLights.get(0).set(Color.WHITE,
		// (float) camController.getCurrent().getX() * spacing,
		// (float) camController.getCurrent().getY() * spacing,
		// (float) camController.getCurrent().getZ() * spacing, 15f);

		modelBatch.begin(cam);
		for (ModelInstance instance : instances) {
			modelBatch.render(instance, environment);
		}
		modelBatch.end();

		// Draws text on screen
		spriteBatch.begin();
		if (m.getAtEnd()) {
			CharSequence str = "You Won! You Finished the Maze in "
					+ String.valueOf(m.getMoveCount()) + " moves!";
			font.draw(spriteBatch, str, 160, 240);
			str = "You took "
					+ String.valueOf(m.getMoveCount()
							- shortPath.shortPathLen()) + " extra moves.";
			font.draw(spriteBatch, str, 220, 220);
		} else {
			CharSequence str = "Points: All of them";
			// + String.valueOf(m.getMoveCount());
			font.draw(spriteBatch, str, 20, 20);
			// str = "Moves Needed: " +
			// String.valueOf(shortPath.shortPathLen());
			// font.draw(spriteBatch, str, 500, 20);
		}
		spriteBatch.end();
	}

	/**
	 * Disposes of MazeBuilder
	 */
	@Override
	public void dispose() {
		// stops threads in MyCameraInputController
		// camController.stopThreads();
		// t.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public Game getGame() {
		return game;
	}

	public Maze getMaze() {
		return m;
	}

}
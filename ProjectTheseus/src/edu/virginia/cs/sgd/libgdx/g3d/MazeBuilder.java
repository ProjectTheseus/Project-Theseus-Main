/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
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
import edu.virginia.cs.sgd.libgdx.entities.Creature;
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
	private ArrayList<String> combatText;
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
	ModelBuilder modelBuilder;

	/**
	 * Creates app
	 */
	@Override
	public void show() {
		super.show();
		
		combatText = new ArrayList<String>();

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
			cam.position.set((m.getStart().getX() * spacing),
					(m.getStart().getY()) * spacing,
					(m.getStart().getZ() * spacing));
			cam.lookAt((m.getStart().getX() * spacing),
					((m.getStart().getY() - 1 * spacing)) * -1, (m.getStart()
							.getZ() * spacing));
			break;

		case 1:
			cam.position.set((m.getStart().getX()) * spacing, (m.getStart()
					.getY() * spacing), (m.getStart().getZ() * spacing));
			cam.lookAt(((m.getStart().getX() * spacing)) * -1, (m.getStart()
					.getY() * spacing), (m.getStart().getZ() * spacing));
			break;

		case 2:
			cam.position.set((m.getStart().getX() * spacing),
					(m.getStart().getY()) * spacing,
					(m.getStart().getZ() * spacing));
			cam.lookAt((m.getStart().getX() * spacing),
					((m.getStart().getY() * spacing)) * -1, (m.getStart()
							.getZ() * spacing));
			break;

		case 3:
			cam.position.set((m.getStart().getX()) * spacing, (m.getStart()
					.getY() * spacing), (m.getStart().getZ() * spacing));
			cam.lookAt(((m.getStart().getX() - 1 * spacing)) * -1, (m
					.getStart().getY() * spacing),
					(m.getStart().getZ() * spacing));
			break;

		default:
			break;

		}
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		modelBuilder = new ModelBuilder();
		models = new ArrayList<Model>();
		instances = new ArrayList<ModelInstance>();

		Material wallM = new Material();
		wallM.set(TextureAttribute.createDiffuse((Texture) sam.get("Wall")),
				ColorAttribute.createDiffuse(Color.GRAY));

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
				}
			}
		}

		camController = new MyCameraInputController(cam, m, this);
		Gdx.input.setInputProcessor(camController);

		game = new Game(camController, this);

		for (Creature creature : game.getCreatures()) {
			spawnCreature(creature);
		}
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
		if (game.getPlayer().isDead()) {
			CharSequence str = "You Died!";
			font.draw(
					spriteBatch,
					str,
					this.cam.viewportWidth / 2 - font.getSpaceWidth()
							* str.length() / 2, this.cam.viewportHeight / 2);
			font.setScale(4);
		} else {
			CharSequence str = "Health: " + game.getPlayer().getCurrentHealth();
			font.draw(spriteBatch, str, 20, 20);
			drawCombatText(font, spriteBatch);
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

	public void spawnCreature(Creature creature) {
		Model m = modelBuilder.createBox(3f, 3f, 6f, creature.getMaterial(),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		models.add(m);
		ModelInstance model = new ModelInstance(m, creature.getLocation()
				.getX() * spacing, creature.getLocation().getY() * spacing, 0);
		instances.add(model);
		creature.setBox(model);
	}

	public void addCombatText(String str) {
		combatText.add(str);
	}

	public void drawCombatText(BitmapFont font, SpriteBatch spriteBatch) {
		if (combatText.size() > 5) {
			for (int i = combatText.size() - 6; i < combatText.size(); i++) {
				int locY = 20 + 20*-1*(i - combatText.size());
				if (i - combatText.size() < -3) {
					locY += 20;
				}
				font.draw(spriteBatch, combatText.get(i), this.VIEWPORT_WIDTH - 100, locY);
			}
		}
	}
}
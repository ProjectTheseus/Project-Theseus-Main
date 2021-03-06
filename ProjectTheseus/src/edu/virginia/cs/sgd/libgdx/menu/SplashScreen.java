package edu.virginia.cs.sgd.libgdx.menu;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;
import edu.virginia.cs.sgd.libgdx.game.Game;
import edu.virginia.cs.sgd.libgdx.screen.AbstractScreen;
import edu.virginia.cs.sgd.libgdx.util.SingletonAssetManager;

public class SplashScreen extends AbstractScreen {
	private Image splashImage;

	public SplashScreen() {
		super();
	}

	@Override
	public void show() {

		super.show();

		// load the splash image and create the texture region
		Texture splashTexture = SingletonAssetManager.getInstance().get(
				"SplashScreen");
		TextureRegion tr = new TextureRegion(splashTexture);
		Drawable splashDrawable = new TextureRegionDrawable(tr);

		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);

		// this is needed for the fade-in effect to work correctly; we're just
		// making the image completely transparent
		splashImage.getColor().a = 0f;

		// configure the fade-in/out effect on the splash image

		splashImage.addAction(sequence(fadeIn(0.75f)));

		// and finally we add the actor to the stage
		stage.addActor(splashImage);

	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		if (screenX > 400 && screenX < 624 && screenY > 550 && screenY < 600) {
			splashImage.addAction(sequence(fadeOut(0.75f), new Action() {

				@Override
				public boolean act(float delta) { // the last action will
													// move to the next
													// screen
					changeScreen(MazeBuilder.class);
					return true;
				}
			}));

			stage.addActor(splashImage);
		}
	}
}
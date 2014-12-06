package edu.virginia.cs.sgd.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.virginia.cs.sgd.libgdx.input.Input;
import edu.virginia.cs.sgd.libgdx.menu.SplashScreen;
import edu.virginia.cs.sgd.libgdx.screen.AbstractScreen;
import edu.virginia.cs.sgd.libgdx.util.SingletonAssetManager;

public class Program extends Game implements ApplicationListener {

	public static final String LOG = Program.class.getName();

	private Input input;
	private AbstractScreen screen;

	@Override
	public void create() {
		input = new Input();
		Texture.setEnforcePotImages(false);

		loadImmediateAssets();
		loadAssets();
		createScreen(SplashScreen.class);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		Class<? extends AbstractScreen> newScreen = screen.checkScreenChange();
		if (newScreen != null) {
			createScreen(newScreen);
		}
		super.render();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	private void createScreen(Class<? extends AbstractScreen> type) {
		screen = null;
		try {
			screen = type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		}

		input.setListener(screen);
		setScreen(screen);
	}

	private void loadImmediateAssets() {

		SingletonAssetManager m = SingletonAssetManager.getInstance();
		// m.load("LibGDX", "data/libgdx.png", Texture.class);
		// m.load("sample", "data/samplesprite.png", Texture.class);

		m.load("Wall", "resources/Rough Block Wall.jpg", Texture.class);
		m.load("Minotaur", "resources/Minotaur.jpg", Texture.class);
		m.load("Orc1", "resources/Orc1.jpg", Texture.class);
		m.load("Orc2", "resources/Orc2.jpg", Texture.class);
		m.load("Orc3", "resources/Orc3.jpg", Texture.class);
		m.load("SplashScreen", "resources/logo.jpg", Texture.class);
		m.load("WhiteOut", "resources/white.jpg", Texture.class);
		m.load("uiskin", "resources/skins/uiskin.json", Skin.class);
		m.load("icons", "resources/icons/icons.atlas", TextureAtlas.class);

		m.finishLoading();

	}

	private void loadAssets() {
		// SingletonAssetManager m = SingletonAssetManager.getInstance();

	}
}

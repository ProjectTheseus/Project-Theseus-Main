/**
 * Name: Dylan Hellems
 * Computing ID: djh5sc
 */
package edu.virginia.cs.sgd.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.virginia.cs.sgd.libgdx.g3d.MazeBuilder;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Project Theseus";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 720;
		cfg.resizable = false;
		
		new LwjglApplication(new MazeBuilder(), cfg);
	}
}

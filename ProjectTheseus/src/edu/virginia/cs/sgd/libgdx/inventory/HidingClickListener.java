package edu.virginia.cs.sgd.libgdx.inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HidingClickListener extends ClickListener {

	private Actor actor;
	private InventoryScreen invScreen;

	public HidingClickListener(Actor actor, InventoryScreen invScreen) {
		this.actor = actor;
		this.invScreen = invScreen;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		invScreen.restoreScreen();
	}

}

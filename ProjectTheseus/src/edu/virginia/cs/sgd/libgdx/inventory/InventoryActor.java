package edu.virginia.cs.sgd.libgdx.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class InventoryActor extends Window {

	public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop,
			Skin skin) {
		super("Inventory", skin);

		TextButton closeButton = new TextButton("X", skin);
		closeButton.addListener(new HidingClickListener(this));
		getButtonTable().add(closeButton).height(getPadTop());

		setPosition(400, 400);
		defaults().space(8);
		row().fill().expandX();

		int i = 0;
		for (Slot slot : inventory.getSlots()) {
			SlotActor slotActor = new SlotActor(skin, slot);
			add(slotActor);

			dragAndDrop.addSource(new SlotSource(slotActor));
			dragAndDrop.addTarget(new SlotTarget(slotActor));

			i++;
			if (i % 5 == 0) {
				row();
			}
		}

		pack();
		setVisible(false);
	}

}

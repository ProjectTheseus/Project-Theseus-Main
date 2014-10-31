package edu.virginia.cs.sgd.libgdx.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import edu.virginia.cs.sgd.libgdx.util.SingletonAssetManager;

public class SlotSource extends Source {

	private Slot sourceSlot;
	private SingletonAssetManager sam = SingletonAssetManager.getInstance();

	public SlotSource(SlotActor actor) {
		super(actor);
		this.sourceSlot = actor.getSlot();
	}

	@Override
	public Payload dragStart(InputEvent event, float x, float y, int pointe) {
		if (sourceSlot.getAmount() == 0) {
			return null;
		}

		Payload payload = new Payload();
		Slot payloadSlot = new Slot(sourceSlot.getItem(),
				sourceSlot.getAmount());
		sourceSlot.take(sourceSlot.getAmount());
		payload.setObject(payloadSlot);

		TextureAtlas icons = sam.get("Icons");
		TextureRegion icon = icons.findRegion(payloadSlot.getItem()
				.getTextureRegion());

		Actor dragActor = new Image(icon);
		payload.setDragActor(dragActor);

		Actor validDragActor = new Image(icon);

		payload.setValidDragActor(validDragActor);

		Actor invalidDragActor = new Image(icon);

		payload.setInvalidDragActor(invalidDragActor);

		return payload;
	}

	public void dragStop(InputEvent event, float x, float y, int pointer,
			Payload payload, Target target) {
		Slot payloadSlot = (Slot) payload.getObject();

		if (target != null) {
			Slot targetSlot = ((SlotActor) target.getActor()).getSlot();

			if (targetSlot.getItem() == payloadSlot.getItem()
					|| targetSlot.getItem() == null) {
				targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
			} else {
				Item targetType = targetSlot.getItem();
				int targetAmount = targetSlot.getAmount();
				targetSlot.take(targetAmount);

				targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
				sourceSlot.add(targetType, targetAmount);
			}
		} else {
			sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
		}
	}
}

package edu.virginia.cs.sgd.libgdx.inventory;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<Slot> slots;
	private int numSlots = 0;

	public Inventory() {
		slots = new ArrayList<Slot>(numSlots);
		for (int i = 0; i < numSlots; i++) {
			slots.add(new Slot(null, 0));
		}
	}

	public Inventory(int numSlots) {
		this.numSlots = numSlots;
		slots = new ArrayList<Slot>(numSlots);
		for (int i = 0; i < numSlots; i++) {
			slots.add(new Slot(null, 0));
		}
	}

	public int checkInventory(Item item) {
		int amount = 0;

		for (Slot slot : slots) {
			if (slot.getItem().equals(item)) {
				amount += slot.getAmount();
			}
		}

		return amount;
	}

	public boolean store(Item item, int amount) {
		Slot itemSlot = firstSlotWithItem(item);
		if (itemSlot != null) {
			itemSlot.add(item, amount);
			return true;
		} else {
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(item, amount);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Slot> getSlots() {
		return slots;
	}

	private Slot firstSlotWithItem(Item item) {
		for (Slot slot : slots) {
			if (slot.getItem().equals(item)) {
				return slot;
			}
		}
		return null;
	}

}

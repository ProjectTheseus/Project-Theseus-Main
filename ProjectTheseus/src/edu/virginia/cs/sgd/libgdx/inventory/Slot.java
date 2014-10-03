package edu.virginia.cs.sgd.libgdx.inventory;

import java.util.ArrayList;

public class Slot {

	private Item item;
	private int amount;
	private ArrayList<SlotListener> slotListeners = new ArrayList<SlotListener>();
	
	public Slot(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}
	
	public boolean isEmpty() {
		return item == null || amount <= 0;
	}
	
	public boolean add(Item item, int amount) {
		if (this.item == item || this.item == null) {
			this.item = item;
			this.amount += amount;
			notifyListeners();
			return true;
		}
		return false;
	}
	
	public boolean take(int amount) {
		if (this.amount >= amount) {
			this.amount -= amount;
			if (this.amount == 0) {
				item = null;
			}
			notifyListeners();
			return true;
		}
		return false;
	}
	
	public void addListener(SlotListener slotListener) {
		slotListeners.add(slotListener);
	}
	
	public void removeListener(SlotListener slotListener) {
		slotListeners.remove(slotListener);
	}
	
	private void notifyListeners() {
		for (SlotListener slotListener : slotListeners) {
			slotListener.hasChanged(this);
		}
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getAmount() {
		return amount;
	}
}

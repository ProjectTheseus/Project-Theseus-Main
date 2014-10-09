package edu.virginia.cs.sgd.libgdx.inventory;

public class Item {

	String name;
	String type;
	
	public Item(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public boolean equals(Item item) {
		if (this.name.equals(item.getName())) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

}

package edu.virginia.cs.sgd.libgdx.inventory;

public class Item {

	private String name, type, textureRegion;

	public Item(String name, String type, String textureRegion) {
		this.name = name;
		this.type = type;
		this.textureRegion = textureRegion;
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

	public String getTextureRegion() {
		return textureRegion;
	}

}

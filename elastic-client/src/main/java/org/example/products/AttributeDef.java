package org.example.products;


public class AttributeDef {
	

	@Override
	public String toString() {
		return "AttributeDef [id=" + id + ", displayName=" + displayName + ", isBoolean=" + isBoolean + "]";
	}
	private String id;
	private String displayName;
	private boolean isBoolean;
	public AttributeDef(String id, String displayName, boolean isBoolean) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.isBoolean = isBoolean;
	}
	public String getId() {		
		return this.id;
	}
	
	public boolean isBoolean() {		
		return this.isBoolean;
	}
	
	
	public String getDisplayName() {		
		return this.displayName;
	}
	 
	 
}

package org.example.products;


public class Attribute {
	

	public String getName() {
		return name;
	}



	public String getValue() {
		return value;
	}

	private String name;
	private String value;
	
	
	public Attribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;		
	}
	 
	

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", value=" + value + "]";
	}
	
	public String toJson() {
		return "{ \"name\": \"" + name + "\", \"value\": \"" + value + "\"}";
	}

}

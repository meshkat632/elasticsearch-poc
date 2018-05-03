package org.example.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

import org.example.groups.CategoryMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Document {
	
    private Gson gson;
	
	private String categoryids;
	private String collection;
	private String id;
	private List<Attribute> attributes= new ArrayList<Attribute>();
	
	public Document(String categoryids, String collection, String id) {
		super();
		this.categoryids = categoryids;
		this.collection = collection;
		this.id = id;	
		this.gson = new GsonBuilder().setPrettyPrinting().create();
	}
	
	public Document(XMLStreamReader reader) {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		for(int index = 0;  index < reader.getAttributeCount(); index++) {
			QName attributeName = reader.getAttributeName(index);
			if(attributeName.getLocalPart().equals("categoryids")) {
				this.categoryids = reader.getAttributeValue(index);
			}else if(attributeName.getLocalPart().equals("collection")) {
				this.collection = reader.getAttributeValue(index);
			}else if(attributeName.getLocalPart().equals("id")) {				
				this.id = reader.getAttributeValue(index).replaceAll("SaturnDEdece", "");
			}else {
				System.err.println(" unknown attribute for document:"+attributeName.getLocalPart());
			}
		}		
	}
	

	@Override
	public String toString() {
		return "Document [categoryids=" + categoryids + ", collection=" + collection + ", id=" + id + "]";
	}
	
	public String toJson() {
		 
		return gson.toJson(this);		
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
		
	}

	public String getId() {		
		return this.id;
	}

	public String toIndexable(CategoryMap categoryMappings) {
		
		Map<String, Object> map = new HashMap<>();		
		this.attributes.stream().forEach(attribute-> {
			map.put(attribute.getName(),attribute.getValue());
		});
		map.put("categoryids", this.categoryids);
		map.put("collection", this.collection);
		map.put("id", this.id);
		
		
		JsonArray categorys = new JsonArray();
		
		Arrays.asList(this.categoryids.split(" ")).stream().forEach(categoryid -> {
			categoryMappings.getCategoryWithId(categoryid).ifPresent(category ->{
				System.out.println(category);
				JsonObject categoryJson = new JsonObject();
				// add a property calle title to the albums object
				categoryJson.addProperty("categoryId", category.getId());
				categoryJson.addProperty("categoryName", category.getCategory());
				categorys.add(categoryJson);			
			});
		});
		
		map.put("categories", categorys);		
		return gson.toJson(map);		
	}

	public boolean hasAttribute(String categoryid) {
		return this.categoryids.contains(categoryid);
	}

	 

}

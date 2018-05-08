package org.example.groups;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "category")
public class Category {

	
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + " childCategories:"+childCategories+"]";
	}
	

	private String category;
	private Category parent;
	

	public String getCategory() {
		return category;
	}

	@XmlJavaTypeAdapter(value=Adapter.class) 
	public void setCategory(String category) {
		this.category = category;
	}

	private String id;
	
	private List<Category> childCategories = new ArrayList<>();
	

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	private static class Adapter extends XmlAdapter<Object, Object> {

		@Override
		public Object marshal(Object v) throws Exception {
			System.out.println("marshal :"+ v.getClass());
			return "<![CDATA[" + v + "]]>";
		}

		@Override
		public Object unmarshal(Object v) throws Exception {
			System.out.println("unmarshal :"+v.getClass());
			return v;
		}
	}

	public void addChild(Category childCategory) {		
		childCategories.add(childCategory);
		childCategory.setParent(this);
		
	}

	private void setParent(Category parent) {
		this.parent = parent;
		
	}
	public Category getParent() {
		return this.parent;		
	}

	public List<Category> getChildren() {		
		return childCategories;
	}
	
	private void getPathToRoot(Category category, StringBuilder pathToRoot) {
		if(category == null) return;
		pathToRoot.append("|"+category.getCategory());
		if(this.parent != null) 
			getPathToRoot(category.getParent(), pathToRoot);
		
	}
	private void getPathIdToRoot(Category category, StringBuilder pathToRoot) {
		if(category == null) return;
		pathToRoot.append("|"+category.getId());
		if(this.parent != null) 
			getPathIdToRoot(category.getParent(), pathToRoot);
		
	}

	public String getPathToRoot() {
		StringBuilder ret = new StringBuilder();
		getPathToRoot(this, ret);
		return ret.toString();
	}

	public String getPathIdToRoot() {
		StringBuilder ret = new StringBuilder();
		getPathIdToRoot(this, ret);
		return ret.toString();
	}

}

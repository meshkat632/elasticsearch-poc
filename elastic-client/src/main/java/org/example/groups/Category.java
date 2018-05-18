package org.example.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.gson.Gson;

@XmlRootElement(name = "category")
public class Category {

	
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + " childCategories:"+childCategories+"]";
	}
	
	
	public String toJson() {		
		return "{ \"id\":\""+ id + "\", \"category\": \""+ category + "\", \"childCategoriesCount\": "+childCategories.size()+"}";
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
	
	private void getRootCategoris(Category category, List<String> rootCatPath) {
		if(category == null) return;
		if(!rootCatPath.contains(category.getId()+":"+category.getCategory()))
			rootCatPath.add(category.getId()+":"+category.getCategory());		
		if(this.parent != null) 
			getRootCategoris(category.getParent(), rootCatPath);
	}
	
	private void getRootCategoris2(Category category, List<String> rootCatPath) {
		if(category == null) return;
		if(!rootCatPath.contains(category.getCategory()))
			rootCatPath.add(category.getCategory());		
		if(this.parent != null) 
			getRootCategoris2(category.getParent(), rootCatPath);
	}

	public List<String> getRootCategoris() {
		List<String> ret = new ArrayList<>();
		getRootCategoris(this, ret);
		return ret;
	}
	

	public List<String> getRootCategoris2() {
		List<String> ret = new ArrayList<>();
		List<String> result = new ArrayList<>();
		getRootCategoris2(this, ret);
		while(!ret.isEmpty()) {			
			//System.out.println(ret+" "+convertToPath(ret));
			result.add(convertToPath(ret));
			ret.remove(0);
		}
		/*
		ret.forEach(item ->{
			ret.stream().filter(item2 -> )
		});
		*/
		return result;
	}


	private String convertToPath(List<String> paths) {
		StringBuilder ret = new StringBuilder(); 
		paths.forEach(item ->{
			ret.append("|"+item);
		});
		return ret.toString();
	}


}

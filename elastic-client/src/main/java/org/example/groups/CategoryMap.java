package org.example.groups;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CategoryMap {
	
	private Category rootCategory;
	private Map<String, Category> categoryMap;
	

	public CategoryMap(Category rootCcategory, Map<String, Category> categoryMap) {
		this.rootCategory  = rootCcategory;
		this.categoryMap = categoryMap;
	}

	public static void main(String[] args) {
		
		final String xmlFile = "src/main/resources/groups.xml";
		
		Optional<CategoryMap> categoryMap = CategoryMap.buildFromFile(xmlFile);
		categoryMap.ifPresent(item ->{
			item.print();
			/*
			System.out.println(item);
			
			
			String json = item.print();
			System.out.println(json);
			/*
			Optional<Category> found = item.getCategoryWithId("SaturnDEdec474973");
			if(found.isPresent()) {
				Category category = found.get();				
				System.out.println(category.getCategory()+" "+category.getId()+" path:"+ category.getPathToRoot());	
			}
			*/			
		});
		
		System.out.println("test CategoryMap");
	}
	
	private void print() {
		JsonObject categoryJson = printCategoryTree(this.rootCategory, 0);
		System.out.println(categoryJson.toString());
		writeToFile(categoryJson.toString(), "groups");
	}
	private JsonObject printCategoryTree(Category category, int space) {
		JsonObject categoryJson = new JsonObject();
		categoryJson.addProperty("id", category.getId());
		categoryJson.addProperty("label", category.getCategory());
		categoryJson.addProperty("categoryPath", category.getPathToRoot());
		if(category.getChildren().size()> 0) {
			JsonArray categoris = new JsonArray();		
			category.getChildren().stream().forEach(child ->{
				categoris.add(printCategoryTree(child, space+1));			
			});		
			categoryJson.add("children", categoris);
		}
		return categoryJson;
	}

	public static void writeToFile(String content, String fileName) {

    	/*
    	C:\Users\meshkatul\Box Sync\rise-search\mms-elasticsearch-poc\guttenberg_search\smart-tvs
    	*/
    	
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/meshkatul/Box Sync/rise-search/mms-elasticsearch-poc/product_search/public/data/"+fileName+".json"))) {
		//try (BufferedWriter bw = new BufferedWriter(new FileWriter("/data/"+fileName+".json"))) {
			bw.write(content);			
			// no need to close it.
			//bw.close();
			System.out.println("Done");
		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	private String getSpace(int space) {
		StringBuilder ret = new StringBuilder();
		for(int i= 0; i< space; i++) {
			ret.append(" ");
		}
		return ret.toString();
	}

	private static Category getCategory(Element eElement) {
		String categoryId = eElement.getAttribute("id");
		String categoryName = eElement.getFirstChild().getNodeValue();
		Category ret = new Category();
		ret.setId(categoryId);
		ret.setCategory(categoryName);

		NodeList nList = eElement.getChildNodes();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node childNode = nList.item(temp);			
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Category childCategory = getCategory((Element) childNode);
				ret.addChild(childCategory);
			} 
		}
		return ret;
	}
	
	private static Map<String, Category> buildCategoryMap(Category categoryTree) {
		Map<String, Category> categoryMap = new HashMap<>();
		_buildCategoryMap(categoryTree, categoryMap);
		return categoryMap;
	}
	
	private static void _buildCategoryMap(Category categoryTree, Map<String, Category> categoryMap) {
		categoryMap.put(categoryTree.getId(), categoryTree);
		for (Category child : categoryTree.getChildren()) {
			_buildCategoryMap(child, categoryMap);				
		}		
	}
	
	public Optional<Category> getCategoryWithId(String categoryId) {
		Category x = getCategoryWithId(rootCategory, categoryId);
		if(x == null) return Optional.empty();
		else return Optional.of(x);
		
	}

	private Category getCategoryWithId(Category categoryTree, String categoryId) {
		if(categoryTree.getId().equals(categoryId)) return categoryTree;
		else {
			for (Category child : categoryTree.getChildren()) {
				Category found = getCategoryWithId(child, categoryId);
				if(found!= null) return found;				
			}			
		}
		return null;
	}

	public static Optional<CategoryMap> buildFromFile(String xmlFile) {	
		
		try {
			
			File inputFile = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			Category category = getCategory(doc.getDocumentElement());			
			Map<String, Category> categoryMap = buildCategoryMap(category);
			/*
			categoryMap.values().stream().forEach(item -> {
				System.out.println(item);
			});
			*/
			return Optional.of(new CategoryMap(category, categoryMap));

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return Optional.empty();
		
	}

}

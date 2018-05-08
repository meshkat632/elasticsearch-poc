package org.example.groups;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CategoryMap {
	
	private Category rootCcategory;
	private Map<String, Category> categoryMap;
	

	public CategoryMap(Category rootCcategory, Map<String, Category> categoryMap) {
		this.rootCcategory  = rootCcategory;
		this.categoryMap = categoryMap;
	}

	public static void main(String[] args) {
		
		final String xmlFile = "src/main/resources/groups.xml";
		
		Optional<CategoryMap> categoryMap = CategoryMap.buildFromFile(xmlFile);
		categoryMap.ifPresent(item ->{
			Optional<Category> found = item.getCategoryWithId("SaturnDEdec474973");
			if(found.isPresent()) {
				Category category = found.get();				
				System.out.println(category.getCategory()+" "+category.getId()+" path:"+ category.getPathToRoot());	
			}			
		});
		
		System.out.println("test CategoryMap");
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
		Category x = getCategoryWithId(rootCcategory, categoryId);
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

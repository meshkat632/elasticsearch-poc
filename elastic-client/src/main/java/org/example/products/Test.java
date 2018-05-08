package org.example.products;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.groups.Category;
import org.example.groups.CategoryMap;

public class Test {

	public static FeatureMappings featureMappings;
	public static CategoryMap categoryMappings;
	
    public static void main(String[] args) throws IOException, XMLStreamException {
    	
    	featureMappings= new FeatureMappings();
    	final String xmlFile = "src/main/resources/groups.xml";
		
		Optional<CategoryMap> _categoryMap = CategoryMap.buildFromFile(xmlFile);
		if(! _categoryMap.isPresent()) {
			throw new IllegalArgumentException("could build category tree");			
		}	
		categoryMappings = _categoryMap.get();
    	
    	
    	
    	//InputStream xml = new FileInputStream("C:/meshkat-shared-folder/searchengine_poc_java/products.xml");
    	try (InputStream is = new FileInputStream("C:/meshkat-shared-folder/searchengine_poc_java/products.xml")) {
    	//try (InputStream is = Main.class.getResourceAsStream("/data/small_product_data.xml")) {
    	//try (InputStream is = Main.class.getResourceAsStream("/data/product_two.xml")) {
        //try (InputStream is = Main.class.getResourceAsStream("/data/product_one.xml")) {
            if (is == null)
                throw new IOException("Failed to open resource");

            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();            
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(is);
            

            readDocuments(reader, new OnDocumentReady() {
				
				@Override
				public void onDocumentReady(Document document) {
					Optional<Category> rootCat = categoryMappings.getCategoryWithId("SaturnDEdec633087");
					if(rootCat.isPresent()) {
						List<String> rootPath = document.isOfCategory(rootCat.get(), categoryMappings);
						if (rootPath.size()> 0) {
							System.out.println("rootPath:"+rootPath);
							writeToFile(document.toIndexable(categoryMappings), "tv-heimkino-zubehör-"+document.getId());
							/*
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							*/	
						}						
					}
					
					
					
					
					/*
					if(document.hasAttribute("SaturnDEdec474973")) {						
						writeToFile(document.toIndexable(categoryMappings), "smart-tv-"+document.getId());						
					}					
					*/
				}
			});                 
            
        } 
           	
        
    }    
    
    protected static String postToElasticSearch(String json, String id) throws ClientProtocolException, IOException {
    	String url = "http://localhost:9200/meshkat/products/"+id;

    	StringEntity requestEntity = new StringEntity(
    			json,
    		    ContentType.APPLICATION_JSON);
    	
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpPost post = new HttpPost(url);

    	// add header
    	post.setHeader("User-Agent", "java-test-client");
    	post.setEntity(requestEntity);
 

    	HttpResponse response = client.execute(post);
    	System.out.println("Response Code : " 
                    + response.getStatusLine().getStatusCode());

    	BufferedReader rd = new BufferedReader(
    	        new InputStreamReader(response.getEntity().getContent()));

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
    	
    	return result.toString();
		
	}

	public interface OnDocumentReady{
    	public void onDocumentReady(Document document);
    }
    
    private static void readDocuments(XMLStreamReader reader, OnDocumentReady onDocumentReady) throws XMLStreamException {    	
    	while (reader.hasNext()) {
            int eventType = reader.next();
            //System.out.println("readDocument2 eventType:"+eventType);
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    final String startElementName = reader.getLocalName();                         
                    if(startElementName.equals("document")) {                    	
                    	Document document = new Document(reader);                        	
                    	readAttributes(reader, document);
                    	onDocumentReady.onDocumentReady(document);                 	                        	
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:                	
                	final String endElementName = reader.getLocalName();
                    System.out.println("readDocuments END_ELEMENT:"+endElementName);                    
                    break;
            }
        }	
	}
    
    
     

    
    private static void readAttributes(XMLStreamReader reader,Document document) throws XMLStreamException {
    	
    	while (reader.hasNext()) {   		
            int eventType = reader.next();
            //final String startElementName2 = reader.getLocalName();
            //System.out.println("---- readAttributes eventType:"+eventType);            
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:                	
                	final String startElementName = reader.getLocalName();                	
                	if(startElementName.equals("attribute")) {        
                		//System.out.println("---- readAttributes start of attribute:"+startElementName);
                		Attribute attribute = readAttribute(reader);
                		document.addAttribute(attribute);       
                	}
                    break;
                case XMLStreamReader.END_ELEMENT:                	
                	final String endElementName = reader.getLocalName();               	                	
                	if(endElementName.equals("document")) {        
                		//System.out.println("---- readAttributes end of attributes");              		
                		return;       
                	}                	
                    break;                    
            }
        }        
		
	}
    
    private static String readAttributeValue(XMLStreamReader reader,String attributeName) throws XMLStreamException {
    	String attributeValue = "";
    	while (reader.hasNext()) {
            int eventType = reader.next();
            //System.out.println("readAttributeValue eventType:"+eventType);
            switch (eventType) {            	
                case XMLStreamReader.CHARACTERS: 
                	attributeValue = reader.getText();                	
                	//System.out.println("readAttributeValue start elementName:"+attributeValue+" for attributeName:"+attributeName);
                	return attributeValue;                    
                case XMLStreamReader.START_ELEMENT:
                	String startElementName = reader.getLocalName();
                	//System.out.println("readAttributeValue start elementName:"+startElementName);
                    break;                
            }
        }
    	System.err.println("Premature end of attribute for attributeName:"+attributeName);    	
    	return attributeValue;
    	
		
	}
    private static Attribute readAttribute(XMLStreamReader reader) throws XMLStreamException {
    	String attributeName = "";
    	 for(int index = 0;  index < reader.getAttributeCount(); index++) {
 			QName attributeQName = reader.getAttributeName(index);
 			if(attributeQName.getLocalPart().equals("name")) {
 				//System.out.println(reader.getAttributeValue(index));
 				attributeName = reader.getAttributeValue(index);
 				//System.out.println(attributeName);
 				
 			}else {
 				System.err.println(" unknown attribute for document:"+attributeQName.getLocalPart());
 			}
 		}
    	 //String attributeValue = "askldjfasd";
    	 String attributeValue = readAttributeValue(reader,attributeName);
    	 //System.out.println(attributeName+":"+attributeValue);
    	 
    	 //boolean isBoolean = featureMappings.isBoolean(attributeName);
    	 /*
    	 if(featureMappings.isBoolean(attributeName)) {
    		 return new Attribute(featureMappings.getDisplayName(attributeName) , attributeValue);
    	 }else {
    		 return new Attribute(attributeName , attributeValue);
    		 return new Attribute(featureMappings.getDisplayName(attributeName) , attributeValue);
    	 }
    	 
    	 return new Attribute(attributeName , attributeValue);
    	 */
    	 if(attributeName.startsWith("featurePRODFEAT")) {
    		 String displayName = featureMappings.getDisplayName(attributeName);
    		 if(displayName != null)
    			 return new Attribute(displayName, attributeValue);    		  
    	 }     		
             
    	 return new Attribute(attributeName , attributeValue);
    	 
	}     
    
    public static void writeToFile(String content, String fileName) {

    	/*
    	C:\Users\meshkatul\Box Sync\rise-search\mms-elasticsearch-poc\guttenberg_search\smart-tvs
    	*/
    	
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/meshkatul/Box Sync/rise-search/mms-elasticsearch-poc/product_search/smart-tvs/"+fileName+".json"))) {
		//try (BufferedWriter bw = new BufferedWriter(new FileWriter("/data/"+fileName+".json"))) {
			bw.write(content);			
			// no need to close it.
			//bw.close();
			System.out.println("Done");
		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
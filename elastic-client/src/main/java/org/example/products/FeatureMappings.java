package org.example.products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeatureMappings {


    public final String FILE_MAPPING = "src/main/resources/features_mapping_de.csv";
    public final String FILE_BOOLEAN = "src/main/resources/features_boolean_de.csv";
    public final String FILE_DEFINITION = "src/main/resources/features_definition_de.csv";

    Map<String,String> map_aggregated;
    List<AttributeDef> list;

    public FeatureMappings() throws IOException {
    	
        Map<String,String> map_mappings = fileToMap(FILE_MAPPING, 0);        
        
        Map<String,String> map_boolean = fileToMap(FILE_BOOLEAN, 1);
        
        Map<String,String> map_definition = fileToMap(FILE_DEFINITION,0);

        
        System.out.println("-------------------------------------------------");
        //map_aggregated = getAggregatedMap(map_mappings, map_boolean, map_definition);
        list = getAggregatedMap2(map_mappings, map_boolean, map_definition);        
        
    }

    public Map<String,String> getMap() {
        return this.map_aggregated;
    }
    
    public List<AttributeDef> getAttributeDef() {
        return this.list;
    }


    private Map<String,String> getAggregatedMap(Map<String,String> map_mappings, Map<String,String> map_boolean, Map<String,String> map_definition) {
        Map<String,String> mapToReturn = new HashMap<>();

        for (Map.Entry<String,String> entry : map_mappings.entrySet()) {
            if (entry.getValue().equals("boolean_search_features")) {
                mapToReturn.put(entry.getKey(), map_boolean.get(entry.getKey()));
            } else {
                mapToReturn.put(entry.getKey(), map_definition.get(entry.getValue()));
            }
        }
        return mapToReturn;
    }
    
    private List<AttributeDef> getAggregatedMap2(Map<String,String> map_mappings, Map<String,String> map_boolean, Map<String,String> map_definition) {        
        List<AttributeDef> list = new ArrayList<AttributeDef>();

        for (Map.Entry<String,String> entry : map_mappings.entrySet()) {
            if (entry.getValue().equals("boolean_search_features")) {
            	System.out.print("key:"+entry.getKey());
            	System.out.print(" isBoolean:"+ true);
            	System.out.println(" displayName:"+ map_boolean.get(entry.getKey()));
            	AttributeDef attributeDef = new AttributeDef(entry.getKey(),map_boolean.get(entry.getKey()), true );
            	list.add(attributeDef);
                //mapToReturn.put(entry.getKey(), map_boolean.get(entry.getKey()));
            } else {
                //mapToReturn.put(entry.getKey(), map_definition.get(entry.getValue()));

            	System.out.print("key:"+entry.getKey());
            	System.out.print(" isBoolean:"+ false);
            	System.out.println(" displayName:"+ map_definition.get(entry.getValue()));
            	
                AttributeDef attributeDef = new AttributeDef(entry.getKey(),map_definition.get(entry.getValue()), false );
            	list.add(attributeDef);
            }
        }
        return list;
    }

    private Map<String,String> fileToMap(String path, Integer offset) throws IOException {
        Map<String,String> mapToReturn = new HashMap<>();
        BufferedReader buffer = new BufferedReader(new FileReader(path));
        String line;

        while((line = buffer.readLine()) != null) {
            String[] items = line.split("\t");
            
            System.out.println(items[0] + ":"+items[1 + offset]);
            mapToReturn.put(items[0], items[1 + offset]);
        }
        return mapToReturn;
    }

	public boolean isBoolean(String attributeName) {		
		return (list
			.stream()
			.filter(item -> item.getId().equals(attributeName))
			.filter(item -> item.isBoolean())
			.count() > 0 );
	}

	public String getDisplayName(String attributeName) {
		List<AttributeDef> result = list
				.stream()
				.filter(item -> item.getId().equals(attributeName))				
				.collect(Collectors.toList());
		if(result.size() == 0) {
			System.err.println("display name "+attributeName+" not found.");
			return null;
			//return attributeName+"_no_featureMapping";
		}
		AttributeDef attributeDef = result.get(0);
		if(attributeDef != null)
			return attributeDef.getDisplayName();
		else {
			System.err.println("display name "+attributeName+" not found.");
			return attributeName+"_no_featureMapping";
		}
	}

    /*

    try{
            BufferedReader buf = new BufferedReader(new FileReader("/home/little/Downloads/test"));
            ArrayList<String> words = new ArrayList<>();
            String lineJustFetched = null;
            String[] wordsArray;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    wordsArray = lineJustFetched.split("\t");
                    for(String each : wordsArray){
                        if(!"".equals(each)){
                            words.add(each);
                        }
                    }
                }
            }

            for(String each : words){
                System.out.println(each);
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }

     */

}

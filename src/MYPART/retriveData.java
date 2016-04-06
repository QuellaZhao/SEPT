package MYPART;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class retriveData {
	public static void main(String[] args){
	try {
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("stations.json"));
		JsonArray array = object.get("Array").getAsJsonArray();
		for(int i = 0; i<array.size(); i++){
			JsonObject obj = array.get(i).getAsJsonObject();
			System.out.println("state: " + obj.get("state").getAsString());
			
			JsonArray ar = obj.get("stations").getAsJsonArray();
			for(int j = 0; j<ar.size(); j++){
				JsonObject subobj = ar.get(j).getAsJsonObject();
				System.out.println("city: " + subobj.get("city").getAsString());
				System.out.println("url: " + subobj.get("url").getAsString());
			}
			System.out.println("================================");
		}
		
		
		
	} catch (JsonIOException e) {
		e.printStackTrace();
	} catch (JsonSyntaxException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	}
}

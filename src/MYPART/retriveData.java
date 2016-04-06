package MYPART;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.io.FileUtils; 
import org.json.*;
import org.json.simple.*;

public class retriveData {
	/*public static String downloadFromUrl(String url,String dir) {  
		  
        try {  
            URL httpurl = new URL(url);  
            String fileName = "temp.json";  
            System.out.println(fileName);  
            File f = new File(dir + fileName);  
            FileUtils.copyURLToFile(httpurl, f);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "Fault!";  
        }   
        return "Successful!";  
    }
	*/
public void justtry(){
	
	/*StringBuffer stringBuffer = new StringBuffer();
	String line = null ;
	try {
	BufferedReader br = new BufferedReader(new FileReader(new File("C:/Users/Administrator/Desktop/tep.json")));
	while( (line = br.readLine())!= null ){
	stringBuffer.append(line);
	} 
	} catch (FileNotFoundException e) {
	       e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}*/
	
	JSONParser parser = new JSONParser();
	
	
	JSONObject jsonObject = new JSONObject(stringBuffer.toString());
	
	String air_temp = null;
	StringBuffer jsonFileInfo = new StringBuffer();
		air_temp = jsonObject.getJSONObject("observations").toString();
		
		String des = air_temp.substring(air_temp.indexOf("\"air_temp\":"), air_temp.indexOf(",\"local_date_time_full\":"));

		      System.out.print("air tempreture:" + des +"\n");
	}
public static void main(String[] args){
	retriveData rd = new retriveData();
	rd.justtry();
	
}
}

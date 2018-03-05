/*
 * This class has functions to verify and read config file parameters
 * @author Saket Mishra
 */

package Lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TRCParser{
	
	private final String trvFileName="TRFConfig/TR.CONFIG";
	
	HashMap<String,String> objHashMap= new HashMap<String,String>();
	
	/*
	 * This method checks if config File Exists
	 * @returns boolean
	 */
	
	public boolean trmCheckIfFileExists() {
	
		File objFileExists=new File(trvFileName);
		
		if(objFileExists.exists()) {
			
			return true;
		}
		else {
			
			return false;
		}
	
	}
	
	
	
	/*
	 * this method checks if the config File has any bad entries
	 * 
	 * @returns boolean
	 * 
	 */
	
	public boolean trmVerifyConfigFile() {
		
		int trvCountEmptyLines=0;
		int trvCountNonEmptyLines=0;
		int trvCountParameters=0;
		int trvTotalParameters=2;
		
		try {
			BufferedReader objBufferedReader=new BufferedReader(new FileReader(trvFileName));
			
			String line=objBufferedReader.readLine();
			
			while(line != null) {
				//System.out.println(line);
				
				line=line.trim();
				
				if(line.length()==0 || line.startsWith("#") || line.contains(":") && line.split(":").length==2 ){
					
					if(line.length()==0) {
						trvCountEmptyLines++;
					}
					trvCountNonEmptyLines++;
					
					if(line.startsWith("PORT_NO")) {

						trvCountParameters++;
					}
					else if(line.startsWith("SERVER_NAME")) {

						trvCountParameters++;
					}
					else {
						
						if(line.length()!=0 && !line.startsWith("#")) {

							trvCountParameters=trvCountParameters+2;
						}
					}
				}
				else {
					objBufferedReader.close();
					return false;
				}
				
				line=objBufferedReader.readLine();
			}
			
			
			objBufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(trvCountEmptyLines==trvCountNonEmptyLines || trvTotalParameters!=trvCountParameters){
			System.out.println("this block"+trvTotalParameters+trvCountParameters);
			return false;
		}
		
		return true;
	}
	
	
	/*
	 * This method reads the Config file and store them in HashMap
	 * 
	 */
	
	
	public boolean trmLoadConfigFile() {
		
		
		try {
			BufferedReader objBufferedReader=new BufferedReader(new FileReader(trvFileName));
			
			String line = objBufferedReader.readLine();
			
			while(line != null) {
				
				line=line.trim();
				
				if(line.length()!=0 && !line.startsWith("#")) {
					
					objHashMap.put(line.split(":")[0], line.split(":")[1]);
				}
				
				line = objBufferedReader.readLine();
			}
			
			objBufferedReader.close();
			
			System.out.println(objHashMap.get("PORT_NO"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/*
	 * 
	 */
	
public String trmGetConfigValues(String Key) {
		
		return objHashMap.get(Key);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
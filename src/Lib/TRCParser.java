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
	
	private final String vFileName="TRFConfig/TR.CONFIG";
	private final String vKeyValueSeparator=":";
	private final String vComment="#";
	
	HashMap<String,String> objHashMap= new HashMap<String,String>();
	
	
	
	/*
	 * This method checks if config File Exists
	 * @returns boolean
	 */
	
	private boolean trmCheckIfFileExists() {
	
		File objFileExists=new File(vFileName);
		
		return objFileExists.exists();
	
	}
	
	
	
	/*
	 * this method checks if the config File has any bad entries
	 * @returns boolean
	 * 
	 */
	
	private boolean mVerifyConfigFile(String vKeyValueSeparator, String vComment) {
		
		int vCountEmptyLines=0;
		int vCountNonEmptyLines=0;
		
		System.out.println("Verifying Config File "+vFileName);
		System.out.println("vKey_Value_Separator "+vKeyValueSeparator+" vComment "+vComment);
		
		try {
			BufferedReader objBufferedReader=new BufferedReader(new FileReader(vFileName));
			
			String line=objBufferedReader.readLine();
			
			while(line != null) {
				
				line=line.trim();
				
				if(line.length()==0 || line.startsWith(vComment) || line.contains(vKeyValueSeparator) && line.split(vKeyValueSeparator).length>=2 ){
					
					if(line.length()==0) {
						vCountEmptyLines++;
					}
					vCountNonEmptyLines++;
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
		
		if(vCountEmptyLines==vCountNonEmptyLines){
			
			return false;
		}
		
		return true;
	}
	
	
	
	
	/*
	 * This method reads the Config file and store them in HashMap
	 * @return boolean
	 * 
	 */
	
	
	private boolean mLoadConfigFile(String vKeyValueSeparator, String vComment) {
		
		
		try {
			BufferedReader objBufferedReader=new BufferedReader(new FileReader(vFileName));
			
			String line = objBufferedReader.readLine();
			
			while(line != null) {
				
				line=line.trim();
				
				if(line.length()!=0 && !line.startsWith(vComment)) {
					
					objHashMap.put(line.split(vKeyValueSeparator,2)[0], line.split(vKeyValueSeparator,2)[1]);
				}
				
				line = objBufferedReader.readLine();
			}
			
			objBufferedReader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	
	/*
	 * this method returns key value that are stored in hashmap
	 * @param String<Key>
	 * @return String<Value>
	 */
	
public String mGetConfigValues(String Key) {
		
		return objHashMap.get(Key);
		
	}
	
	
	

/*
 * this method verifies and then loads config file
 * @param - String,String
 * @returns - boolean
 * 
 */

public boolean mVerifyAndLoadConfigFile(String vKeyValueSeparator, String vComment) {
	
	vKeyValueSeparator=vKeyValueSeparator.trim().isEmpty()?this.vKeyValueSeparator:vKeyValueSeparator.trim();
	vComment=vComment.trim().isEmpty()?this.vComment:vComment.trim();
	
	if( trmCheckIfFileExists() && mVerifyConfigFile(vKeyValueSeparator,vComment) ){
		
		return mLoadConfigFile(vKeyValueSeparator,vComment);
	}
	else {
		
		return false;
	}
}



/*
 * overloading method mVerifyAndLoadConfigFile
 * @returns - boolean
 * 
 */

public boolean mVerifyAndLoadConfigFile() {
	
	return mVerifyAndLoadConfigFile(this.vKeyValueSeparator,this.vComment);
}


/*
 * overloading method mVerifyAndLoadConfigFile
 * @param String<vKeyValueSeparator>
 * @returns - boolean
 * 
 */

public boolean mVerifyAndLoadConfigFile(String vKeyValueSeparator) {

	return mVerifyAndLoadConfigFile(vKeyValueSeparator,this.vComment);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
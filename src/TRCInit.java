/*
 * This class calls all the classes needed by TaskRunner
 * @author Saket Mishra
 */

import Lib.TRCParser;


public class TRCInit{
	
	TRCParser objTRCParser=new TRCParser();
	
	public void trmConfigFileCheckOnStartup() {
		
		System.out.println(objTRCParser.mVerifyAndLoadConfigFile(";"));
	
	}
	
	
}
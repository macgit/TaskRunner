/*
 * This class calls all the classes needed by TaskRunner
 * @author Saket Mishra
 */

import Lib.TRCParser;


public class TRCInit{
	
	TRCParser objTRCParser=new TRCParser();
	
	public void trmConfigFileCheckOnStartup() {
		
		System.out.println(objTRCParser.trmCheckIfFileExists());
		System.out.println(objTRCParser.trmVerifyConfigFile());
		System.out.println(objTRCParser.trmLoadConfigFile());
		
		System.out.println(objTRCParser.trmGetConfigValues("PORT_NO"));
	}
	
	
}
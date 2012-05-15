package cn.gd.lucas.util;

public class ReplaseSpecialXmlChar {
	public static String doReplace(String input){
		
		input=input.replace("&", "&amp;");
		input=input.replace("<", "&lt;");	
		input=input.replace(">", "&gt;");
		input=input.replace("'", "&apos;");
		input=input.replace("\"", "&quot;");
		return input;
	}

}

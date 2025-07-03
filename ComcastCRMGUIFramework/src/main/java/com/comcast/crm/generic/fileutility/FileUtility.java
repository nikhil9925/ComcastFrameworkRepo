package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fils = new FileInputStream("./configAppData/commondata.properties");
		Properties p = new Properties();
		p.load(fils);
		String data = p.getProperty(key);
		return data;
	}
}

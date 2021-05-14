package com.mystore.utilities;

import java.io.*;
import java.util.Properties;

public class Config {

	Properties pro;
	File src = new File("./Configuration/config.properties");
	
	public Config() {
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch(Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
	}

	public String getBaseUrl(){
		String url = pro.getProperty("baseUrl");
		return url;
	}
	public String getLoginEmail(){
		String loginEmail = pro.getProperty("email");
		return loginEmail;
	}
	public String getLoginPassword(){
		String loginPass = pro.getProperty("password");
		return loginPass;
	}
	public void setLoginEmailAndPass(String em,String pass) throws IOException {
		FileOutputStream out =new FileOutputStream(src);
		pro.setProperty("email",em);
		pro.setProperty("password",pass);
		pro.store(out,null);
		out.close();
	}
}

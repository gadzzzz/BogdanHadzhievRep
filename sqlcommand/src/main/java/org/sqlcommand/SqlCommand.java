package org.sqlcommand;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlCommand {
	final static Logger LOGGER = Logger.getLogger(SqlCommand.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Locale.setDefault(Locale.ENGLISH);
		System.out.println("Connected to Database");
		QueryInjector injector = (QueryInjector)context.getBean("queryInjector");
		Scanner in = new Scanner(System.in);
		boolean running=true;
		String query = "";
		while(running){
			String tmpQuery = in.nextLine();
			query=query+" "+tmpQuery;
			for(int i=0;i<tmpQuery.length();i++)
				try{
					if(tmpQuery.charAt(i)==';'){
						LOGGER.info(query.trim());
						if(query.substring(0,7).toLowerCase().trim().equals("create"))
								injector.executeUpdate(query.trim());
						if(query.substring(0,7).toLowerCase().trim().equals("update"))
							injector.executeUpdate(query.trim());
						if(query.substring(0,7).toLowerCase().trim().equals("insert"))
							injector.executeUpdate(query.trim());
						if(query.substring(0,7).toLowerCase().trim().equals("delete"))
							injector.executeUpdate(query.trim());
						if(query.substring(0,7).toLowerCase().trim().equals("select")){
							String result = injector.executeQuery(query.trim());
							System.out.println(result);
							LOGGER.info(result);
						}
						query="";
					}
				}catch(Exception e){
					System.out.println(e);
					query="";
					LOGGER.error("Error in SQL" + e);
				}
		}
		context.close();
	}

}

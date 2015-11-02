package org.sqlcommand;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlCommand {
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
				if(tmpQuery.charAt(i)==';'){
					if(query.substring(0,7).toLowerCase().trim().equals("create"))
						injector.executeUpdate(query.trim());
					if(query.substring(0,7).toLowerCase().trim().equals("update"))
						injector.executeUpdate(query.trim());
					if(query.substring(0,7).toLowerCase().trim().equals("insert"))
						injector.executeUpdate(query.trim());
					if(query.substring(0,7).toLowerCase().trim().equals("delete"))
						injector.executeUpdate(query.trim());
					if(query.substring(0,7).toLowerCase().trim().equals("select"))
						System.out.println(injector.executeQuery(query.trim()));
					query="";
				}
		}
		context.close();
	}

}

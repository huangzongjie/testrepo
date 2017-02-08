package bean;

import java.sql.*;
import java.io.*;
import java.util.*;

public class DbConnectionManager {


 
	
	public static Connection getConnection() {
//		long l1=System.currentTimeMillis();
    	Connection con = null;
    	try {
    		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
    	//	con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.230:1433;DatabaseName=bankDB", "sa", "sa");
    		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    		con = DriverManager.getConnection("jdbc:Oracle:thin:@192.168.0.230:1521:orcl", "mytest", "mytest");
    		
    		String driver = "";  
            String url = "";  
    	} catch (Exception ee) {
    		ee.printStackTrace();
    	}
//    	long l2=System.currentTimeMillis();
//    	System.out.println("����ʱ�� "+(l2-l1)/1000);
    	return con;
	}
	
}

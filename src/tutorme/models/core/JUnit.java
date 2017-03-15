package tutorme.models.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public final class JUnit {
	//set to false to allow compiler to identify and eliminate
	//unreachable code
	public static boolean ON = false;
	
	public static Connection getDBConnection(String dbPath, String dbName, String username, String password) {
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbPath + dbName + "test", username, password);
		}
		catch(MySQLSyntaxErrorException e) {
			Connection conn;
			try {
				conn = DriverManager.getConnection(String.format("%1$s?user=%2$s&password=%3$s", dbPath, username, password));
				int result = conn.createStatement().executeUpdate(String.format("CREATE DATABASE %1$s", dbName + "test"));
				if(result > 0) {
					conn = DriverManager.getConnection(dbPath + dbName + "test", username, password);
					generateDB(conn, "../SQL/GenerateDB.sql");
					return conn;
				}
				e.printStackTrace();
				return null;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void generateDB(Connection conn, String sqlPath) {
		String s            = new String();
        StringBuffer sb = new StringBuffer();
 
        try
        {
            FileReader fr = new FileReader(new File(sqlPath));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
 
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
 
            Statement st = conn.createStatement();
 
            for(int i = 0; i<inst.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals(""))
                {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
   
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
	}
} 

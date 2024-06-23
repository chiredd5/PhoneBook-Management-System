package phone;
import java.sql.*;
import java.awt.*;
public class Connections {
	Connection conn;
	Statement smt;
	Connections()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","newnewone","reddappa");
			smt=conn.createStatement();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		new Connections();
	}
}

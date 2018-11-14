package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtil {
	
	//定义数据库的用户名
	private static String USERNAME;
	
	//定义数据库的密码
	private static String PASSWORD;
	
	//定义数据库的驱动信息
	private static String DRIVER;
	
	//定义数据库的连接地址
	private static String URL;
	
	//定义数据库的连接
	private static Connection connection;
	
	/**
	 * 静态加载配置文件，并且给相应的属性赋值
	 */
	
	static{
		
		try {
			InputStream instream=JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			
			Properties prop=new Properties();
			
			prop.load(instream);
			
			USERNAME=prop.getProperty("jdbc.username");
			
			PASSWORD=prop.getProperty("jdbc.password");
			
			DRIVER=prop.getProperty("jdbc.driver");
			
			URL=prop.getProperty("jdbc.url");
			
		} catch (Exception e) {
			throw new RuntimeException("读取文件异常!",e);
		}
	}
	
	/**
	 * 获取数据库的连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getCon() throws SQLException{
		
		try {
			Class.forName(DRIVER);
			connection=(Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("x");
			System.out.println(connection);
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 测试连接是否成功
	 */
	public static void main(String[] args){
		try {
			System.out.println(JdbcUtil.getCon());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}

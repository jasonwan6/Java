package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtil {
	
	//�������ݿ���û���
	private static String USERNAME;
	
	//�������ݿ������
	private static String PASSWORD;
	
	//�������ݿ��������Ϣ
	private static String DRIVER;
	
	//�������ݿ�����ӵ�ַ
	private static String URL;
	
	//�������ݿ������
	private static Connection connection;
	
	/**
	 * ��̬���������ļ������Ҹ���Ӧ�����Ը�ֵ
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
			throw new RuntimeException("��ȡ�ļ��쳣!",e);
		}
	}
	
	/**
	 * ��ȡ���ݿ������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * ���������Ƿ�ɹ�
	 */
	public static void main(String[] args){
		try {
			System.out.println(JdbcUtil.getCon());
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
}

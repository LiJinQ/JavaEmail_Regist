package com.ljq.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {
	private static DataSource ds = null;
	static {
		try {
			InputStream in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			//String path = DBCPUtil.class.getClassLoader().getResource("//").getPath()+"dbcpconfig.properties";
			Properties prop = new Properties();
			prop.load(in);
			//prop.load(new BufferedInputStream (new FileInputStream(path)));
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }      
        if(conn!=null){
            try{
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

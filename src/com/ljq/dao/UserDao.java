package com.ljq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ljq.po.User;
import com.ljq.util.DBCPUtil;

public class UserDao {
	private QueryRunner queryRunner=null;
	
	
	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
		queryRunner = new QueryRunner(DBCPUtil.getDataSource());
	}
	public void newUserByDbutil(User user) {
		String sql = "insert into user(username,password,email,code,status) values(?,?,?,?,?)";
		try {
			queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.getStatus());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void newUser(User user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into user(username,password,email,code,status) values(?,?,?,?,?)";
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getCode());
			pstmt.setInt(5, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
	}
	
	public void updateUserByDbutil(User user) {
		String sql = "update user set username=?,password=?,email=?,code=?,status=? where id=?";
		try {
			queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.getStatus(),user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update user set username=?,password=?,email=?,code=?,status=? where id=?";
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getCode());
			pstmt.setInt(5, user.getStatus());
			pstmt.setInt(6, user.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
	}
	
	public User getUserByUserNameByDbutil(String username) {
		String sql = "select * from user where username='"+username+"'";
		User user = null;
		try {
			user = new User();
			user = queryRunner.query(sql,new BeanHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User getUserByUserName(String username) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where username='"+username+"'";
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setCode(rs.getString("code"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return user;
	}
	
	public User getUserByCodeByDbutil(String code) {
		String sql = "select * from user where code='"+code+"'";
		User user = null;
		try {
			user = new User();
			user = queryRunner.query(sql, new BeanHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User getUserByCode(String code) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where code='"+code+"'";
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setCode(rs.getString("code"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return user;
	}
}

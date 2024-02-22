package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.User;
import com.util.ProjectUtil;

public class UserDao {

	public static void signupUser(User u) {
		try {
			Connection conn=ProjectUtil.creatConnection();
			String sql="insert into user(fname,lname,email,mobile,address,password) value(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setLong(4, u.getMobile());
			pst.setString(5, u.getAddress());
			pst.setString(6, u.getPassword());
			pst.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean checkemail(String email) {
		boolean flag=false;
		try {
			Connection conn=ProjectUtil.creatConnection();
			String sql="select * from user where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				flag= true;
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}

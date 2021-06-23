package com.jsp.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDTO {
	public MemberDTO getMember(String id) {
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet set = null;
	    String query = "select * from users where id = ?";
	    MemberDTO dto = null;
	    
	    try {
	        connection = getConnection();
	        pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, id);
	        set = pstmt.executeQuery();
	        
	        if(set.next()) {
	            dto = new MemberDTO();
	            dto.setId(set.getString("id"));
	            dto.setPw(set.getString("pw"));
	            dto.setName(set.getString("name"));
	            dto.seteMail(set.getString("eMail"));
	            dto.setrDate(set.getTimestamp("rDate"));
	            dto.setAddress(set.getString("address"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            set.close();
	            pstmt.close();
	            connection.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    
	    return dto;
	    
	}

	public int updateMember(MemberDTO dto) {
	    int ri = 0;
	    
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    String query = "update users set pw=?, eMail=?, address=? where id=?";
	    
	    try {
	        connection = getConnection();
	        pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, dto.getPw());
	        pstmt.setString(2, dto.geteMail());
	        pstmt.setString(3, dto.getClass());
	        pstmt.setString(4, dto.getId());
	        ri = pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            pstmt.close();
	            connection.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    
	    return ri;
	}

	private String geteMail() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getPw() {
		// TODO Auto-generated method stub
		return null;
	}

	private Connection getConnection() {
	    
	    Context context = null;
	    DataSource dataSource = null;
	    Connection connection = null;
	    try {
	        context = new InitialContext();
	        dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
	        connection = dataSource.getConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return connection;
	}
	
}

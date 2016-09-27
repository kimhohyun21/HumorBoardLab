package com.dao;

import java.util.*;
import java.sql.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.83:1521:ORCL";
	
	public BoardDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn = DriverManager.getConnection(URL, "scott", "tiger");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	public void disConnection(){
		try{
			if(ps !=null) ps.close();
			if(conn !=null) conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public List<BoardDTO> boardListData(int page){
		List<BoardDTO> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT no, subject, name, regdate, hit, group_tab, TO_CHAR(regdate, 'YYYY-MM-DD') FROM humorboard ORDER BY group_id DESC, group_step ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			int rowSize = 5;
			int i = 0;
			int j = 0;
			int pagecnt = (page*rowSize)-rowSize;
			while(rs.next()){
				if(i<rowSize && j>=pagecnt){
					BoardDTO dto = new BoardDTO();
					dto.setNo(rs.getInt(1));
					dto.setSubject(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setDate(rs.getDate(4));
					dto.setHit(rs.getInt(5));
					dto.setGroup_tab(rs.getInt(6));
					dto.setDbday(rs.getString(7));
					list.add(dto);
					i++;
				}
				j++;
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
		return list;
	}
	
	public int boardTotalPage(){
		int total = 0;
		try{
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/5) FROM humorboard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
		
		return total;
	}
	
	public int boardCount(){
		int total = 0;
		try{
			getConnection();
			String sql = "SELECT COUNT(*) FROM humorboard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
		return total;
	}
}

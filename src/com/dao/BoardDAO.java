package com.dao;

import java.util.*;

import java.sql.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.88:1521:ORCL";
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
			
			int rowSize = 10;
			int i = 0;
			int j = 0;
			int pagecnt = (page*rowSize)-rowSize;
			while(rs.next()){
				if(i<rowSize && j>=pagecnt){
					BoardDTO dto = new BoardDTO();
					dto.setNo(rs.getInt(1));
					dto.setSubject(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setRegdate(rs.getDate(4));
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
			String sql = "SELECT CEIL(COUNT(*)/10) FROM humorboard";
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
	
	public BoardDTO boardContentData(int no){
		BoardDTO dto=new BoardDTO();

		try{
			getConnection();

			String sql="UPDATE humorboard SET hit=hit+1 WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,no);
			ps.executeUpdate();
			ps.close();

			sql="SELECT no, name, subject, content, regdate, hit FROM humorboard WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,no);
			ResultSet rs= ps.executeQuery();
			rs.next();

			dto.setNo(rs.getInt(1));
			dto.setName(rs.getString(2));
			dto.setSubject(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setRegdate(rs.getDate(5));
			dto.setHit(rs.getInt(6));

			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnection();
		}

		return dto;
	}
}

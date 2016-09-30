package com.dao;

import java.util.*;

import java.sql.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.81:1521:ORCL";
	public BoardDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn = DriverManager.getConnection(URL, "scott", "1234");
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
			String sql = "SELECT no, subject, name, regdate, hit, group_tab, TO_CHAR(regdate, 'YYYY-MM-DD'), hot FROM humorboard ORDER BY group_id DESC, group_step ASC";
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
					dto.setHot(rs.getInt(8));
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
	
	public List<BoardDTO> boardTileData(int page){
	      List<BoardDTO> list = new ArrayList<>();
	      try{
	         getConnection();
	         String sql = "SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD'), hot FROM humorboard ORDER BY group_id DESC, group_step ASC";
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
	               dto.setDbday(rs.getString(4));
	               dto.setHot(rs.getInt(5));
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
	
	//insert
	public void boardInsert(BoardDTO dto){
		try{
			getConnection();
			
			String sql="INSERT INTO humorboard (no, name, subject, content, pwd, group_id) "
						+ "VALUES((SELECT NVL((MAX(no)+1), 1) FROM humorboard), ?, ?, ?, ?, "
						+ "(SELECT NVL((MAX(group_id)+1), 1) FROM humorboard))";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getSubject());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getPwd());
			ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnection();
		}
	}
	
	//reply
	public void boardReply(int no, BoardDTO dto){
		try{
			getConnection();
			
			//답변이 달리는 글의 gi, gs, gt 정보 가져오기
			String sql="SELECT group_id, group_step, group_tab FROM humorboard WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();

			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);

			rs.close();
			ps.close();
			
			//group_step 설정
			sql="UPDATE humorboard SET group_step=group_step+1 "
				+ "WHERE group_id=? AND group_step>?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			ps.close();
			
			//depth 설정
			sql="UPDATE humorboard SET depth=depth+1 WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			ps.close();
			
			//reply insert
			sql="INSERT INTO humorboard (no, name, subject, content, pwd, group_id, group_step, group_tab, root) "
				+ "VALUES((SELECT NVL(MAX(no)+1, 1) FROM humorboard), ?, ?, ?, ?, ?, ?, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getSubject());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, no);
			ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnection();
		}
	}
}

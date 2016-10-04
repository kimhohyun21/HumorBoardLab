package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.*;
import java.util.*;
import java.text.*;

public class ListModel implements Model{
/*
 * ListModel은 jsp에서 사용할 구현하여 클라이언트로부터 요청받고 DB에서 값을 불러온는 코딩을 구현
 */
	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		//메서드 사용을 위한 dao class생성
		BoardDAO dao=new BoardDAO();
		
		//jsp에서 사용할 파라미터 값 설정
		//page 전환을 위한 파라미터
		String strPage=request.getParameter("page"); 
		if(strPage==null){strPage="1"; }
		int curPage=Integer.parseInt(strPage);
		
		//검색을 위한 파라미터
		String fs=request.getParameter("fs");
		if(fs==""){fs=null;};
		System.out.println(fs);
		String fi=request.getParameter("fi");
		if(fi==""){fi=null;};
		System.out.println(fi);
		//삭제된 게시물 관리를 위한 파라미터
		String msg="게시자에 의해서 삭제된 게시물입니다.";
		
		//new_icon생성을 위한 파라미터 
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		//게시물을 생성하기 위한 list 생성
		List<BoardDTO> list=dao.boardListData(curPage, fs, fi);
		
		//전체 페이지수 사용을 위한 파라미터
		String plist="2";
		int totalPage=dao.boardTotalPage(plist, fs, fi);
		String tile="1";
		int tileTotal=dao.boardTotalPage(tile, fs, fi);
		
		//블럭 출력을 위한 변수 설정
		int block=5;
		int fromPage=(((curPage-1)/block)*block)+1;
		int toPage=(((curPage-1)/block)*block)+block;
		if(toPage>totalPage)toPage=totalPage;
		
		//각 변수 request에 set하기 ==> list.jsp에서 사용 
		request.setAttribute("curPage", curPage);
		request.setAttribute("msg", msg);
		request.setAttribute("today", today);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("block", block);
		request.setAttribute("fromPage", fromPage);
		request.setAttribute("toPage", toPage);
		request.setAttribute("plist", plist);
		request.setAttribute("tileTotal", tileTotal);
		request.setAttribute("fs", fs);
		request.setAttribute("fi", fi);
		
		//list.jsp로 콜백
		return "humor/list.jsp";
	}

}

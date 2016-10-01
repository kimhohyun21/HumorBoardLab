package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardDTO;

public class TileModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao=new BoardDAO();
		String strPage=request.getParameter("page"); 
		if(strPage==null){strPage="1"; }
		int curPage=Integer.parseInt(strPage);
		  
		//new_icon생성을 위한 파라미터 
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		  
		List<BoardDTO> list=dao.boardTileData(curPage);
		  
		//전체 페이지수 사용을 위한 파라미터
		String plist="1";
		int totalPage=dao.boardTotalPage(plist);
		  
		//블럭 출력을 위한 변수 설정
		int block=5;
		int fromPage=(((curPage-1)/block)*block)+1;
		int toPage=(((curPage-1)/block)*block)+block;
		if(toPage>totalPage)toPage=totalPage;
		  
		request.setAttribute("today", today);
		request.setAttribute("curPage", curPage);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("block", block);
		request.setAttribute("fromPage", fromPage);
		request.setAttribute("toPage", toPage);
		request.setAttribute("plist", plist);
		  
		return "humor/tile.jsp";
	}
}
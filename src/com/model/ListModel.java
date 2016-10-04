package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.*;
import java.util.*;
import java.text.*;

public class ListModel implements Model{
/*
 * ListModel�� jsp���� ����� �����Ͽ� Ŭ���̾�Ʈ�κ��� ��û�ް� DB���� ���� �ҷ��´� �ڵ��� ����
 */
	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		//�޼��� ����� ���� dao class����
		BoardDAO dao=new BoardDAO();
		
		//jsp���� ����� �Ķ���� �� ����
		//page ��ȯ�� ���� �Ķ����
		String strPage=request.getParameter("page"); 
		if(strPage==null){strPage="1"; }
		int curPage=Integer.parseInt(strPage);
		
		//�˻��� ���� �Ķ����
		String fs=request.getParameter("fs");
		if(fs==""){fs=null;};
		System.out.println(fs);
		String fi=request.getParameter("fi");
		if(fi==""){fi=null;};
		System.out.println(fi);
		//������ �Խù� ������ ���� �Ķ����
		String msg="�Խ��ڿ� ���ؼ� ������ �Խù��Դϴ�.";
		
		//new_icon������ ���� �Ķ���� 
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		//�Խù��� �����ϱ� ���� list ����
		List<BoardDTO> list=dao.boardListData(curPage, fs, fi);
		
		//��ü �������� ����� ���� �Ķ����
		String plist="2";
		int totalPage=dao.boardTotalPage(plist, fs, fi);
		String tile="1";
		int tileTotal=dao.boardTotalPage(tile, fs, fi);
		
		//�� ����� ���� ���� ����
		int block=5;
		int fromPage=(((curPage-1)/block)*block)+1;
		int toPage=(((curPage-1)/block)*block)+block;
		if(toPage>totalPage)toPage=totalPage;
		
		//�� ���� request�� set�ϱ� ==> list.jsp���� ��� 
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
		
		//list.jsp�� �ݹ�
		return "humor/list.jsp";
	}

}

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
		//�޼��� ����� ���� dao class����
		BoardDAO dao=new BoardDAO();
		
		//jsp���� ����� �Ķ���� �� ����
		//page ��ȯ�� ���� �Ķ����
		String strPage=request.getParameter("page"); 
		if(strPage==null){strPage="1"; }
		int curPage=Integer.parseInt(strPage);
		
		//������ �Խù� ������ ���� �Ķ����
		String msg="�����ڿ� ���ؼ� ������ �Խù��Դϴ�.";
		
		//new_icon������ ���� �Ķ���� 
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		//�Խù��� �����ϱ� ���� list ����
		List<BoardDTO> list=dao.boardListData(curPage);
		
		//��ü �������� ����� ���� �Ķ����
		String plist="2";
		int totalPage=dao.boardTotalPage(plist);
		
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
		
		//list.jsp�� �ݹ�
		return "humor/list.jsp";
	}

}

package com.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.*;
import com.model.*;

public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//beans.xml에 설정된 model값을 저장하기 위한 해시맵 생성
	private Map clsMap=new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try{
			//beans를 읽어오기 위한 주소 값 받기
			String path=config.getInitParameter("modelLocation");
			
			//주소값을 사용하기위한 build 생성(new instance로 생성 왜??)
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			
			//주소 값을 파싱하여 beans.xml을 파일 불러오기 ==> DOM객체 활용
			Document doc=db.parse(new File(path));
			
			//DOM객체를 활용하기 위한 root생성
			Element root=doc.getDocumentElement();
			
			//DOM에서 node(bean)를 태그네임으로 읽어서 list에 저장
			NodeList list=root.getElementsByTagName("beans");
			
			//list에 저장된 내용(bean) 불러오기
			for(int i=0;i<list.getLength();i++){
				//bean을 Element로 생성
				Element elm=(Element) list.item(i);
				
				//생성 bean에서 id와 class값 받아오기
				String id=elm.getAttribute("id");
				String cls=elm.getAttribute("class");
				
				//cls로 model을 호출하여 class파일로 연결				
				Class clsName=Class.forName(cls);
				
				//읽어온 class파일을 객체로 받아서 메모리에 올리기
				//==>메모리에 올라가면 해시값이 할당됨
				//==>해당 class파일(ListModel.java)의 멤버 변수 메소드 활용 가능
				Object obj=clsName.newInstance();
				
				//생성한 해시값을 해시맵으로 저장
				clsMap.put(id, obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//URL로 호출되었을 때, URI를 받아오기
			String cmd=request.getRequestURI();
			
			//URI에서 key값으로 사용할 list(혹은 insert 등)만 추출
			cmd=cmd.substring(request.getContextPath().length()+1, cmd.lastIndexOf("."));
			
			//key값에 맞는 model을 불러와서 model 생성
			//==> model(ListModel.java)의 멤버변수, 메서드 활용 가능
			Model model=(Model) clsMap.get(cmd);
			
			//model에서 반환하는 주소 String 담기
			String jsp=model.handlerRequest(request, response);
			
			//jsp에 담기는 값이 .jsp로 끝나지 않는 경우를 대비하여 temp파일로 확장자명 받아서 비교
			String temp=jsp.substring(jsp.lastIndexOf(".")+1);
			if(temp.equals("do")){
				//.jsp가 아닌경우는 다시 jsp로 보내주기
				response.sendRedirect(jsp);
			}else{
				//.jsp인 경우는 .jsp의 값을 .do로 포워딩 해오기 ==> 아마 보안을 위해 .jsp노출 X
				RequestDispatcher rd=request.getRequestDispatcher(jsp);
				rd.forward(request, response);
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

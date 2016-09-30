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

	//beans.xml�� ������ model���� �����ϱ� ���� �ؽø� ����
	private Map clsMap=new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try{
			//beans�� �о���� ���� �ּ� �� �ޱ�
			String path=config.getInitParameter("beansLocation");
			System.out.println(path);
			//�ּҰ��� ����ϱ����� build ����(new instance�� ���� ��??)
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			
			//�ּ� ���� �Ľ��Ͽ� beans.xml�� ���� �ҷ����� ==> DOM��ü Ȱ��
			Document doc=db.parse(new File(path));
			
			//DOM��ü�� Ȱ���ϱ� ���� root����
			Element root=doc.getDocumentElement();
			
			//DOM���� node(bean)�� �±׳������� �о list�� ����
			NodeList list=root.getElementsByTagName("bean");
			
			//list�� ����� ����(bean) �ҷ�����
			for(int i=0;i<list.getLength();i++){
				//bean�� Element�� ����
				Element elm=(Element) list.item(i);
				
				//���� bean���� id�� class�� �޾ƿ���
				String id=elm.getAttribute("id");
				String cls=elm.getAttribute("class");
				
				//cls�� model�� ȣ���Ͽ� class���Ϸ� ����				
				Class clsName=Class.forName(cls);
				
				//�о�� class������ ��ü�� �޾Ƽ� �޸𸮿� �ø���
				//==>�޸𸮿� �ö󰡸� �ؽð��� �Ҵ��
				//==>�ش� class����(ListModel.java)�� ��� ���� �޼ҵ� Ȱ�� ����
				Object obj=clsName.newInstance();
				System.out.println(obj);
				//������ �ؽð��� �ؽø����� ����
				clsMap.put(id, obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//URL�� ȣ��Ǿ��� ��, URI�� �޾ƿ���
			String cmd=request.getRequestURI();
			
			//URI���� key������ ����� list(Ȥ�� insert ��)�� ����
			cmd=cmd.substring(request.getContextPath().length()+1, cmd.lastIndexOf("."));
			
			//key���� �´� model�� �ҷ��ͼ� model ����
			//==> model(ListModel.java)�� �������, �޼��� Ȱ�� ����
			Model model=(Model) clsMap.get(cmd);
			
			//model���� ��ȯ�ϴ� �ּ� String ���
			String jsp=model.handlerRequest(request, response);
			System.out.println(jsp);
			
			//jsp�� ���� ���� .jsp�� ������ �ʴ� ��츦 ����Ͽ� temp���Ϸ� Ȯ���ڸ� �޾Ƽ� ��
			String temp=jsp.substring(jsp.lastIndexOf(".")+1);
			
			//?page= or no ���� �Ķ���� ���� �ѹ� �� �߶��ֱ� ���ؼ� 
			if(!temp.equals("do") && !temp.equals("jsp")){
				temp=temp.substring(0, temp.lastIndexOf("?"));
			}
			
			if(temp.equals("do")){
				//.jsp�� �ƴѰ��� �ٽ� jsp�� �����ֱ�
				response.sendRedirect(jsp);
			}else{
				//.jsp�� ���� .jsp�� ���� .do�� ������ �ؿ��� ==> �Ƹ� ������ ���� .jsp���� X
				RequestDispatcher rd=request.getRequestDispatcher(jsp);
				rd.forward(request, response);
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

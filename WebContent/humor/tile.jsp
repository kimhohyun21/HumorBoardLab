<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ÿ�� ����</title>
<link rel="stylesheet" type="text/css" href="humor/table2.css">
</head>
<body>
   <center>
   	<div id="header">
		<h3>HUMOR BOARD</h3>
	</div>
	<div id="article">
		<table width="1500" id="table_content">
		   <tr height="200">
		      <c:forEach var="dto" begin="0" end="4" step="1" items="${list }">
		      <td id="tiletd">
		         <table>
		            <tr height="100" class="tdimg">
		               <td>
		                  <a href="humor/content.jsp">${dto.subject }</a>
		                  <c:if test="${dto.dbday==today }">
		                          <sup><img src="humor/img/icon_new (2).gif"></sup>
		                        </c:if>   
		               </td>
		            </tr>
		            <tr height="60"> 
		               <td align="right" >-${dto.name }-</td>
		            </tr>
		            <tr height="40">
		               <td>
		                  <img src="humor/img/like.png" width="35" height="35">
		               ${dto.hot }</td>
		            </tr>
		         </table>
		      </td>
		      </c:forEach>
		   </tr>
		   <tr height="200">
		      <c:forEach var="dto" begin="5" end="9" step="1" items="${list }">
		      <td width="300" id="tiletd">
		         <table>
		            <tr height="100" class="tdimg">
		               <td>
		                  <a href="humor/content.jsp">${dto.subject }</a>
		                  <c:if test="${dto.dbday==today }">
		                  	<sup><img src="humor/img/icon_new (2).gif"></sup>
		                  </c:if>   
		               </td>
		            </tr>
		            <tr height="60">
		               <td align="right">-${dto.name }-</td>
		            </tr>
		            <tr height="40">
		               <td>
		                  <img src="humor/img/like.png" width="35" height="35">   
		               	  ${dto.no }
		               </td>
		            </tr>
		         </table>
		      </c:forEach>
		   </tr>
		</table>
		
		<table id="button_table" width="1500">
	      <tr>
	         <td align="center">
	         <!-- 
	            [1][2][3][4][5][6]
	            fp         tp   
	          -->
	          <a href="tile.do?page=1">
	             <img src="humor/img/begin.gif">
	          </a>
	          <!-- �⺻�����δ� 5������ ������ ������ �̵�, ó�� ��ϰ� ������ ��Ͽ����� 1�������� �̵� -->
	         <c:if test="${curPage>block }">
	         	<a href="tile.do?page=${fromPage-1}">
	            	<img src="humor/img/prev.gif">                   
	            </a>
	         </c:if>   
	         <c:if test="${curPage<=block }">
	            <a href="tile.do?page=${curPage>1 ? curPage-1 : 1 }">
	               <img src="humor/img/prev.gif">                   
	            </a>
	         </c:if>
	         <c:forEach var="i" begin="${fromPage }" end="${toPage }">
	             [
	            <c:if test="${curPage==i }">
	               <font color="red">
	                  ${i }
	               </font>                
	            </c:if>
	            <c:if test="${curPage!=i }">
	               	<a href="tile.do?page=${i }">
	               		${i }
	               	</a>
	            </c:if>   
	             ]
	         </c:forEach>
	         <c:if test="${toPage<totalPage }">
	            <a href="tile.do?page=${toPage+1 }">
	            	<img src="humor/img/next.gif">                   
	            </a>
	         </c:if>
	         <c:if test="${toPage>=totalPage }">
	            <a href="tile.do?page=${curPage<totalPage ? curPage+1 : totalPage }">
	            	<img src="humor/img/next.gif">                   
	            </a>
	         </c:if>
	         <a href="tile.do?page=${totalPage }">
	           	<img src="humor/img/end.gif">
	         </a>
	         &nbsp;
	         ${curPage }page / ${totalPage }page
	         </td>
	         <td align="right">
	            <a href="humor/insert.jsp"><img src="humor/img/insert.png" width="30" height="30"></a>
	            <a href="humor/list.jsp"><img src="humor/img/list.png" width="30" height="30"></a>
	         </td>
	      </tr>
	   </table>
	</div>
    <div id="footer">
		<span>&copy;2016 Shin Eun Hye, Kim Ho Hyun, Park Jung Hwan, Jun Jin Tae.</span>
	</div>   
   </center>
</body>
</html>


























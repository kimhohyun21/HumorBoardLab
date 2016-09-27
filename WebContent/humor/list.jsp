<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>게시판 리스트</title>
	<link rel="stylesheet" type="text/css" href="humor/table.css">
</head>
<body>
	<div align="center">
		<h3>목록보기</h3>
		<table class="button_table">
			<tr>
				<td>
					<a href="insert.do">
						<img alt="write button" src="humor/img/btn_write.gif">
					</a>
				</td>
			</tr>
		</table>
		<table id="content_table">
			<tr>
				<th width="10%">번호</th>
				<th width="45%">제목</th>
				<th width="15%">이름</th>
				<th width="20%">작성일</th>
				<th width="10%">조회수</th>				
			</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td width="10%">${dto.no }</td>
				<td width="45%" id="left_td">
				<!-- group_tab : 몇 번째 단계의 답변인지 나타내는 컬럼
					
					 새로운 글      		   0
					   |	
					   --답변 1		   1
					   	  |
					   	  -- 답변 1의 답변   2						
				 -->
				<c:if test="${dto.group_tab!=0 }">
					<c:forEach var="blank" begin="1" end="${dto.group_tab }">
						&nbsp;
					</c:forEach>
					<img src="humor/img/icon_reply.gif">
				</c:if>
				<c:if test="${dto.subject==msg }">
					<font color="red">${dto.subject }</font>
				</c:if>
				<c:if test="${dto.subject!=msg }">
					<a href="content.do?no=${dto.no }&page=${curPage}">
						${dto.subject }
					</a>
				</c:if>					
				<c:if test="${dto.dbday==today }">
					<sup><img src="humor/img/icon_new (2).gif"></sup>
				</c:if>	
				</td>
				<td width="15%">${dto.name }</td>
				<td width="20%">${dto.regdate }</td>
				<td width="10%">${dto.hit }</td>		
			</tr>
		</c:forEach>
		</table>
		<table class="button_table">
			<tr>
				<td align="right">
				<!-- 
					[1][2][3][4][5][6]
					fp			tp	
				 -->
			 	<a href="list.do?page=1">
			 		<img src="humor/img/begin.gif">
			 	</a>
			 	<!-- 기본적으로는 5페이지 단위로 페이지 이동, 처음 블록과 마지막 블록에서만 1페이지씩 이동 -->
				<c:if test="${curPage>block }">
					<a href="list.do?page=${fromPage-1}">
						<img src="humor/img/prev.gif">				 		
				 	</a>
				</c:if>	
				<c:if test="${curPage<=block }">
					<a href="list.do?page=${curPage>1 ? curPage-1 : 1 }">
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
				 		<a href="list.do?page=${i }">
				 			${i }
				 		</a>
				 	</c:if>	
				 	]
				</c:forEach>
				<c:if test="${toPage<totalPage }">
					<a href="list.do?page=${toPage+1 }">
						<img src="humor/img/next.gif">				 		
				 	</a>
				</c:if>
				<c:if test="${toPage>=totalPage }">
					<a href="list.do?page=${curPage<totalPage ? curPage+1 : totalPage }">
						<img src="humor/img/next.gif">				 		
				 	</a>
				</c:if>
				<a href="list.do?page=${totalPage }">
			 		<img src="humor/img/end.gif">
			 	</a>
			 	&nbsp;
			 	${curPage }page / ${totalPage }page 
				</td>
			</tr>
		</table>	
	</div>
</body>
</html>
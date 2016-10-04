<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세 보기</title>
<link rel="stylesheet" type="text/css" href="humor/table.css">
</head>
<body>
	<center>
		<div id="header">
			<h3><a href="cover.do" class="h3">HUMOR BOARD</a></h3>
		</div>
		<div id="article">
			<table width="50%" class="button_table">
				<tr>
					<td align="center">
						<span class="btn_title">상세보기</span>
					</td>
				</tr>
			</table>
			<table id="content_table" width="50%">
				<tr>
					<th width="20%">번호</th>
					<td width="30%">${dto.no }</td>
					
					<th width="20%">작성일</th>
					<td width="30%">${dto.regdate }</td>
				</tr>
				<tr>
					<th width="20%">이름</th>
					<td width="30%">${dto.name }</td>
					
					<th width="20%">조회수</th>
					<td width="30%">${dto.hit }</td>
				</tr>
				<tr>
					<th width="20%">제목</th>
					<td class="left_td" colspan="3">${dto.subject }</td>
				</tr>
				<tr>
					<td colspan="4" id="cont">
						<pre>${dto.content }</pre>
					</td>
				</tr>
				<tr>
					<td class="left_td">
						<a href="hot.do?page=${page }&no=${dto.no }&list=${plist }&fs=${fs }&fi=${fi }">
		                	<img src="humor/img/like.png" class="btn_icon">   
		                </a>
		            	<span class="text_block valign">${dto.hot }</span>
		            </td>
					<td colspan="3" class="right_td">
						<a href="reply.do?no=${dto.no }&page=${page }">
							<img src="humor/img/btn_reply.png" title="리플" class="btn_icon">
							<span class="text_block valign">리플</span>
						</a>&nbsp;&nbsp;
						<a href="update.do?no=${dto.no }&page=${page }&list=${plist }&fs=${fs }&fi=${fi }">
							<img src="humor/img/btn_modify.png" title="수정" class="btn_icon">
							<span class="text_block valign">수정</span>
						</a>						
						&nbsp;&nbsp;
						<a href="delete.do?no=${dto.no }&page=${page }&list=${plist }&fs=${fs }&fi=${fi }">
							<img src="humor/img/btn_delete.png" title="삭제" class="btn_icon">
							<span class="text_block valign">삭제</span>
						</a>
						&nbsp;&nbsp;
						<c:set var="url" value="list.do?page=${page }&fs=${fs }&fi=${fi }"></c:set>
						<c:if test="${plist==1 }">
							<c:set var="url" value="tile.do?page=${page }"></c:set>
						</c:if>
						<a href="${url }">						
							<img src="humor/img/btn_list.png" title="리스트" class="btn_icon">
							<span class="text_block valign">리스트</span>
						</a>
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

















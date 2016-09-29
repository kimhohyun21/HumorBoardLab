<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상세 보기</title>
<link rel="stylesheet" type="text/css" href="humor/table.css">
</head>
<body>
	<center>
		<div id="header">
			<h3><a href="cover.do" class="h3">HUMOR BOARD</a></h3>
		</div>
		<div id="article">
			<table id="content_table" width="1000px">
				<tr class="cont_tr">
					<th width="20%">번호</th>
					<td width="30%">${dto.no }</td>
					
					<th width="20%">작성일</th>
					<td width="30%">${dto.regdate }</td>
				</tr>
				<tr class="cont_tr">
					<th width="20%">이름</th>
					<td width="30%">${dto.name }</td>
					
					<th width="20%">조회수</th>
					<td width="30%">${dto.hit }</td>
				</tr>
				<tr class="cont_tr">
					<th width="20%">제목</th>
					<td id="left_td" colspan="3">${dto.subject }</td>
				</tr>
				<tr>
					<td colspan="4" id="cont">${dto.content }</td>
				</tr>
			</table>			
			<table class="button_table" width="1000px">
				<tr>
					<td align="right">
						<a href="reply.do?no=${dto.no }&page=${page}">
							<img src="humor/img/btn_reply.png" title="리플" class="btn_icon">
						</a>&nbsp;&nbsp;
						<a href="modify.do?no=${dto.no }&page=${page}">
							<img src="humor/img/btn_modify.png" title="수정" class="btn_icon">
						</a>						
						&nbsp;&nbsp;
						<a href="delete.do?no=${dto.no }&page=${page}">
							<img src="humor/img/btn_delete.png" title="삭제" class="btn_icon">
						</a>
						&nbsp;&nbsp;
						<a href="list.do?&page=${page}">
							<img src="humor/img/btn_list.png" title="리스트" class="btn_icon">
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

















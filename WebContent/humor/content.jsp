<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ����</title>
<link rel="stylesheet" type="text/css" href="humor/table.css">
</head>
<body>
	<center>
		<h3>����</h3>
		<table id="content_table">
			<tr height="27">
				<th width="20%">��ȣ</th>
				<td width="30%" align="center">${dto.no }</td>
				
				<th width="20%">�ۼ���</th>
				<td width="30%" align="center">${dto.regdate }</td>
			</tr>
			<tr height="27">
				<th width="20%">�̸�</th>
				<td width="30%" align="center">${dto.name }</td>
				
				<th width="20%">��ȸ��</th>
				<td width="30%" align="center">${dto.hit }</td>
			</tr>
			<tr height="27">
				<th width="20%">����</th>
				<td width="30%" align="left" colspan="3">${dto.subject }</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="4" align="left" valign="top" height="200">
					<pre>${dto.content }</pre>
				</td>
			</tr>
		</table>
		
		<table border="0">
			<tr>
				<td align="right">
					<a href="reply.do?no=${dto.no }&page=${page}">
						<img src="humor/img/btn_reply.gif">
					</a>
					<img src="humor/img/btn_modify.gif">
					<a href="delete.do?no=${dto.no }&page=${page}">
						<img src="humor/img/btn_delete.gif">
					</a>
					<a href="list.do?&page=${page}">
						<img src="humor/img/btn_list.gif">
					</a>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>

















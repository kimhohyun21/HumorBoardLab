<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>�����ϱ�</title>
	<link rel="stylesheet" type="text/css" href="humor/table.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#sendBtn').click(function(){
				submit();
			});
			
			$('input').keypress(function(key) {
				if(key.keyCode == 13){
					submit();
				}
			});
			
			submit=function(){
				var name=$('#name').val();
				if(name.trim()==""){
					$('#name').focus();
					alert('�̸��� �Է��ϼ���.');
					$('#name').val("");
					return;
				}
				var subject=$('#subject').val();
				if(subject.trim()==""){
					$('#subject').focus();
					alert('������ �Է��ϼ���.');
					$('#subject').val("");
					return;
				}
				var content=$('#content').val();
				if(content.trim()==""){
					$('#content').focus();
					alert('������ �Է��ϼ���.');
					$('#content').val("");
					return;
				}				
				var pwd=$('#pwd').val();
				if(pwd.trim()==""){
					$('#pwd').focus();
					alert('��й�ȣ�� �Է��ϼ���.');
					$('#pwd').val("");
					return;
				}
				$('#frm').submit();
			};
		});
	</script>
</head>
<body>
	<div align="center">
		<div id="header">
			<h3><a href="cover.do" class="h3">HUMOR BOARD</a></h3>
		</div>
		<div id="article">
			<table width="1000px" class="button_table">
				<tr>
					<td align="center">
						<span class="btn_title">�����ϱ�</span>
					</td>
				</tr>
			</table>
			<form action="update_ok.do?page=${page }&list=${plist }" method="post" name="frm" id="frm">
				<table id="insert_table" width="1000px">
					<tr>
						<th width="20%">�̸�</th>
						<td>
							<input type="hidden" value="${dto.no }" name="no" >
							<input type="text" size="15" value="${dto.name }" name="name" id="name">
						</td>
					</tr>
					<tr>
						<th width="20%">����</th>
						<td>
							<input type="text" size="50" value="${dto.subject }" name="subject" id="subject">
						</td>
					</tr>
					<tr >
						<th width="20%">����</th>
						<td>
							<textArea cols="70" rows="20" name="content" id="content">${dto.content }</textArea>
						</td>
					</tr>
					<tr>
						<th width="20%">��й�ȣ</th>
						<td>
							<input type="password" size="15" name="pwd" id="pwd">
						</td>
					</tr>
				</table>
				<table class="button_table" width="1000px">
					<tr>
						<td align="center">
							<input type="button" value="�۾���" id="sendBtn" class="btn_normal2">
							<input type="reset" value="���" onclick="javascript:history.back()" class="btn_normal2">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="footer">
			<span>&copy;2016 Shin Eun Hye, Kim Ho Hyun, Park Jung Hwan, Jun Jin Tae.</span>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>글쓰기</title>
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
					alert('이름을 입력하세요.');
					$('#name').val("");
					return;
				}
				var subject=$('#subject').val();
				if(subject.trim()==""){
					$('#subject').focus();
					alert('제목을 입력하세요.');
					$('#subject').val("");
					return;
				}
				var content=$('#content').val();
				if(content.trim()==""){
					$('#content').focus();
					alert('내용을 입력하세요.');
					$('#content').val("");
					return;
				}
				var pwd=$('#pwd').val();
				if(pwd.trim()==""){
					$('#pwd').focus();
					alert('비밀번호를 입력하세요.');
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
			<table width="1500px" class="button_table">
				<tr>
					<td align="center">
						<font size="5px" color="gray">[ 글쓰기 ]</font>
					</td>
				</tr>
			</table>
			<form action="insert_ok.do?list=${plist }" method="post" name="frm" id="frm">
				<table id="insert_table" width="1000px">
					<tr>
						<th width="20%">이름</th>
						<td>
							<input type="text" size="15" name="name" id="name">
						</td>
					</tr>
					<tr>
						<th width="20%">제목</th>
						<td>
							<input type="text" size="50" name="subject" id="subject">
						</td>
					</tr>
					<tr >
						<th width="20%">내용</th>
						<td>
							<textArea cols="70" rows="20" name="content" id="content"></textArea>
						</td>
					</tr>
					<tr>
						<th width="20%">비밀번호</th>
						<td>
							<input type="password" size="15" name="pwd" id="pwd">
						</td>
					</tr>
				</table>
				<table class="button_table" width="1000px">
					<tr>
						<td align="center">
							<input type="button" value="글쓰기" id="sendBtn" class="btn_normal2">
							<input type="reset" value="취소" onclick="javascript:history.back()" class="btn_normal2">
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
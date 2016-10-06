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
			$('input').keypress(function(key) {
				if(key.keyCode == 13){
					$('#frm').submit();
				}
			});
		});
	</script>
</head>
<body>
	<center>
		<div id="header">
			<h3><a href="cover.do" class="h3">HUMOR BOARD</a></h3>
		</div>
		<div id="article">
			<table class="button_table del_align" width="300px">
				<tr>
					<td>
						<font size="5px" color="gray">[ �����ϱ� ]</font>
					</td>
				</tr>
			</table>
			<form action="delete_ok.do" method="post" id="frm">
			<table class="del_table del_align" width="300px">
				<tr>
					<th>��й�ȣ</th>
				</tr>
				<tr>
					<td>
						<input type="password" name="pwd" size="13">
						<input type="hidden" name="no" value="${no }">
						<input type="hidden" name="page" value="${page }">
						<input type="hidden" name="list" value="${plist }">
						<input type="hidden" name="fs" value="${fs }">
						<input type="hidden" name="fi" value="${fi }">
					</td>
				</tr>
			</table>
			<table class="button_table del_align" width="300px">
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="����" class="btn_normal2">
						<input type="button" value="���" onclick="javascript:history.back()" class="btn_normal2">
					</td>
				</tr>
			</table>
		</form>
		</div>
		<div id="footer">
			<span>&copy;2016 Shin Eun Hye, Kim Ho Hyun, Park Jung Hwan, Jun Jin Tae.</span>
		</div>
	</center>	
</body>
</html>

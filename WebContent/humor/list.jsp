<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>���� �Խ���</title>
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
				var $fs=$("#fs").val();
				if($fs.trim()==""){
					alert("�˻� �׸��� �������ּ���.");
					$("#fs").focus();
					return;
				}
				var $fi=$("#fi").val();
				if($fi.trim()==""){
					alert("�˻�� �Է� ���ּ���.");
					$("#fi").focus();
					return;
				}
				$("#frm").submit();
			}
		});
	</script>	
</head>
<body>
	<div align="center">
		<div id="header">
			<h3><a href="cover.do" class="h3">HUMOR BOARD</a></h3>
		</div>
		<div id="article">
			<table width="80%" class="button_table">
				<tr>
					<td width="50%">
						<a href="tile.do?page=${curPage>tileTotal ? tileTotal : curPage }">
							<button class="btn_list">
								<img alt="tile" title="Ÿ����" src="humor/img/tile_icon.png" class="btn_icon">
								<span class="text_block valign">Ÿ���� ����</span>
							</button>
						</a>
						<a href="list.do?page=${curPage }">
							<button class="btn_list click">
								<img alt="list" title="����Ʈ��" src="humor/img/list_icon.png" class="btn_icon">
								<span class="text_block valign">����Ʈ�� ����</span>
							</button>
						</a>
					</td>
					<td class="find_td">
						<form action="list.do" method="post" name="frm" id="frm">
							<select name="fs" id="fs">
								<option value="">����</option>
								<option value="name">�̸�</option>
								<option value="subject">����</option>
								<option value="content">����</option>
							</select>
							<input type="text" size="20px" name="fi" id="fi">							
							<input type="button" value="�˻�" id="sendBtn" class="btn_normal">
						</form>
					</td>
				</tr>
			</table>
			<table id="list_table" width="80%">
				<tr>
					<th width="5%">��ȣ</th>
					<th width="45%">����</th>
					<th width="10%">�̸�</th>
					<th width="20%">�ۼ���</th>
					<th	width="15%">��õ��</th>
					<th width="5%">��ȸ��</th>				
				</tr>
			<c:forEach var="dto" items="${list }">
			<c:set var="tr_id" value="normal"></c:set>
			<c:if test="${dto.subject==msg }">
				<c:set var="tr_id" value="delete_tr"></c:set>
			</c:if>	
				<tr id="${tr_id }">
					<td width="5%">${dto.no }</td>
					<td width="45%" id="left_td">
					<c:if test="${dto.group_tab!=0 }">
						<c:forEach var="blank" begin="1" end="${dto.group_tab }">
							&nbsp;
						</c:forEach>
						<img src="humor/img/icon_reply.gif">
					</c:if>
					<c:if test="${dto.subject==msg }">
						${dto.subject }
					</c:if>
					<c:if test="${dto.subject!=msg }">
						<a href="content.do?no=${dto.no }&page=${curPage}&list=${plist }&fs=${fs }&fi=${fi }">
							${dto.subject }
						</a>
						<c:if test="${dto.dbday==today }">
							<sup><img src="humor/img/icon_new (2).gif"></sup>
						</c:if>	
					</c:if>					
					</td>
					<td width="10%">${dto.name }</td>
					<td width="20%">${dto.regdate }</td>
					<td width="15%">
						<!-- progress value="${dto.hot }" max="500"></progress -->					
						<div id="star_rating" class="${dto.no }"></div>	
						<script type="text/javascript">
							$(function(){
								var $hot=${dto.hot };
								var $top=((Math.floor(($hot/100)*10))*10);
								if($top>100){$top=100};
								var $position={
									backgroundPosition: "left "+$top+"%"
								};
								$(".${dto.no }").css($position);
								console.log($position);
							});
						</script>	
					</td>
					<td width="5%">${dto.hit }</td>		
				</tr>
			</c:forEach>
			</table>
			<table class="button_table" width="80%">
				<tr>
					<td width="90%" align="center">
					      <!-- 
					  [1][2][3][4][5][6]
					  fp         tp   
					-->
					<a href="list.do?page=1">
					   <button class="btn_normal2">&lt;&lt; first</button>  
					</a>
					<!-- �⺻�����δ� 5������ ������ ������ �̵�, ó�� ��ϰ� ������ ��Ͽ����� 1�������� �̵� -->
					<c:if test="${curPage>block }">
					<a href="list.do?page=${fromPage-1}&fs=${fs }&fi=${fi }">
					  	 <button class="btn_normal2">&lt; prev</button>                    
					  </a>
					</c:if>   
					<c:if test="${curPage<=block }">
					  <a href="list.do?page=${curPage>1 ? curPage-1 : 1 }&fs=${fs }&fi=${fi }">
					     <button class="btn_normal2">&lt; prev</button>                   
					  </a>
					</c:if>
					<c:forEach var="i" begin="${fromPage }" end="${toPage }">
					  <c:if test="${curPage==i }">
					     <button class="btn_select">${i }</button>               
					  </c:if>
					  <c:if test="${curPage!=i }">
					     	<a href="list.do?page=${i }&fs=${fs }&fi=${fi }">
					     		<button class="btn_normal">${i }</button>
					     	</a>
					  </c:if>   
					</c:forEach>
					<c:if test="${toPage<totalPage }">
					  <a href="list.do?page=${toPage+1 }&fs=${fs }&fi=${fi }">
					  	<button class="btn_normal2">next &gt;</button>                    
					  </a>
					</c:if>
					<c:if test="${toPage>=totalPage }">
					  <a href="list.do?page=${curPage<totalPage ? curPage+1 : totalPage }&fs=${fs }&fi=${fi }">
					  	<button class="btn_normal2">next &gt;</button>                  
					  </a>
					</c:if>
					<a href="list.do?page=${totalPage }">
					  	<button class="btn_normal2">last &gt;&gt;</button>   
					</a>
					&nbsp;
					<span class="text_block">${curPage }page / ${totalPage }page</span>
					</td>
					<td class="right_td">
						<a href="insert.do?list=${plist }">
							<img src="humor/img/btn_write.png" title="�۾���" class="btn_icon">
					        <span class="text_block valign">�۾���</span>
						</a>
					</td>
				</tr>
			</table>	
		</div>		
		<div id="footer">
			<span>&copy;2016 Shin Eun Hye, Kim Ho Hyun, Park Jung Hwan, Jun Jin Tae.</span>
		</div>
	</div>
</body>
</html>
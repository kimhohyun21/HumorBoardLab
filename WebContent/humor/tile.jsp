<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>유머 게시판</title>
	<link rel="stylesheet" type="text/css" href="humor/table.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#sendBtn").click(function(){
				var $fs=$("#fs").val();
				if($fs.trim()==""){
					alert("검색 항목을 선택해주세요.");
					$("#fs").focus();
					return;
				}
				var $fi=$("#fi").val();
				if($fi.trim()==""){
					alert("검색어를 입력 해주세요.");
					$("#fi").focus();
					return;
				}
				$("#frm").submit();
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
		<table width="1500px" class="button_table">
			<tr>
				<td width="50%">
					<a href="tile.do?page=${curPage }">
						<button class="btn_list click">
							<img alt="tile" title="타일형" src="humor/img/tile_icon.png" class="btn_icon">
							<span class="text_block valign">타일형 보기</span>
						</button>
					</a>
					<a href="list.do?page=${curPage }">
						<button class="btn_list">
							<img alt="list" title="리스트형" src="humor/img/list_icon.png" class="btn_icon">
							<span class="text_block valign">리스트형 보기</span>
						</button>
					</a>
				</td>
				<td class="find_td">
					<form action="list.do" method="post" name="frm" id="frm">
						<select name="fs" id="fs">
							<option value="">선택</option>
							<option value="name">이름</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" size="20px" name="fi" id="fi">							
						<input type="button" value="검색" id="sendBtn" class="btn_normal">
					</form>
				</td>
			</tr>
		</table>
		<table width="1500" id="table_content">
		   <tr height="200">
		      <c:forEach var="dto" begin="0" end="4" step="1" items="${list }">	
		      <td id="tiletd" onclick="location.href='content.do?page=${curPage }&no=${dto.no }&list=${plist }'">     
		         <table>
		            <tr height="100" class="tdimg">
		               <td>
		               	  <a href="content.do?page=${curPage }&no=${dto.no }&list=${plist }">	
		                  	${dto.subject }
		                  </a>
		                  <c:if test="${dto.dbday==today }">
		                          <sup><img src="humor/img/icon_new (2).gif"></sup>
		                  </c:if>   
		               </td>
		            </tr>
		            <tr height="60"> 
		               <td align="center" >${dto.name }</td>
		            </tr>
		            <tr height="40">
		               <td align="center">
		                  <img src="humor/img/like.png" width="35" height="35">
		               	  <span class="text_block valign">${dto.hot }</span>
		               </td>
		            </tr>  
		         </table>
		      </td> 
		      </c:forEach>
		   </tr>
		   <tr height="200">
		      <c:forEach var="dto" begin="5" end="9" step="1" items="${list }">
		      <td id="tiletd" onclick="location.href='content.do?page=${curPage }&no=${dto.no }&list=${plist }'">
		         <table>
		            <tr height="100" class="tdimg">
		               <td>
		                  <a href="content.do?page=${curPage }&no=${dto.no }&list=${plist }">	
		                  	${dto.subject }
		                  </a>
		                  <c:if test="${dto.dbday==today }">
		                  	<sup><img src="humor/img/icon_new (2).gif"></sup>
		                  </c:if>   
		               </td>
		            </tr>
		            <tr height="60">
		               <td align="center">${dto.name }</td>
		            </tr>
		            <tr height="40">
		               <td align="center">
		                  <img src="humor/img/like.png" width="35" height="35">   
		               	  <span class="text_block valign">${dto.hot }</span>
		               </td>
		            </tr>
		         </table>
		      </c:forEach>
		   </tr>
		</table>
		
		<table class="button_table" width="1500">
	      <tr>
	         <td width="90%" align="center">
	         <!-- 
	            [1][2][3][4][5][6]
	            fp         tp   
	          -->
	          <a href="tile.do?page=1">
	             <button class="btn_normal2">&lt;&lt; first</button>  
	          </a>
	          <!-- 기본적으로는 5페이지 단위로 페이지 이동, 처음 블록과 마지막 블록에서만 1페이지씩 이동 -->
	          <c:if test="${curPage>block }">
	         	 <a href="tile.do?page=${fromPage-1}">
	            	 <button class="btn_normal2">&lt; prev</button>                    
	             </a>
	          </c:if>   
	          <c:if test="${curPage<=block }">
	             <a href="tile.do?page=${curPage>1 ? curPage-1 : 1 }">
	                <button class="btn_normal2">&lt; prev</button>                   
	             </a>
	          </c:if>
	          <c:forEach var="i" begin="${fromPage }" end="${toPage }">
	             <c:if test="${curPage==i }">
	                <button class="btn_select">${i }</button>               
	             </c:if>
	          <c:if test="${curPage!=i }">
	             <a href="tile.do?page=${i }">
	             	<button class="btn_normal">${i }</button>
	             </a>
	          </c:if>   
	          </c:forEach>
	          <c:if test="${toPage<totalPage }">
	             <a href="tile.do?page=${toPage+1 }">
	            	<button class="btn_normal2">next &gt;</button>                    
	             </a>
	          </c:if>
	          <c:if test="${toPage>=totalPage }">
	             <a href="tile.do?page=${curPage<totalPage ? curPage+1 : totalPage }">
	            	<button class="btn_normal2">next &gt;</button>                  
	             </a>
	          </c:if>
	          <a href="tile.do?page=${totalPage }">
	             <button class="btn_normal2">last &gt;&gt;</button>   
	          </a>
	          &nbsp;
	          <span class="text_block">${curPage }page / ${totalPage }page</span>
	          </td>
	          <td align="center">
	             <a href="insert.do?list=${plist }">
	            	<img src="humor/img/btn_write.png" title="글쓰기" class="btn_icon">
	            	<span class="text_block valign">글쓰기</span>
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


























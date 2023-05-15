<%@page import="kr.or.ddit.NaverWorkCheckParsing"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="kr.or.ddit.NaverWorkCheckParsing"%>
	
<style>
	.re_green{ color:green;}
	.re_red{ color:red;}
	.re_purple{	color:purple;}
	
	#result{
		width:400px;
		height:300px;
		border:1px solid gray;
		overflow:auto; 
	}
</style>

<h3>네이버 맞춤법검사기 파싱 테스트 </h3>
<form action="parsing2" method="post">
	<textarea id="txt" placeholder="맞춤법 검사를 원하는 단어나 문장을 직접 입력 또는 붙여 넣기 해주세요." name="txt"
		rows="4" cols="54"></textarea>
	<br> <br> <input type="submit" value="검사하기">
</form>	<br>
<div id="result"></div>
<span class="re_red">맞춤법</span>
<span class="re_green">띄어쓰기</span>
<span class="re_purple">표준어 의심단어</span>
<h4>* 참고 : 저작권은 네이버(http://www.naver.com)에 있습니다. </h4>
<h4>*      불법사용이 아닌 파싱 공부용으로 참고하시기 바랍니다.</h4>
<%
	String result;
	request.setCharacterEncoding("utf-8");
	if (request.getParameter("txt") != null) {

		NaverWorkCheckParsing parsing = new NaverWorkCheckParsing();
		result = parsing.parsing(request.getParameter("txt"));
		%>
		<script>
		document.getElementById('txt').value = "<%=request.getParameter("txt")%>";
		document.getElementById('result').innerHTML = "<%=result%>";
		</script>
		<%
	} else {
		result = null;
		System.out.println("없음");
	}
%>>
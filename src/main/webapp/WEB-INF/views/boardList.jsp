<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든 글 목록</title>
</head>
<body>
	<h2>게시판 글 목록</h2>
	<hr>
	<!-- 위에 jstl 불러와야함 -->
	<!-- 파이썬 문법 : for bDto in bDtos: -->
	글번호 / 글쓴이 / 글제목 / 게시일 / 조회수 <br><br>
	<c:forEach var="bDto" items="${bDtos }">
	${bDto.bnum } / ${bDto.bname } / ${bDto.btitle } / ${bDto.bcontent } / ${bDto.bdate } / ${bDto.bhit } <br><br>
	</c:forEach>
</body>
</html>
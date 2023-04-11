<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기동물 정보 조회</title>
<link rel="stylesheet" href="/resource/css/style.css?" />
</head>
<body>
	<div class="root">
		<div style="text-align: center">
			<h1>
				유기동물 정보 조회 <small>(OPEN API 활용)</small>
			</h1>
			<div>총 ${total } 건의 유기동물정보가 존재합니다.</div>
			<c:forEach items="${datas }" var="obj">
				<div class="index_view">
					${obj.kindCd } | (${obj.specialMark })
					${obj.orgNm } ${obj.happenPlace } 
					<img src="${obj.filename }">
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
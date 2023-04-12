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
			<div class="animal_list">
				<form action="/index">
					<input type="date" name="bgnde" value="${param.bgnde }" /> ~ <input
						type="date" name="endde" value="${param.endde }" /> <select
						name="upr_cd">
						<option value="">전국</option>
						<option value="6110000">서울특별시</option>
						<option value="6260000">부산광역시</option>
						<option value="6270000">대구광역시</option>
						<option value="6280000">인천광역시</option>
						<option value="6290000">광주광역시</option>
						<option value="5690000">세종특별자치시</option>
						<option value="6300000">대전광역시</option>
						<option value="6310000">울산광역시</option>
						<option value="6410000">경기도</option>
						<option value="6420000">강원도</option>
						<c:forEach items="${sidos }" var="obj">
							<option value="${obj.orgCd }">${obj.orgdownNm }</option>
						</c:forEach>
					</select> <select name="upkind">
						<option value="" ${param.upkind eq '' ? 'selected' : '' }>전체</option>
						<option value="417000"
							${param.upkind eq '417000' ? 'selected' : '' }>개</option>
						<option value="422400"
							${param.upkind eq '422400' ? 'selected' : '' }>고양이</option>
						<option value="429900"
							${param.upkind eq '429900' ? 'selected' : '' }>기타</option>
					</select>
					<button type="submit">조회</button>
				</form>
			</div>
			<%-- 유기동물 정보 리스트 --%>
			<div>총 ${total } 건의 유기동물정보가 존재합니다.</div>
			<div class="homelessAnimals">
				<c:forEach items="${datas }" var="obj">
						<div>
							<div style="opacity: 0.8">
								<b>${obj.happenDt }</b>
								<hr color="#eeeeee" />
								<img src="${obj.filename }"/>
								<hr color="#eeeeee" />
								<span>${obj.kindCd } (${obj.specialMark })</span>
								<hr color="#eeeeee" />
								<span>${obj.orgNm } ${obj.happenPlace }</span>
							</div>
						</div>
				</c:forEach>
			</div>
		</div>
		<%-- 페이징처리 --%>
		<c:set var="currentPage"
			value="${empty param.pageNo ? 1 : param.pageNo }" />
		<div style="text-align: right">
			<%-- existPrev 처리 --%>
			<c:if test="${existPrev }">
				<c:url value="/" var="target">
						<c:param name="upkind" value="${param.upkind }" />
						<c:param name="upr_cd" value="${param.upr_cd }" />
						<c:param name="bgnde" value="${param.bgnde }" />
						<c:param name="endde" value="${param.endde }" />
						<c:param name="pageNo" value="${start-1 }" />
				</c:url>
				<a href="${target}">←</a>
			</c:if>
			<%-- existPrev 처리 끝 --%>
			<%-- 페이징처리 --%>
			<c:forEach var="p" begin="${start }" end="${last }">
				<c:url value="/" var="target">
						<c:param name="upkind" value="${param.upkind }" />
						<c:param name="upr_cd" value="${param.upr_cd }" />
						<c:param name="bgnde" value="${param.bgnde }" />
						<c:param name="endde" value="${param.endde }" />
						<c:param name="pageNo" value="${p }" />
				</c:url>
			<%-- 현재페이지 --%>
				<c:choose>
					<c:when test="${p eq currentPage }">
						<b style="color: fuchsia;">${p }</b>
					</c:when>
					<c:otherwise>
						<a href="${target }">${p }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<%-- 페이징처리끝 --%>
			<%-- existNext 처리 --%>
			<c:if test="${existNext }">
				<c:url value="/" var="target">
						<c:param name="upkind" value="${param.upkind }" />
						<c:param name="upr_cd" value="${param.upr_cd }" />
						<c:param name="pageNo" value="${param.pageNo }" />
						<c:param name="bgnde" value="${param.bgnde }" />
						<c:param name="endde" value="${param.endde }" />
					<c:param name="pageNo" value="${last + 1 }" />
				</c:url>
				<a href="${target }">→</a>
			</c:if>
		</div>
			<%-- existNext 처리끝 --%>

	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/style.css" />
</head>
<body>
	<div class="container mt-5">
		<h1 class="text-center mb-5">MY SPBBS</h1>
		<div class="row justify-content-center">
			<table class="bbsList table-hover">
				<colgroup>
					<col width="10%" />
					<col>
					<col width="20%" />
					<col width="20%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td class="text-center">${dto.num }</td>
							<td class="title">
								<c:forEach begin="1" end="${dto.s_indent }">
									&nbsp;&nbsp; - 
								</c:forEach>
								<a href="detail?num=${dto.num }">${dto.title }</a>
							</td>
							<td class="text-center">${dto.uname }</td>
							<td class="text-center">${dto.wdate }</td>
							<td class="text-center">${dto.hit }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="offset-md-3 col-md-6">
				<ul class="pagination justify-content-center">

					<c:if test="${pages.prev }">
						<li class="page-item disabled"><a href="#" class="page-link">이전</a></li>
					</c:if>

					<c:forEach begin="${pages.startPage }" end="${pages.endPage }" var="idx">
						<li class="page-item active"><a href="#" class="page-link">1</a></li>
					</c:forEach>

					<c:if test="${pages.next && pages.endPage > 0 }">
						<li class="page-item"><a href="#" class="page-link">다음</a></li>
					</c:if>
					
				</ul>
			</div>
			<div class="col-md-3">
				<div class="button-group">
					<a href="list" class="btn btn-primary">목록보기</a> <a href="write" class="btn btn-primary">글쓰기</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
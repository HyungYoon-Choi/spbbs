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
	<h1 class="text-center m-5">MY SPBBS</h1>
	<div class="container detail">
		<h3 class="py-5">${detail.title }</h3>

		<div class="d-flex border border-gray mb-5">
			<div class="col-md-4">
				<label>글쓴이</label> ${detail.uname }
			</div>
			<div class="col-md-4">
				<label>날짜</label> ${detail.wdate }
			</div>
			<div class="col-md-4">
				<label>조회수</label> ${detail.hit }
			</div>
		</div>

		<div class="datailview border border-gray p-5">${detail.content }</div>
	</div>
	<div class="container">
		<div class="row justify-content-end px-3 my-5 button group">
			<a href="list" class="btn btn-primary">목록보기</a> 
			<a href="write" class="btn btn-primary">글쓰기</a> 
			<a href="reply?num=${detail.num }" class="btn btn-success">답글달기</a> 
			<a href="update?num=${detail.num }" class="btn btn-danger">수정하기</a> 
			<a href="javascript:;" onclick="deleteConfirm(${detail.num})" class="btn btn-danger">삭제</a>
		</div>
	</div>
	<script>
    function deleteConfirm(num) {
        var check = confirm("정말로 삭제하시겠습니까?");
        if (check == true) {
            
            window.location.href = "del?num=" + num;
        	}
    }
	</script>
</body>
</html>
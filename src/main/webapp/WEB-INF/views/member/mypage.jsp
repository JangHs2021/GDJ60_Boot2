<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Modern Business - Start Bootstrap Template</title>
<!-- css, favicon -->
<c:import url="../temp/style.jsp"></c:import>
<!-- css, favicon -->
</head>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<!-- Navigation-->
        <c:import url="../temp/header.jsp"></c:import>
        <!-- Header-->
        
        <!-- Page Content-->
         <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                        <div class="col-lg-3">
                            <div class="d-flex align-items-center mt-lg-5 mb-4">
                                <img class="img-fluid rounded-circle" src="/images/user-1.jpg" alt="..." />
                                <div class="ms-3">
                                    <div class="fw-bold">${memberVO.name}</div>
                                    <div class="text-muted">News, Business</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <!-- Post content-->
                            <article>
                                <!-- Post header-->
                                <header class="mb-4">
                                    <!-- Post title-->
                                    <h1 class="fw-bolder mb-1">마이페이지</h1>
                                    <!-- Post meta content-->
                                    <div class="text-muted fst-italic mb-2">${memberVO.name}님, 환영합니다</div>
                                    <!-- Post categories-->
                                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">JAVA</a>
                                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">CSS</a>
                                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">JAVASCRIPT</a>
                                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">HTML</a>
                                    <a class="badge bg-secondary text-decoration-none link-light" href="#!">JSP</a>
                                </header>
                                <!-- Post content-->
                                <section class="mb-5">
                                    <h5>UserName : ${memberVO.userName}</h5> <br>
                                    <h5>Name : ${memberVO.name}</h5> <br>
                                    <h5>Email : ${memberVO.email}</h5> <br>
                                    <h5>Birth : ${memberVO.birth}</h5> <br>
                                    <img alt="" src="/images/winter.jpg">
                                </section>
                            </article>
                        </div>
                    </div>
                </div>
            </section>   
	</main>
	
	<!-- Footer -->
    <c:import url="../temp/footer.jsp"></c:import>
    <!-- Footer -->
</body>
</html>
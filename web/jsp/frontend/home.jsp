<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/3/2017
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main role="main">
    <!-- Main Carousel -->
    <section class="section background-dark">
        <div class="line">
            <div class="carousel-fade-transition owl-carousel carousel-main carousel-nav-white carousel-wide-arrows">
                <c:forEach var="one" items="${banner_list}" varStatus="roop">
                    <div class="item">
                        <div class="s-12 center">
                            <img src="${banner_file_path}${one.img}" alt="">
                            <div class="carousel-content">
                                <div class="padding-2x">
                                    <div class="s-12 m-12 l-8">
                                        <p class="text-white text-s-size-20 text-m-size-40 text-l-size-60 margin-bottom-40 text-thin text-line-height-1">
                                                ${one.title}</p>
                                        <p class="text-white text-size-16 margin-bottom-40">${one.content}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <!-- Section 1 -->
    <section class="section background-white">
        <div class="line">
            <div class="margin">
                <c:forEach var="one" items="${product_list}" varStatus="roop">
                    <div class="s-12 m-12 l-4 margin-bottom-20">
                        <h1 class="text-primary margin-bottom-20 text-size-50 text-thin text-line-height-1">
                            <i class="icon-eye icon2x"></i>${one[0].category_name}
                        </h1>
                        <a class="image-hover-zoom" href="/strongshine/web/frontend/product-info?product_id=${one[0].id}">
                            <img class="margin-bottom" src="${product_file_path}${one[0].img}" alt="">
                        </a>
                        <h2 class="text-thin">${one[0].name}</h2>
                        <p>${one[0].content}</p>
                        <a class="text-more-info text-primary-hover"
                           href="/strongshine/web/frontend/product-info?product_id=${one[0].id}"><s:message
                                code="read_more"/></a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <!-- Section 2 -->
    <section class="section background-primary text-center">
        <div class="line">
            <div class="s-12 m-10 l-8 center">
                <h2 class="headline text-thin text-s-size-30"><s:message code="company.about"/></h2>
                <h3 class="headline text-thin text-s-size-30">${about_list[0].title}</h3>
                <p class="text-size-20">${about_list[0].content}</p>
            </div>
        </div>
    </section>

    <!-- Section 3 -->
    <section class="section background-white">
        <div class="full-width text-center">
            <img class="center margin-bottom-30" style="margin-top: -210px;"
                 src="${about_file_path}${about_list[0].img}" alt="">
            <div class="line">
                <h2 class="headline text-thin text-s-size-30"><span class="text-primary">${about_list[1].title}</span>
                </h2>
                <p class="text-size-20 text-s-size-16 text-thin">${about_list[1].content}</p>
                <i class="icon-more_node_links icon2x text-primary margin-top-bottom-10"></i>
                <%--<p class="text-size-20 text-s-size-16 text-thin text-primary">Try resize your browser window</p>--%>
            </div>
        </div>
    </section>
    <hr class="break margin-top-bottom-0">

    <!-- Section 4 -->
    <%--<section class="section background-white">
        <div class="line">
            <h2 class="text-thin headline text-center text-s-size-30 margin-bottom-50">From Our <span
                    class="text-primary">Blog</span></h2>
            <div class="carousel-default owl-carousel carousel-wide-arrows">
                <div class="item">
                    <div class="margin">
                        <div class="s-12 m-12 l-6">
                            <div class="image-border-radius margin-m-bottom">
                                <div class="margin">
                                    <div class="s-12 m-12 l-4 margin-m-bottom">
                                        <a class="image-hover-zoom" href="#"><img src="<c:url value = "/assets/img/blog-05.jpg"/>" alt=""></a>
                                    </div>
                                    <div class="s-12 m-12 l-8 margin-m-bottom">
                                        <h3><a class="text-dark text-primary-hover" href="#">Lorem Ipsum Dolor sit
                                            Amet</a></h3>
                                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                                            consequat.</p>
                                        <a class="text-more-info text-primary-hover" href="#">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="s-12 m-12 l-6">
                            <div class="image-border-radius">
                                <div class="margin">
                                    <div class="s-12 m-12 l-4 margin-m-bottom">
                                        <a class="image-hover-zoom" href="#"><img src="<c:url value = "/assets/img/blog-03.jpg"/>" alt=""></a>
                                    </div>
                                    <div class="s-12 m-12 l-8">
                                        <h3><a class="text-dark text-primary-hover" href="#">Lorem Ipsum Dolor sit
                                            Amet</a></h3>
                                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                                            consequat.</p>
                                        <a class="text-more-info text-primary-hover" href="#">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="margin">
                        <div class="s-12 m-12 l-6">
                            <div class="image-border-radius margin-m-bottom">
                                <div class="margin">
                                    <div class="s-12 m-12 l-4 margin-m-bottom">
                                        <a class="image-hover-zoom" href="#"><img src="<c:url value = "/assets/img/blog-04.jpg"/>" alt=""></a>
                                    </div>
                                    <div class="s-12 m-12 l-8 margin-m-bottom">
                                        <h3><a class="text-dark text-primary-hover" href="#">Lorem Ipsum Dolor sit
                                            Amet</a></h3>
                                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                                            consequat.</p>
                                        <a class="text-more-info text-primary-hover" href="#">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="s-12 m-12 l-6">
                            <div class="image-border-radius">
                                <div class="margin">
                                    <div class="s-12 m-12 l-4 margin-m-bottom">
                                        <a class="image-hover-zoom" href="#"><img src="<c:url value = "/assets/img/blog-02.jpg"/>" alt=""></a>
                                    </div>
                                    <div class="s-12 m-12 l-8">
                                        <h3><a class="text-dark text-primary-hover" href="#">Lorem Ipsum Dolor sit
                                            Amet</a></h3>
                                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                                            consequat.</p>
                                        <a class="text-more-info text-primary-hover" href="#">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="margin">
                        <div class="s-12 m-12 l-6">
                            <div class="image-border-radius margin-m-bottom">
                                <div class="margin">
                                    <div class="s-12 m-12 l-4 margin-m-bottom">
                                        <a class="image-hover-zoom" href="#"><img src="<c:url value = "/assets/img/blog-01.jpg"/>" alt=""></a>
                                    </div>
                                    <div class="s-12 m-12 l-8 margin-m-bottom">
                                        <h3><a class="text-dark text-primary-hover" href="#">Lorem Ipsum Dolor sit
                                            Amet</a></h3>
                                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                                            consequat.</p>
                                        <a class="text-more-info text-primary-hover" href="#">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="s-12 m-12 l-6">
                            <div class="image-border-radius">
                                <div class="margin">
                                    <div class="s-12 m-12 l-4 margin-m-bottom">
                                        <a class="image-hover-zoom" href="#"><img src="<c:url value = "/assets/img/blog-06.jpg"/>" alt=""></a>
                                    </div>
                                    <div class="s-12 m-12 l-8">
                                        <h3><a class="text-dark text-primary-hover" href="#">Lorem Ipsum Dolor sit
                                            Amet</a></h3>
                                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                                            consequat.</p>
                                        <a class="text-more-info text-primary-hover" href="#">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>--%>

</main>
<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/8/2017
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- MAIN -->
<main role="main">
    <!-- Content -->
    <article>
        <header class="section background-primary text-center">
            <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1"><s:message
                    code="products"/></h1>
        </header>
        <c:forEach var="group" items="${product_list}" varStatus="roop">
            <div class="section-small-padding background-white">
                <div class="line">
                    <h1 class="text-primary margin-bottom-20 text-size-50 text-thin text-line-height-1">
                        <i class="icon-eye icon2x"></i>${group[0].category_name}
                    </h1>
                </div>
                <div class="line">
                    <div class="margin text-center">
                        <c:forEach var="one" items="${group}" varStatus="roop">
                            <div class="s-12 m-12 l-6 margin-bottom">
                                <div class="padding-2x block-bordered border-radius">
                                    <img class="margin-bottom-20" src="${product_file_path}${one.img}"/>
                                    <h2 class="text-thin">${one.name}</h2>
                                    <p class="margin-bottom-30">${one.content}</p>
                                    <a class="button border-radius background-primary text-size-12 text-white text-strong"
                                       href="/strongshine/web/frontend/product-info?product_id=${one.id}"><s:message
                                            code="read_more"/></a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
    </article>
</main>
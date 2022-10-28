<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/9/2017
  Time: 11:50 AM
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
            <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1">${product_info_list[0].name}</h1>
        </header>
        <div class="section background-white">
            <div class="line">
                <div class="s-12 m-12 l-10 center text-center">
                    <c:forEach var="one" items="${product_info_list}" varStatus="roop">
                        <h3 class="headline text-extra-strong text-uppercase margin-bottom-40">${one.content}</h3>
                        <c:if test="${one.img != null}">
                        	<img class="center margin-bottom-50" src="${file_path}${one.img}" alt="responsive template"/>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </article>
</main>
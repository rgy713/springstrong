<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/8/2017
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- HEADER -->
<header role="banner">
    <!-- Top Bar -->
    <div class="top-bar background-white">
        <div class="line">
            <div class="s-12 m-6 l-6">
                <div class="top-bar-contact">
                    <p class="text-size-12"><s:message code="contact_us"/>: 8888 888 888 | <a class="text-orange-hover"
                                                                          href="mailto:contact@sampledomain.com">QR@strongshine.com</a>
                    </p>
                </div>
            </div>
            <%--<div class="s-12 m-6 l-6">
                <div class="right">
                    <ul class="top-bar-lang right">
                        <li><a href="home?lang=ko_KR"><span class="text-orange-hover">조선어</span></a></li>
                        <li><a href="home?lang=en_US"><span class="text-orange-hover">English</span></a></li>
                        <li><a href="home?lang=zh_CN"><span class="text-orange-hover">中国语</span></a></li>
                        <li><a href="home?lang=ja_JP"><span class="text-orange-hover">日本語</span></a></li>
                    </ul>
                </div>
            </div>--%>
        </div>
    </div>

    <!-- Top Navigation -->
    <nav class="background-white background-primary-hightlight">
        <div class="line">
            <div class="s-12 l-2">
                <a href="/strongshine/web/backend/admin" class="logo"><img src="<c:url value = "/assets/img/logo-free.png"/>" alt=""></a>
            </div>
            <div class="top-nav s-12 l-10">
                <p class="nav-text"></p>
                <ul class="right chevron">
                    <li><a href="/strongshine/web/backend/admin/home"><s:message code="home"/></a></li>
                    <li><a href="/strongshine/web/backend/admin/products"><s:message code="products"/></a></li>
                    <%--<li><a><s:message code="service"/></a>
                        <ul>
                            <li><a><s:message code="service1"/></a>
                                <ul>
                                    <li><a href="/strongshine/web/backend/admin/home"><s:message code="service1a"/></a></li>
                                    <li><a><s:message code="service1b"/></a></li>
                                </ul>
                            </li>
                            <li><a><s:message code="service2"/></a></li>
                        </ul>
                    </li>--%>
                    <li><a href="/strongshine/web/backend/admin/about"><s:message code="about"/></a></li>
                    <%--<li><a href="/strongshine/web/backend/admin"><s:message code="gallery"/></a></li>--%>
                    <li><a href="/strongshine/web/backend/admin/contact"><s:message code="contact"/></a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
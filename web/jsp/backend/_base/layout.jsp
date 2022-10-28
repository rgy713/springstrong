<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/8/2017
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="requestURI" value="${pageContext.request.requestURI}" scope="application"/>
<c:set var="imagePath" value="${contextPath}/assets/img" scope="application"/>
<c:set var="cssPath" value="${contextPath}/assets/css" scope="application"/>
<c:set var="jsPath" value="${contextPath}/assets/js" scope="application"/>
<c:set var="jsLibPath" value="${contextPath}/assets/js/_lib" scope="application"/>

<c:choose>
    <c:when test="${sessionScope.SESS_LOCALE eq null}">
        <c:set var="locale" value="en_US" scope="session"/>
    </c:when>
    <c:otherwise>
        <c:set var="locale" value="${sessionScope.SESS_LOCALE}" scope="session"/>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="${lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>strongshine Admin Page</title>
    <link rel="icon" type="image/png" href="${imagePath}/strongshine_logo.png">
    <link rel="stylesheet" href="${cssPath}/components.css">
    <link rel="stylesheet" href="${cssPath}/icons.css">
    <link rel="stylesheet" href="${cssPath}/responsee.css">
    <link rel="stylesheet" href="${jsLibPath}/owl-carousel/owl.carousel.css">
    <link rel="stylesheet" href="${jsLibPath}/owl-carousel/owl.theme.css">
    <link rel="stylesheet" href="${cssPath}/strongshine.css">
    <!-- CUSTOM STYLE -->
    <link rel="stylesheet" href="<c:url value = "/assets/css/template-style.css"/>">
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800&subset=latin,latin-ext' rel='stylesheet' type='text/css'>--%>
    <script type="text/javascript" src="${jsPath}/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${jsPath}/jquery-ui.min.js"></script>
</head>

<body class="size-1140">
<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="body"/>

<tiles:insertAttribute name="footer"/>
<script>
    var select_img = false;
    function getPopupPane(selector) {
        if (selector)
            return $("#qrid-popup-pane " + selector);
        else
            return $("#qrid-popup-pane");
    }
    function showPopupPane() {
        select_img = false;
        getPopupPane().css("display", "inherit");
    }
    function hidePopupPane() {
        getPopupPane().css("display", "none");
    }
    function popupClose() {
        hidePopupPane();
    }
    function selectPane(selector) {
        getPopupPane().empty();
        getPopupPane().html($(selector).html());
    }
    function readImg(input_list) {
        if (input_list.files && input_list.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                getPopupPane("#qrid-img-view")
                    .attr('style', '')
                    .attr('src', e.target.result);
            };

            reader.readAsDataURL(input_list.files[0]);
            select_img = true;
        }
    }
</script>
<script type="text/javascript" src="${jsPath}/responsee.js"></script>
<script type="text/javascript" src="${jsLibPath}/owl-carousel/owl.carousel.js"></script>
<script type="text/javascript" src="${jsPath}/template-scripts.js"></script>
</body>
</html>
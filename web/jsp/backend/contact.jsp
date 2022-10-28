<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/13/2017
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<main role="main">
    <header class="section-small-padding background-primary text-center">
        <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1">Contact</h1>
    </header>
    <section class="section-small-padding">
        <div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>e_mail</th>
                    <th>name</th>
                    <th>subject</th>
                    <th>content</th>
                    <th>send time</th>
                    <th>#</th>
                </tr>
                </thead>
                <c:forEach var="one" items="${feedback_list}" varStatus="roop">
                    <tr>
                        <td>${one.id}</td>
                        <td>${one.e_mail}</td>
                        <td>${one.name}</td>
                        <td>${one.subject}</td>
                        <td>${one.content}</td>
                        <td>${one.send_time}</td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</main>
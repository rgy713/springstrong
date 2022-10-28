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
            <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1">Prospera Premium Features</h1>
        </header>
        <div class="section background-white">
            <div class="line">
                <div class="s-12 m-12 l-10 center text-center">
                    <h3 class="headline text-extra-strong text-uppercase margin-bottom-40">Looks Great on Every Device</h3>
                    <img class="center" src="<c:url value = "/assets/img/responsive2.png"/>" alt="responsive template"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Many Variants of Page Templates</h3>
                    <img class="center" src="<c:url value = "/assets/img/templates.png"/>" alt="templates"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Magazine Page Template</h3>
                    <img class="center" src="<c:url value = "/assets/img/magazine.png"/>" alt="magazine"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Tons of Reusable Elements</h3>
                    <img class="center" src="<c:url value = "/assets/img/tons-of-reusable-elements.png"/>" alt="tons of reusable elements"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Animated Scroll Loading Effects</h3>
                    <img class="center" src="<c:url value = "/assets/img/content-animation.gif"/>" alt="animated scroll loading effects"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80">Skill Bars Animation</h3>
                    <img class="center" src="<c:url value = "/assets/img/skill-bars.gif"/>" alt="skill bars animation"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Animated Typing</h3>
                    <img class="center" src="<c:url value = "/assets/img/typed.gif"/>" alt="animated typing"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Count To Animation</h3>
                    <img class="center" src="<c:url value = "/assets/img/count-to.gif"/>" alt="count to animations"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Responsive Pricing Tables<br> with Count To Animation</h3>
                    <img class="center" src="<c:url value = "/assets/img/responsive-pricing-tables.png"/>" alt="responsive pricing tables"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Responsive Image and Video Lightbox</h3>
                    <img class="center" src="<c:url value = "/assets/img/lightbox.png"/>" alt="responsive image and video lightbox"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">Functional Ajax Contact Form</h3>
                    <img class="center" src="<c:url value = "/assets/img/contact-form.jpg"/>" alt="ajax contact form"/>
                    <h3 class="headline text-extra-strong text-uppercase margin-top-80 margin-bottom-40">and many more...</h3>
                    <img class="center" src="<c:url value = "/assets/img/only-for-2.png"/>" alt="only-for"/>
                </div>
            </div>
        </div>
    </article>
</main>
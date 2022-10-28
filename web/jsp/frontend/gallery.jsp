<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/9/2017
  Time: 9:18 AM
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
            <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1"><s:message code="gallery"/></h1>
        </header>
        <div class="section background-white">
            <div class="line">
                <div class="margin">
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-02.jpg"/>" alt="" title="Portfolio Image 1" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-09.jpg"/>" alt="" title="Portfolio Image 2" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-08.jpg"/>" alt="" title="Portfolio Image 3" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-05.jpg"/>" alt="" title="Portfolio Image 4" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-06.jpg"/>" alt="" title="Portfolio Image 5" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-11.jpg"/>" alt="" title="Portfolio Image 6" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-12.jpg"/>" alt="" title="Portfolio Image 7" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-07.jpg"/>" alt="" title="Portfolio Image 8" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-01.jpg"/>" alt="" title="Portfolio Image 9" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-14.jpg"/>" alt="" title="Portfolio Image 10" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-03.jpg"/>" alt="" title="Portfolio Image 11" />
                        </div>
                    </div>
                    <div class="s-12 m-6 l-3">
                        <div class="image-with-hover-overlay image-hover-zoom margin-bottom">
                            <div class="image-hover-overlay background-primary">
                                <div class="image-hover-overlay-content text-center padding-2x">
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                            <img src="<c:url value = "/assets/img/portfolio/thumb-04.jpg"/>" alt="" title="Portfolio Image 12" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>
</main>
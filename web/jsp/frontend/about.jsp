<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/9/2017
  Time: 9:15 AM
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
                    code="about"/></h1>
        </header>
        <div class="section background-white">
            <div class="line">
                <c:forEach var="one" items="${about_list}" varStatus="roop">
                    <h2 class="text-size-50">${one.title}</h2>
                    <p class="text-size-30">${one.content}</p>
                    <c:if test="${one.img != null}">
                    	<img class="center margin-bottom-30 margin-top-30" src="${file_path}${one.img}" alt="responsive template"/>
                    </c:if>
                    <%--<blockquote class="margin-top-bottom-20">
                        Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum
                        formas humanitatis per seacula quarta decima et quinta decima.
                    </blockquote>
                    <p class="margin-bottom-30">
                        Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim
                        placerat facer possim assum. Typi non habent claritatem insitam;
                        est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores
                        legere me lius quod ii legunt saepius.
                        Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est
                        notare quam littera gothica, quam nunc
                        putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et
                        quinta decima. Eodem modo typi, qui nunc
                        nobis videntur parum clari, fiant sollemnes in futurum.
                    </p>
                    <div class="line">
                        <div class="margin">
                            <div class="s-12 m-12 l-6 margin-m-bottom-30">
                                <h2>Mirum est notare quam littera gothica</h2>
                                <p>
                                    Typi non habent claritatem insitam est usus legentis in iis qui facit eorum
                                    claritatem.
                                    Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius.
                                    Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium
                                    lectorum.
                                </p>
                            </div>
                            <div class="s-12 m-12 l-6">
                                <h2>Claritas est etiam processus dynamicus</h2>
                                <p>
                                    Typi non habent claritatem insitam est usus legentis in iis qui facit eorum
                                    claritatem.
                                    Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius.
                                    Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium
                                    lectorum.
                                </p>
                            </div>
                        </div>
                    </div>--%>
                </c:forEach>
            </div>
        </div>
    </article>
</main>
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
<!-- FOOTER -->
<footer>
    <!-- Social -->
    <div class="background-primary padding text-center">
        <a href="#"><i class="icon-facebook_circle icon2x text-white"></i></a>
        <a href="#"><i class="icon-twitter_circle icon2x text-white"></i></a>
        <a href="#"><i class="icon-google_plus_circle icon2x text-white"></i></a>
        <a href="#"><i class="icon-instagram_circle icon2x text-white"></i></a>
    </div>

    <!-- Main Footer -->
    <section class="section-small-padding background-dark">
        <div class="line">
            <div class="margin">

                <!-- Collumn 1 -->
                <div class="s-12 m-12 l-4 margin-m-bottom-2x">
                    <h4 class="text-uppercase text-strong"><s:message code="philosophy.title"/></h4>
                    <p class="text-size-20"><em><s:message code="philosophy.cont"/></em></p>
                    <div class="line">
                        <h4 class="text-uppercase text-strong margin-top-30"><em><s:message code="company.about"/></em>
                        </h4>
                        <div class="margin">
                            <div class="s-12 m-12 l-4 margin-m-bottom">
                                <a class="image-hover-zoom" href="#"><img
                                        src="<c:url value = "/assets/img/blog-04.jpg"/>" alt=""></a>
                            </div>
                            <div class="s-12 m-12 l-8 margin-m-bottom">
                                <p><s:message code="company.cont"/></p>
                                <a class="text-more-info text-primary-hover" href="#"><s:message code="read_more"/></a>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Collumn 2 -->
                <div class="s-12 m-12 l-4 margin-m-bottom-2x">
                    <h4 class="text-uppercase text-strong"><s:message code="contact_us"/></h4>
                    <div class="line">
                        <div class="s-1 m-1 l-1 text-center">
                            <i class="icon-placepin text-primary text-size-12"></i>
                        </div>
                        <div class="s-11 m-11 l-11 margin-bottom-10">
                            <p><b><s:message code="adress"/>:</b> Responsive Street 7, London, UK</p>
                        </div>
                    </div>
                    <div class="line">
                        <div class="s-1 m-1 l-1 text-center">
                            <i class="icon-mail text-primary text-size-12"></i>
                        </div>
                        <div class="s-11 m-11 l-11 margin-bottom-10">
                            <p><a href="#" class="text-primary-hover"><b><s:message code="e_mail"/>:</b>
                                contact@sampledomain.com</a></p>
                        </div>
                    </div>
                    <div class="line">
                        <div class="s-1 m-1 l-1 text-center">
                            <i class="icon-smartphone text-primary text-size-12"></i>
                        </div>
                        <div class="s-11 m-11 l-11 margin-bottom-10">
                            <p><b><s:message code="phone"/>:</b> 0700 000 987</p>
                        </div>
                    </div>
                    <div class="line">
                        <div class="s-1 m-1 l-1 text-center">
                            <i class="icon-twitter text-primary text-size-12"></i>
                        </div>
                        <div class="s-11 m-11 l-11 margin-bottom-10">
                            <p><a href="#" class="text-primary-hover"><b>Twitter</b></a></p>
                        </div>
                    </div>
                    <div class="line">
                        <div class="s-1 m-1 l-1 text-center">
                            <i class="icon-facebook text-primary text-size-12"></i>
                        </div>
                        <div class="s-11 m-11 l-11">
                            <p><a href="#" class="text-primary-hover"><b>Facebook</b></a></p>
                        </div>
                    </div>
                </div>

                <!-- Collumn 3 -->
                <div class="s-12 m-12 l-4">
                    <h4 class="text-uppercase text-strong"><s:message code="leave_message"/></h4>
                    <form id="add_feedback_req_form_footer" class="customform text-white" method="POST"
                          enctype="multipart/form-data" novalidate="novalidate">
                        <div class="line">
                            <div class="margin">
                                <div class="s-12 m-12 l-6">
                                    <input name="e_mail" class="required email border-radius"
                                           placeholder="<s:message code="your.e_mail"/>"
                                           title="Your e-mail" type="text"/>
                                </div>
                                <div class="s-12 m-12 l-6">
                                    <input name="name" class="name border-radius"
                                           placeholder="<s:message code="your.name"/>"
                                           title="Your name"
                                           type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="s-12">
                            <textarea name="content" class="required message border-radius"
                                      placeholder="<s:message code="your.message"/>"
                                      rows="3"></textarea>
                        </div>
                        <div class="s-12">
                            <div class="submit-form button background-primary border-radius text-white"
                                 onclick="addFeedbackFooter()">
                                <s:message code="submit_button"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <hr class="break margin-top-bottom-0" style="border-color: rgba(0, 38, 51, 0.80);">

    <!-- Bottom Footer -->
    <section class="padding background-dark">
        <div class="line">
            <div class="s-12 l-6">
                <p class="text-size-12">Copyright 2017, Vision Design - strongshine</p>
                <p class="text-size-12"><s:message code="do_not_use"/></p>
            </div>
            <div class="s-12 l-6">
                <a class="right text-size-12" href="#"
                   title="Responsee - lightweight responsive framework">Design and coding<br> by strongshine Team</a>
            </div>
        </div>
    </section>
</footer>
<script>
    function addFeedbackFooter() {
        if (
            $("#add_feedback_req_form_footer input[name=e_mail]").val() == "" ||
            $("#add_feedback_req_form_footer input[name=name]").val() == "" ||
            $("#add_feedback_req_form_footer textarea[name=content]").val() == ""
        ) {
            alert("You can't send message!");
            return false;
        }
        var formData = new FormData($("#add_feedback_req_form_footer")[0]);
        $.ajax({
            url: "/strongshine/web/frontend/contact/add-feedback",
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                $("#add_feedback_req_form_footer input[name=e_mail]").val("");
                $("#add_feedback_req_form_footer input[name=name]").val("");
                $("#add_feedback_req_form_footer textarea[name=content]").val("");
                alert("Success!");
            },
            error: function () {
                alert("Send Feedback Error!");
            }
        });
    }
</script>

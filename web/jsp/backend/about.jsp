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
        <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1">Home</h1>
    </header>
    <section class="section-small-padding">
        <div id="qrid-add-about" class="submit-form button background-primary border-radius text-white"
             onclick="addAboutPane()">
            Add About
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Image</th>
                    <th>Title(ko)</th>
                    <th>Title(en)</th>
                    <th>Title(zh)</th>
                    <th>Title(ja)</th>
                    <th>Content(ko)</th>
                    <th>Content(en)</th>
                    <th>Content(zh)</th>
                    <th>Content(ja)</th>
                    <th>#</th>
                </tr>
                </thead>
                <c:forEach var="one" items="${about_list}" varStatus="roop">
                    <tr id="qrid-about-list-${one.id}">
                        <td>${one.id}</td>
                        <td>${one.img}</td>
                        <td>${one.title_ko}</td>
                        <td>${one.title_en}</td>
                        <td>${one.title_zh}</td>
                        <td>${one.title_ja}</td>
                        <td>${one.content_ko}</td>
                        <td>${one.content_en}</td>
                        <td>${one.content_zh}</td>
                        <td>${one.content_ja}</td>
                        <td><i id="qrid-about-edit" class="icon-pen text-dark-hover"
                               onclick="updateAboutPane(${one.id})"></i></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>

    <div id="qrid-popup-pane" class="popup owl-fade-in"></div>

    <data id="qrid-about-edit-pane" style="display:none">
        <div class="modal center s-12 m-10 l-6">
            <div class="line margin-bottom-15">
                <i class="text-strong left">Add About</i>
                <i class="icon-sli-close text-dark-hover right" onclick="popupClose()"></i>
            </div>
            <div class="qr-ralative">
                <form id="about_reg_form" class="customform" method="POST"
                      enctype="multipart/form-data" novalidate="novalidate">
                    <input id="qrid-about-id" type="hidden" name="id" class="display:none;" value=""/>
                    <div class="line center s-12 m-10 l-8 qr-ralative">
                        <img id="qrid-img-view" class="margin-bottom-15" src="${imagePath}/input-img.png"/>
                        <input type="file" id="qrid-img-input" name="image" class="autoHeight"
                               onchange="readImg(this);"/>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Title(Korean)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="title_ko" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Title(English)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="title_en" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Title(Chinese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="title_zh" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Title(Japanese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="title_ja" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Content(Korean)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="content_ko" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Content(English)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="content_en" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Content(Chinese)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="content_zh" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Content(Japanese)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="content_ja" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                </form>
            </div>

            <div id="qrid-cancel" class="right button background-dark border-radius text-white margin-left-10"
                 onclick="popupClose()">
                Cancel
            </div>
            <div id="qrid-delete"
                 class="right cancel-btn submit-form button border-radius text-white margin-left-10"
                 onclick="deleteAbout()">
                Delete
            </div>
            <div id="qrid-submit"
                 class="right submit-form button background-primary border-radius text-white"
                 onclick="aboutSubmit()">
                OK
            </div>
        </div>
        <form id="delete_reg_form" class="customform" method="POST"
              enctype="multipart/form-data" novalidate="novalidate" action="/strongshine/web/backend/admin/about/delete-about">
            <input id="qrid-delete-id" type="hidden" name="id" class="display:none;" value=""/>
        </form>
    </data>
</main>
<script>
    function addAboutPane() {
        selectPane("data#qrid-about-edit-pane");
        getPopupPane("#qrid-delete").css("display", "none");
        getPopupPane("#about_reg_form").attr("action", "/strongshine/web/backend/admin/about/add-about");
        showPopupPane();
    }
    function updateAboutPane(id) {
        selectPane("data#qrid-about-edit-pane");
        getPopupPane("#qrid-about-id").val(id);
        getPopupPane("#qrid-delete-id").val(id);
        var $about_list = $("#qrid-about-list-" + id + ">td");
        var img_url = "${about_file_path}" + $($about_list[1]).html();
        getPopupPane("#about_reg_form>div>img").attr("src", img_url);
        var tmp_html = "";
        var select_dom = "";
        for (var i = 0; i < 8; i++) {
            tmp_html = $($about_list[i + 2]).html();
            if (i < 4)
                select_dom = "input";
            else
                select_dom = "textarea";
            $(getPopupPane("#about_reg_form>div")[i + 1]).children("div").children(select_dom).val(tmp_html);
        }
        getPopupPane("#about_reg_form").attr("action", "/strongshine/web/backend/admin/about/update-about");
        showPopupPane();
    }
    function aboutSubmit() {
        if (getPopupPane("#qrid-about-id").val() == "" && !select_img) {
            alert("Please Select about Image!");
            return;
        }
        getPopupPane("#about_reg_form").submit();
    }
    function deleteAbout() {
        getPopupPane("#delete_reg_form").submit();
    }
</script>
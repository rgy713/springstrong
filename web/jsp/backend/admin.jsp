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
        <div id="qrid-add-banner" class="submit-form button background-primary border-radius text-white"
             onclick="addBannerPane()">
            Add Banner
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Banner Image</th>
                    <th>Banner Title(ko)</th>
                    <th>Banner Title(en)</th>
                    <th>Banner Title(zh)</th>
                    <th>Banner Title(ja)</th>
                    <th>Banner Content(ko)</th>
                    <th>Banner Content(en)</th>
                    <th>Banner Content(zh)</th>
                    <th>Banner Content(ja)</th>
                    <th>#</th>
                </tr>
                </thead>
                <c:forEach var="one" items="${banner_list}" varStatus="roop">
                    <tr id="qrid-banner-list-${one.id}">
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
                        <td><i id="qrid-banner-edit" class="icon-pen text-dark-hover"
                               onclick="updateBannerPane(${one.id})"></i></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>

    <div id="qrid-popup-pane" class="popup owl-fade-in"></div>

    <data id="qrid-banner-edit-pane" style="display:none">
        <div class="modal center s-12 m-10 l-6">
            <div class="line margin-bottom-15">
                <i class="text-strong left">Add Banner</i>
                <i class="icon-sli-close text-dark-hover right" onclick="popupClose()"></i>
            </div>
            <div class="qr-ralative">
                <form id="banner_reg_form" class="customform" method="POST"
                      enctype="multipart/form-data" novalidate="novalidate">
                    <input id="qrid-banner-id" type="hidden" name="id" class="display:none;" value=""/>
                    <div class="line center s-12 m-10 l-8 qr-ralative">
                        <img id="qrid-img-view" class="margin-bottom-15" src="${imagePath}/input-img.png"/>
                        <input type="file" id="qrid-img-input" name="banner_img" class="autoHeight"
                               onchange="readImg(this);"/>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Title(Korean)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="banner_title_ko" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Title(English)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="banner_title_en" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Title(Chinese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="banner_title_zh" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Title(Japanese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="banner_title_ja" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Content(Korean)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="banner_content_ko" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Content(English)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="banner_content_en" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Content(Chinese)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="banner_content_zh" class="required message border-radius"
                  placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Banner Content(Japanese)</span>
                        <div class="s-9 m-9 l-9">
        <textarea name="banner_content_ja" class="required message border-radius"
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
                 onclick="deleteBanner()">
                Delete
            </div>
            <div id="qrid-submit"
                 class="right submit-form button background-primary border-radius text-white"
                 onclick="bannerSubmit()">
                OK
            </div>
        </div>
        <form id="delete_reg_form" class="customform" method="POST"
              enctype="multipart/form-data" novalidate="novalidate" action="/strongshine/web/backend/admin/home/delete-banner">
            <input id="qrid-delete-id" type="hidden" name="id" class="display:none;" value=""/>
        </form>
    </data>
</main>
<script>
    function addBannerPane() {
        selectPane("data#qrid-banner-edit-pane");
        getPopupPane("#qrid-delete").css("display", "none");
        getPopupPane("#banner_reg_form").attr("action", "/strongshine/web/backend/admin/home/add-banner");
        showPopupPane();
    }
    function updateBannerPane(id) {
        selectPane("data#qrid-banner-edit-pane");
        getPopupPane("#qrid-banner-id").val(id);
        getPopupPane("#qrid-delete-id").val(id);
        var $banner_list = $("#qrid-banner-list-" + id + ">td");
        var img_url = "${banner_file_path}" + $($banner_list[1]).html();
        getPopupPane("#banner_reg_form>div>img").attr("src", img_url);
        var tmp_html = "";
        var select_dom = "";
        for (var i = 0; i < 8; i++) {
            tmp_html = $($banner_list[i + 2]).html();
            if (i < 4)
                select_dom = "input";
            else
                select_dom = "textarea";
            $(getPopupPane("#banner_reg_form>div")[i + 1]).children("div").children(select_dom).val(tmp_html);
        }
        getPopupPane("#banner_reg_form").attr("action", "/strongshine/web/backend/admin/home/update-banner");
        showPopupPane();
    }
    function bannerSubmit() {
        if (getPopupPane("#qrid-banner-id").val() == "" && !select_img) {
            alert("Please Select Banner Image!");
            return;
        }
        getPopupPane("#banner_reg_form").submit();
    }
    function deleteBanner() {
        getPopupPane("#delete_reg_form").submit();
    }
</script>
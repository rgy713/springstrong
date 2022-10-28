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
        <h1 class="text-white margin-bottom-0 text-size-50 text-thin text-line-height-1">Products</h1>
    </header>
    <section class="section-small-padding">
        <div id="qrid-add-category" class="submit-form button background-primary border-radius text-white"
             onclick="addCategoryPane()">
            Add Category
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name(ko)</th>
                    <th>Name(en)</th>
                    <th>Name(zh)</th>
                    <th>Name(ja)</th>
                    <th>#</th>
                </tr>
                </thead>
                <c:forEach var="one" items="${category_list}" varStatus="roop">
                    <tr id="qrid-category-list-${one.id}">
                        <td>${one.id}</td>
                        <td>${one.name_ko}</td>
                        <td>${one.name_en}</td>
                        <td>${one.name_zh}</td>
                        <td>${one.name_ja}</td>
                        <td><i id="qrid-category-edit" class="icon-pen text-dark-hover"
                               onclick="updateCategoryPane(${one.id})"></i></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
    <section class="section-small-padding">
        <div id="qrid-add-product" class="submit-form button background-primary border-radius text-white"
             onclick="addProductPane()">
            Add Product
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Id</th>
                    <th>Category Id</th>
                    <th>Product Image</th>
                    <th>Product Name(ko)</th>
                    <th>Product Name(en)</th>
                    <th>Product Name(zh)</th>
                    <th>Product Name(ja)</th>
                    <th>Product Content(ko)</th>
                    <th>Product Content(en)</th>
                    <th>Product Content(zh)</th>
                    <th>Product Content(ja)</th>
                    <th>#</th>
                </tr>
                </thead>
                <c:forEach var="one" items="${product_list}" varStatus="roop">
                    <tr id="qrid-product-list-${one.id}">
                        <td><i id="qrid-product-info-view" class="icon-plus text-dark-hover"
                               onclick="viewProductInfo(${one.id})"></i></td>
                        <td>${one.id}</td>
                        <td>${one.category_id}</td>
                        <td>${one.img}</td>
                        <td>${one.name_ko}</td>
                        <td>${one.name_en}</td>
                        <td>${one.name_zh}</td>
                        <td>${one.name_ja}</td>
                        <td>${one.content_ko}</td>
                        <td>${one.content_en}</td>
                        <td>${one.content_zh}</td>
                        <td>${one.content_ja}</td>
                        <td><i id="qrid-product-edit" class="icon-pen text-dark-hover"
                               onclick="updateProductPane(${one.id},${one.category_id})"></i></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>

    <div id="qrid-popup-pane" class="popup owl-fade-in"></div>

    <data id="qrid-product-edit-pane" style="display:none">
        <div class="modal center s-12 m-10 l-6">
            <div class="line margin-bottom-15">
                <i class="text-strong left">Add Product</i>
                <i class="icon-sli-close text-dark-hover right" onclick="popupClose()"></i>
            </div>
            <div class="qr-ralative">
                <form id="product_reg_form" class="customform" method="POST"
                      enctype="multipart/form-data" novalidate="novalidate">
                    <input id="qrid-product-id" type="hidden" name="id" class="display:none;" value=""/>
                    <div class="line center s-12 m-10 l-8 qr-ralative">
                        <img id="qrid-img-view" class="margin-bottom-15" src="${imagePath}/input-img.png"/>
                        <input type="file" id="qrid-img-input" name="product_img" class="autoHeight"
                               onchange="readImg(this);"/>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Category</span>
                        <div class="s-9 m-9 l-9">
                            <select name="category_id" form="product_reg_form">
                                <c:forEach var="one" items="${category_list}" varStatus="roop">
                                    <option value="${one.id}">${one.name_en}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Name(Korean)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="product_name_ko" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Name(English)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="product_name_en" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Name(Chinese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="product_name_zh" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Name(Japanese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="product_name_ja" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Content(Korean)</span>
                        <div class="s-9 m-9 l-9">
                            <textarea name="product_content_ko" class="required message border-radius"
                                      placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Content(English)</span>
                        <div class="s-9 m-9 l-9">
                            <textarea name="product_content_en" class="required message border-radius"
                                      placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Content(Chinese)</span>
                        <div class="s-9 m-9 l-9">
                            <textarea name="product_content_zh" class="required message border-radius"
                                      placeholder="Input text"></textarea>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Product Content(Japanese)</span>
                        <div class="s-9 m-9 l-9">
                            <textarea name="product_content_ja" class="required message border-radius"
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
                 onclick="deleteProduct()">
                Delete
            </div>
            <div id="qrid-submit" class="right submit-form button background-primary border-radius text-white"
                 onclick="productSubmit()">
                OK
            </div>
        </div>
        <form id="delete_reg_form" class="customform" method="POST"
              enctype="multipart/form-data" novalidate="novalidate"
              action="/strongshine/web/backend/admin/products/delete-product">
            <input id="qrid-delete-id" type="hidden" name="id" class="display:none;" value=""/>
        </form>
    </data>

    <data id="qrid-product-info-edit-pane" style="display:none">
        <div class="modal center s-12 m-10 l-6">
            <div class="line margin-bottom-15">
                <i class="text-strong left">Add Product Information</i>
                <i class="icon-sli-close text-dark-hover right" onclick="popupClose()"></i>
            </div>
            <div class="qr-ralative">
                <form id="product_info_reg_form" class="customform" method="POST"
                      enctype="multipart/form-data" novalidate="novalidate">
                    <input id="qrid-product-id" type="hidden" name="product_id" class="display:none;" value=""/>
                    <input id="qrid-product-info-id" type="hidden" name="id" class="display:none;" value=""/>
                    <div class="line center s-12 m-10 l-8 qr-ralative">
                        <img id="qrid-img-view" class="margin-bottom-15" src="${imagePath}/input-img.png"/>
                        <input type="file" id="qrid-img-input" name="img" class="autoHeight"
                               onchange="readImg(this);"/>
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
                 onclick="deleteProductInfo()">
                Delete
            </div>
            <div id="qrid-submit" class="right submit-form button background-primary border-radius text-white"
                 onclick="productInfoSubmit()">
                OK
            </div>
        </div>
        <form id="delete_info_reg_form" class="customform" method="POST"
              enctype="multipart/form-data" novalidate="novalidate"
              action="/strongshine/web/backend/admin/products/delete-product-info">
            <input id="qrid-delete-id" type="hidden" name="id" class="display:none;" value=""/>
            <input id="qrid-delete-product-id" type="hidden" name="product_id" class="display:none;" value=""/>
        </form>
    </data>
    <data id="qrid-category-edit-pane" style="display:none">
        <div class="modal center s-12 m-10 l-6">
            <div class="line margin-bottom-15">
                <i class="text-strong left">Add Category</i>
                <i class="icon-sli-close text-dark-hover right" onclick="popupClose()"></i>
            </div>
            <div class="qr-ralative">
                <form id="category_reg_form" class="customform" method="POST"
                      enctype="multipart/form-data" novalidate="novalidate">
                    <input id="qrid-category-id" type="hidden" name="id" class="display:none;" value=""/>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Name(Korean)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="name_ko" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Name(English)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="name_en" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Name(Chinese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="name_zh" class="required message border-radius"
                                   placeholder="Input text"/>
                        </div>
                    </div>
                    <div class="line">
                        <span class="text-size-12 s-3 m-3 l-3">Name(Japanese)</span>
                        <div class="s-9 m-9 l-9">
                            <input name="name_ja" class="required message border-radius"
                                   placeholder="Input text"/>
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
                 onclick="deleteCategory()">
                Delete
            </div>
            <div id="qrid-submit" class="right submit-form button background-primary border-radius text-white"
                 onclick="categorySubmit()">
                OK
            </div>
        </div>
        <form id="delete_reg_form" class="customform" method="POST"
              enctype="multipart/form-data" novalidate="novalidate"
              action="/strongshine/web/backend/admin/products/delete-category">
            <input id="qrid-delete-id" type="hidden" name="id" class="display:none;" value=""/>
        </form>
    </data>
</main>
<script>
    function addProductPane() {
        selectPane("data#qrid-product-edit-pane");
        getPopupPane("#qrid-delete").css("display", "none");
        getPopupPane("#product_reg_form").attr("action", "/strongshine/web/backend/admin/products/add-product");
        showPopupPane();
    }
    function updateProductPane(id, category_id) {
        selectPane("data#qrid-product-edit-pane");
        getPopupPane("#qrid-product-id").val(id);
        getPopupPane("#qrid-delete-id").val(id);
        getPopupPane("select[name=category_id]").val(category_id);
        var $product_list = $("#qrid-product-list-" + id + ">td");
        var img_url = "${product_file_path}" + $($product_list[3]).html();
        getPopupPane("#product_reg_form>div>img").attr("src", img_url);
        var tmp_html = "";
        var select_dom = "";
        for (var i = 0; i < 8; i++) {
            tmp_html = $($product_list[i + 4]).html();
            if (i < 4)
                select_dom = "input";
            else
                select_dom = "textarea";
            $(getPopupPane("#product_reg_form>div")[i + 2]).children("div").children(select_dom).val(tmp_html);
        }
        getPopupPane("#product_reg_form").attr("action", "/strongshine/web/backend/admin/products/update-product");
        showPopupPane();
    }
    function productSubmit() {
        if (getPopupPane("#qrid-product-id").val() == "" && !select_img) {
            alert("Please Select Product Image!");
            return;
        }
        getPopupPane("#product_reg_form").submit();
    }
    function deleteProduct() {
        getPopupPane("#delete_reg_form").submit();
    }

    function selectProductFlag(product_id) {
        return ($("#qrid-product-list-" + product_id + " #qrid-product-info-view").hasClass("icon-plus"));
    }
    function selectProduct(product_id) {
        var $select = $("#qrid-product-list-" + product_id + " #qrid-product-info-view");
        if (selectProductFlag(product_id)) {
            $select.removeClass("icon-plus");
            $select.addClass("icon-minus");
        }
        else {
            $select.removeClass("icon-minus");
            $select.addClass("icon-plus");
        }
    }
    function makeProductInfoHtml(result, product_id) {
        var n = result.length;
        var tmp_html = '<div id="qrid-product-info-select-' + product_id + '" class="line">' +
            '<div id="qrid-add-product-info" class="submit-form button background-primary border-radius text-white"' +
            'onclick="addProductInfoPane(' + product_id + ')">Add</div>' +
            '<table>' +
            '<thead><tr>' +
            '<th>Id</th>' +
            '<th>Image</th>' +
            '<th>Content(ko)</th>' +
            '<th>Content(en)</th>' +
            '<th>Content(zh)</th>' +
            '<th>Content(ja)</th>' +
            '<th>#</th>' +
            '</tr></thead>';
        for (var i = 0; i < n; i++) {
            var one = result[i];
            tmp_html = tmp_html +
                '<tr id="qrid-product-info-list' + one.id + '"><td>' + one.id + '</td>' +
                '<td>' + one.image + '</td>' +
                '<td>' + one.content_ko + '</td>' +
                '<td>' + one.content_en + '</td>' +
                '<td>' + one.content_zh + '</td>' +
                '<td>' + one.content_ja + '</td>' +
                '<td><i id="qrid-product-info-edit" class="icon-pen text-dark-hover" ' +
                'onclick="updateProductInfoPane(' + one.id + ',' + one.product_id + ')">' +
                '</i></td></tr>';
        }
        return tmp_html + "</table></div>";
    }
    function viewProductInfo(product_id) {
        selectProduct(product_id);
        if (!selectProductFlag(product_id)) {
            $.ajax({
                url: "/strongshine/web/backend/admin/products/get-product-info",
                type: 'POST',
                data: {
                    product_id: product_id
                },
                async: false,
                success: function (result) {
                    $(makeProductInfoHtml(result, product_id)).insertAfter("#qrid-product-list-" + product_id);
                },
                error: function () {
                    alert("Get Product Info List Error!");
                }
            });
        }
        else {
            $("#qrid-product-info-select-" + product_id).remove();
        }
    }
    function addProductInfoPane(product_id) {
        selectPane("data#qrid-product-info-edit-pane");
        getPopupPane("#qrid-delete").css("display", "none");
        getPopupPane("#qrid-product-id").val(product_id);
        showPopupPane();
    }
    function productInfoSubmit() {
        var id = getPopupPane("input[name=id]").val(),
            product_id = getPopupPane("input[name=product_id]").val();
        var formData = new FormData($("#product_info_reg_form")[0]);
        var url = "";
        if (id == "") {
            url = "/strongshine/web/backend/admin/products/add-product-info";
            if (!select_img) {
                alert("Select Image!");
                return false;
            }
        }
        else {
            url = "/strongshine/web/backend/admin/products/update-product-info";
        }

        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                $("#qrid-product-info-select-" + product_id).remove();
                $(makeProductInfoHtml(result, product_id)).insertAfter("#qrid-product-list-" + product_id);
                popupClose();
            },
            error: function () {
                alert("Add Product Info List Error!");
            }
        });
    }
    function updateProductInfoPane(id, product_id) {
        selectPane("data#qrid-product-info-edit-pane");
        getPopupPane("input[name=id]").val(id);
        getPopupPane("input[name=product_id]").val(product_id);
        getPopupPane("#qrid-delete-id").val(id);
        getPopupPane("#qrid-delete-product-id").val(product_id);
        var $product_info_list = $("#qrid-product-info-list" + id + ">td");
        var img_url = "${product_file_path}" + $($product_info_list[1]).html();
        getPopupPane("#product_info_reg_form>div>img").attr("src", img_url);
        var tmp_html = "";
        var select_dom = "";
        for (var i = 0; i < 4; i++) {
            tmp_html = $($product_info_list[i + 2]).html();
            select_dom = "textarea";
            $(getPopupPane("#product_info_reg_form>div")[i + 1]).children("div").children(select_dom).val(tmp_html);
        }
        showPopupPane();
    }
    function deleteProductInfo() {
        var id = getPopupPane("#qrid-delete-id").val(),
            product_id = getPopupPane("#qrid-delete-product-id").val();
        $.ajax({
            url: "/strongshine/web/backend/admin/products/delete-product-info",
            type: 'POST',
            data: {
                id: id,
                product_id: product_id
            },
            async: false,
            success: function (result) {
                $("#qrid-product-info-select-" + product_id).remove();
                $(makeProductInfoHtml(result, product_id)).insertAfter("#qrid-product-list-" + product_id);
                popupClose();
            },
            error: function () {
                alert("Add Product Info List Error!");
            }
        });
    }
    function addCategoryPane() {
        selectPane("data#qrid-category-edit-pane");
        getPopupPane("#qrid-delete").css("display", "none");
        getPopupPane("#category_reg_form").attr("action", "/strongshine/web/backend/admin/products/add-category");
        showPopupPane();
    }
    function updateCategoryPane(id) {
        selectPane("data#qrid-category-edit-pane");
        getPopupPane("#qrid-category-id").val(id);
        getPopupPane("#qrid-delete-id").val(id);
        var $category_list = $("#qrid-category-list-" + id + ">td");
        var tmp_html = "";
        var select_dom = "input";
        for (var i = 0; i < 4; i++) {
            tmp_html = $($category_list[i + 1]).html();
            $(getPopupPane("#category_reg_form>div")[i]).children("div").children(select_dom).val(tmp_html);
        }
        getPopupPane("#category_reg_form").attr("action", "/strongshine/web/backend/admin/products/update-category");
        showPopupPane();
    }
    function categorySubmit() {
        getPopupPane("#category_reg_form").submit();
    }
    function deleteCategory() {
        getPopupPane("#delete_reg_form").submit();
    }
</script>
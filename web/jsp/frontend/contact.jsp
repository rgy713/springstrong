<%--
  Created by IntelliJ IDEA.
  User: rgy713
  Date: 5/9/2017
  Time: 9:17 AM
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
                    code="contact_us"/></h1>
        </header>
        <div class="section background-white">
            <div class="line">
                <div class="margin">

                    <!-- Company Information -->
                    <div class="s-12 m-12 l-6">
                        <h2 class="text-uppercase text-strong margin-bottom-30"><s:message code="company.info"/></h2>
                        <div class="float-left">
                            <i class="icon-placepin background-primary icon-circle-small text-size-20"></i>
                        </div>
                        <div class="margin-left-80 margin-bottom">
                            <h4 class="text-strong margin-bottom-0"><s:message code="company.address"/></h4>
                            <p>Responsive Street 7<br>
                                London<br>
                                UK, Europe
                            </p>
                        </div>
                        <div class="float-left">
                            <i class="icon-paperplane_ico background-primary icon-circle-small text-size-20"></i>
                        </div>
                        <div class="margin-left-80 margin-bottom">
                            <h4 class="text-strong margin-bottom-0"><s:message code="e_mail"/></h4>
                            <p>contact@sampledomain.com<br>
                                office@sampledomain.com
                            </p>
                        </div>
                        <div class="float-left">
                            <i class="icon-smartphone background-primary icon-circle-small text-size-20"></i>
                        </div>
                        <div class="margin-left-80">
                            <h4 class="text-strong margin-bottom-0"><s:message code="phone_numbers"/></h4>
                            <p>0800 4521 800 50<br>
                                0450 5896 625 16<br>
                                0798 6546 465 15
                            </p>
                        </div>
                    </div>

                    <!-- Contact Form -->
                    <div class="s-12 m-12 l-6">
                        <h2 class="text-uppercase text-strong margin-bottom-30"><s:message code="contact_us"/></h2>
                        <form id="add_feedback_req_form" class="customform" method="POST"
                              enctype="multipart/form-data" novalidate="novalidate">
                            <div class="line">
                                <div class="margin">
                                    <div class="s-12 m-12 l-6">
                                        <input name="e_mail" class="required email border-radius"
                                               placeholder="<s:message code="your.e_mail"/>" title="Your e-mail"
                                               type="text"/>
                                    </div>
                                    <div class="s-12 m-12 l-6">
                                        <input name="name" class="name border-radius"
                                               placeholder="<s:message code="your.name"/>" title="Your name"
                                               type="text"/>
                                    </div>
                                </div>
                            </div>
                            <div class="s-12">
                                <input name="subject" class="subject border-radius"
                                       placeholder="<s:message code="subject"/>" title="Subject" type="text"/>
                            </div>
                            <div class="s-12">
                                <textarea name="content" class="required message border-radius"
                                          placeholder="<s:message code="your.message"/>" rows="3"></textarea>
                            </div>
                            <div class="s-12 m-12 l-4">
                                <div class="submit-form button background-primary border-radius text-white"
                                     onclick="addFeedback()"><s:message code="submit_button"/></div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </article>
    <div class="background-primary padding text-center">
        <a href="#"><i class="icon-facebook_circle icon2x text-white"></i></a>
        <a href="#"><i class="icon-twitter_circle icon2x text-white"></i></a>
        <a href="#"><i class="icon-google_plus_circle icon2x text-white"></i></a>
        <a href="#"><i class="icon-instagram_circle icon2x text-white"></i></a>
    </div>
    <!-- MAP -->
    <script src="http://api.map.baidu.com/api?v=2.0&ak=QdWrlUoXGnfzLfn45LLkbZDp"></script>
    <div class="s-12 center">
        <div id="qrid-map-view" class="s-10 m-10 l-10 center" style="height:500px;"></div>
    </div>
    <script type="text/javascript">
        // 百度地图API功能
        var map = new BMap.Map("qrid-map-view");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);  // 初始化地图,设置中心点坐标和地图级别
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
        var geoLocation = new BMap.GeolocationControl();
        map.addControl(geoLocation);//添加定位控件
        geoLocation.addEventListener('locationSuccess', function () {
            //获取主动定位后的位置信息，\
            //返回一个object形如：{"province":"广东省","city":"广州市","district":"","street":"","streetNumber":""}
            console.log(JSON.stringify(geoLocation.getAddressComponent()))
        });

        map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

        // var pt = new BMap.Point(116.417, 39.909);
        // //自定义标注
        // var icon = new BMap.Icon(
        //     'http://www.yy.com/assets/images/favicon.ico',
        //     new BMap.Size(16, 16)
        //   );
        // var marker = new BMap.Marker(pt, {
        //   icon: icon
        // });
        // map.addOverlay(marker);
        // marker.addEventListener('click', function() {
        //   alert('tanchu')
        // });
        //设置一个固定位置跳排行榜
        var rankPoint = new BMap.Point(91.06, 29.36);
        var rankMarker = new BMap.Marker(rankPoint, {
            icon: new BMap.Icon('http://s2.yy.com/shenqu_mobile/images/common/yy-bear.png', new BMap.Size(100, 100))
        });
        //map.addOverlay(rankMarker);
        rankMarker.addEventListener('click', function () {
            window.location.href = 'http://www.baidu.com'
        });
        //在全国各地设置若干个标注
        function addMarker(point, iconObj) {
            var marker = new BMap.Marker(point, iconObj);
            map.addOverlay(marker);
            marker.addEventListener("click", addVideoDiv);
        }
        //设置用户的标注背景
        var userIcon = new BMap.Icon(
            'http://www.yy.com/assets/images/favicon.ico',
            new BMap.Size(16, 16)
        );
        var bounds = map.getBounds();//返回地图可视区域，以地理坐标表示
        var sw = bounds.getSouthWest();
        var ne = bounds.getNorthEast();
        var lngSpan = Math.abs(sw.lng - ne.lng);//lng 是地理经度，lat 是地理纬度
        var latSpan = Math.abs(ne.lat - sw.lat);
        // for (var i = 0; i < 18; i++) {
        //   var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
        //   addMarker(point, {icon: userIcon});
        // };
        function addVideoDiv() {
            var videoHtml = [
                '<div class="videoBox">',
                '<a href="/">',
                '<img style="float:right;margin:4px" id="imgDemo" src="http://app.baidu.com/map/images/tiananmen.jpg" width="39" height="50"',
                '</a>',
                '</div>'
            ].join('\n');
            var infoWindow = new BMap.InfoWindow(videoHtml);
            this.openInfoWindow(infoWindow);
        }

        map.addEventListener('zoomstart', function () {
            //alert('fangdaditu')
        });
        //设置标注的内容
        var videoHtml = '';
        //自定义一个控件，用来返回默认的尺寸和位置
        function OriginControl() {
            this.defaultAnchor = BMAP_ANCHOR_BOTTOM_LEFT;
            this.defaultOffset = new BMap.Size(10, 10);
        }
        OriginControl.prototype = new BMap.Control();
        OriginControl.prototype.initialize = function (map) {
            var div = document.createElement('div');
            div.appendChild(document.createTextNode('还原'));
            div.style.cursor = 'pointer';
            div.style.border = '1px solid gray';
            div.style.backgroundColor = 'white';
            div.onclick = function (e) {
                map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);
            };
            map.getContainer().appendChild(div);
            return div;
        };
        var myOriginCtrl = new OriginControl();
        map.addControl(myOriginCtrl);
    </script>
    <script>
        function addFeedback() {
            if (
                $("#add_feedback_req_form input[name=e_mail]").val() == "" ||
                $("#add_feedback_req_form input[name=name]").val() == "" ||
                $("#add_feedback_req_form input[name=subject]").val() == "" ||
                $("#add_feedback_req_form textarea[name=content]").val() == ""
            ) {
                alert("You can't send message!");
                return false;
            }
            var formData = new FormData($("#add_feedback_req_form")[0]);
            $.ajax({
                url: "/strongshine/web/frontend/contact/add-feedback",
                type: 'POST',
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    $("#add_feedback_req_form input[name=e_mail]").val("");
                    $("#add_feedback_req_form input[name=name]").val("");
                    $("#add_feedback_req_form input[name=subject]").val("");
                    $("#add_feedback_req_form textarea[name=content]").val("");
                    alert("Success!");
                },
                error: function () {
                    alert("Send Feedback Error!");
                }
            });
        }
    </script>
</main>
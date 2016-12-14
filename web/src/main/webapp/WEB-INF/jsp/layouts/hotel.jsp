<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <%@include file="/WEB-INF/jsp/commons/standard_scripts.jsp"%>
    <title><spring:message code="title.main"/></title>
    <script type="text/javascript">
        function initialize() {
            var secheltLoc = new google.maps.LatLng(49.47216, -123.76307);

            var myMapOptions = {
                zoom: 15
                ,center: secheltLoc
                ,mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var theMap = new google.maps.Map(document.getElementById("map_canvas"), myMapOptions);


            var marker = new google.maps.Marker({
                map: theMap,
                draggable: true,
                position: new google.maps.LatLng(49.47216, -123.76307),
                visible: true
            });

            var boxText = document.createElement("div");
            boxText.innerHTML = "<strong>Best ipsum hotel</strong><br />1400 Pennsylvania Ave,<br />Washington DC<br />www.bestipsumhotel.com";

            var myOptions = {
                content: boxText
                ,disableAutoPan: false
                ,maxWidth: 0
                ,pixelOffset: new google.maps.Size(-140, 0)
                ,zIndex: null
                ,closeBoxURL: ""
                ,infoBoxClearance: new google.maps.Size(1, 1)
                ,isHidden: false
                ,pane: "floatPane"
                ,enableEventPropagation: false
            };

            google.maps.event.addListener(marker, "click", function (e) {
                ib.open(theMap, this);
            });

            var ib = new InfoBox(myOptions);
            ib.open(theMap, marker);
        }
    </script>
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</body>
</html>

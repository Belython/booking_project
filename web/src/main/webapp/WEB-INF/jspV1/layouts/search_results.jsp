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
        $(document).ready(function() {
            $('dt').each(function() {
                var tis = $(this), state = false, answer = tis.next('dd').hide().css('height','auto').slideUp();
                tis.click(function() {
                    state = !state;
                    answer.slideToggle(state);
                    tis.toggleClass('active',state);
                });
            });

            $('.view-type li:first-child').addClass('active');

            $('#star').raty({
                score    : 3,
                click: function(score, evt) {
                    alert('ID: ' + $(this).attr('id') + '\nscore: ' + score + '\nevent: ' + evt);
                }
            });
        });

        $(window).load(function () {
            var maxHeight = 0;

            $(".three-fourth .one-fourth").each(function(){
                if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
            });
            $(".three-fourth .one-fourth").height(maxHeight);
        });
    </script>
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="HandheldFriendly" content="True">

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<link rel="stylesheet" href="${context}/assets/css/style.css" type="text/css" media="screen,projection,print" />
<link rel="stylesheet" href="${context}/assets/css/css.css" type="text/css" media="screen,projection,print" />
<link rel="stylesheet" href="${context}/assets/css/prettyPhoto.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${context}/assets/plugins/calendar/tcal.css" type="text/css" />
<link rel="shortcut icon" href="${context}/assets/images/favicon.ico" />
<%--JQUERY--%>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<%--ANGULAR--%>
<%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>--%>
<%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-mocks.js"></script>--%>
<%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-resource.js"></script>--%>
<%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-route.js"></script>--%>
<script type="text/javascript" src="${context}/assets/scripts/parsers.js"></script>
<script type="text/javascript" src="${context}/assets/scripts/ajax_scripts.js"></script>



<script type="text/javascript" src="${context}/assets/js/css3-mediaqueries.js"></script>
<script type="text/javascript" src="${context}/assets/js/sequence.jquery-min.js"></script>
<script type="text/javascript" src="${context}/assets/js/jquery.uniform.min.js"></script>
<script type="text/javascript" src="${context}/assets/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="${context}/assets/js/sequence.js"></script>
<script type="text/javascript" src="${context}/assets/js/selectnav.js"></script>
<script type="text/javascript" src="${context}/assets/js/scripts.js"></script>
<script type="text/javascript" src="${context}/assets/scripts/script.js"></script>

<c:choose>
    <c:when test="${currentLocale eq 'ru_RU'}">
        <script type="text/javascript" src="${context}/assets/plugins/calendar/tcal_ru.js"></script>
    </c:when>
    <c:when test="${currentLocale eq 'en_US'}">
        <script type="text/javascript" src="${context}/assets/plugins/calendar/tcal_en.js"></script>
    </c:when>
</c:choose>

<script type="text/javascript">
    $(document).ready(function(){
        $(".form").hide();
        $(".form:first").show();
        $(".f-item:first").addClass("active");
        $(".f-item:first span").addClass("checked");
    });
</script>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>


<!--bottom navigation-->
<div class="bottom-nav">
    <!--pager-->
    <div class="pager">
        <c:set var="navSize" value="3"/>
        <c:if test="${totalPages < navSize}" >
            <c:set var="start" value="1"/>
            <c:set var="end" value="${totalPages}"/>
        </c:if>
        <c:if test="${totalPages >= navSize}" >
            <c:set var="start" value="${page - ((page - 1) % navSize)}"/>
            <c:set var="end" value="${navSize}"/>
        </c:if>
        <c:if test="${page >= navSize}">
            <span><a href="${context}/${command}?page=${start - 1}&perPage=${perPage}">&lt;</a></span>
            <span><a href="${context}/${command}?page=1&perPage=${perPage}">1</a></span>
            <span>...</span>
        </c:if>
        <c:forEach begin="${start}" end="${end}" var="i">
            <c:choose>
                <c:when test="${page == i}">
                    <span class="current"><a href="#">${i}</a></span>
                </c:when>
                <c:otherwise>
                    <span><a href="${context}/${command}?page=${i}&perPage=${perPage}">${i}</a></span>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${end < totalPages}">
            <span>...</span>
            <span><a href="${context}/${command}?page=${totalPages}&perPage=${perPage}">${totalPages}</a></span>
            <span><a href="${context}/${command}?page=${end + 1}&perPage=${perPage}">&gt;</a></span>
        </c:if>
    </div>
    <!--//pager-->
</div>
<!--//bottom navigation-->
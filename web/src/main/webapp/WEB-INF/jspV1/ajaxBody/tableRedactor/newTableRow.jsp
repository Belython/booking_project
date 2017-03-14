<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tr>
    <td>${loop.index + 1}</td>
    <c:set var="descriptor" value="${descriptor}" scope="request"/>
    <jsp:include page="/WEB-INF/jsp/ajaxBody/tableRedactor/newImport.jsp" flush="true"/>
    <td>
        <button class="addEntityBtn" type="button"><spring:message code="tableRedactor.create"/></button>
    </td>
    <td>
        <button class="removeRowBtn" type="button"><spring:message code="tableRedactor.remove"/></button>
    </td>
</tr>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p id="operationMessage"></p>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/admin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/tablesorter/tablesorter/jquery.tablesorter.min.js"></script>

<form class="newEntityForm entityForm" name="newEntityForm" method="POST" action="controller">
    <input type="hidden" name="command" value="${alterTable}"/>
    <input type="hidden" name="subCommand" value="addNew"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="newEntity" border="2px">
        <thead>
        <tr>
            <c:forEach var="colName" items="${colNameList}">
                <th>${colName}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${loop.index + 1}</td>
            <c:set var="descriptor" value="${descriptorList.get(0)}" scope="request"/>
            <jsp:include page="/newImport.jsp" flush="true"/>
            <td>
                <button class="addEntityBtn" type="button"><spring:message code="tableRedactor.create"/></button>
            </td>
            <td>
                <button class="removeRowBtn" type="button"><spring:message code="tableRedactor.remove"/></button>
            </td>
        </tr>
        </tbody>
    </table>
    <button class="addRowBtn" type="button"><spring:message code="tableRedactor.add"/></button>
    <input type="submit" value="add all">
</form>

<form class="alterEntityForm entityForm" name="alterEntityForm" method="POST" action="controller">
    <input type="hidden" name="command" value="${alterTable}"/>
    <input type="hidden" name="subCommand" value="changeExisting"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table id="mt" class="existingEntities  tablesorter" border="2px">
        <thead>
        <tr>
            <c:forEach var="colName" items="${colNameList}">
                <th>${colName}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="descriptor" items="${descriptorList}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <c:set var="descriptor" value="${descriptor}" scope="request"/>
                <jsp:include page="/WEB-INF/jsp/ajaxBody/tableRedactor/alterImport.jsp"/> flush="true"/>
                <td>
                    <button class="alterEntityBtn" type="button"><spring:message code="tableRedactor.alter"/></button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="alter all">
</form>

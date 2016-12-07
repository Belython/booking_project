<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>

<!--slider-->
<section class="slider clearfix">
    <div id="sequence">
        <ul>
            <li>
                <div class="info animate-in">
                    <h2>Last minute Winter escapes</h2><br />
                    <p>January 2013 holidays 40% off! An unique opportunity to realize your dreams</p>
                </div>
                <img class="main-image animate-in" src="" alt="" />
            </li>
            <li>
                <div class="info animate-in">
                    <h2>Check out our top weekly deals</h2><br />
                    <p>Save Now. Book Later.</p>
                </div>
                <img class="main-image animate-in" src="${context}/assets/images/slider/img.jpg" alt="" />
            </li>
            <li>
                <div class="info animate-in">
                    <h2>Check out last minute flight, hotel &amp; vacation offers!</h2><br />
                    <p>Save up to 50%!</p>
                </div>
                <img class="main-image animate-in" src="${context}/assets/images/slider/img.jpg" alt="" />
            </li>
        </ul>
    </div>
</section>
<!--//slider-->
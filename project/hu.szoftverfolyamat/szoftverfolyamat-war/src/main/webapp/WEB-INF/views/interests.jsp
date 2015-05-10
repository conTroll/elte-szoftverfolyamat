<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <h3>User interests</h3>

    <div id="interests">
        <c:if test="${fn:length(interests) gt 0}">
            <c:forEach var="interest" items="${interests}">
                <a href="#"><span>${interest.name}</span></a>
            </c:forEach>
        </c:if>
    </div>

    <a href="#">Add new interest</a>
</div>
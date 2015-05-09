<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment">
    <div class="ui comments">
        <h3 class="ui dividing header">Interests</h3>

        <ul>
            <c:forEach var="interest" items="${interests}">
                <li><a class="show" rel="${interest.id}"><span class="label">${interest.name}</span></a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<script type="text/javascript">
    $(".show").click(function() {
        loadInterestPage($(this).attr('rel'));
    });
</script>
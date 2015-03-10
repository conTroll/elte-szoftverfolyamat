<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment">
    <div class="ui reply form">
        <div class="field">
            <textarea id="postText"></textarea>
        </div>
        <div class="ui blue labeled submit icon button"
             onclick="alert('message sent');">
            <i class="icon edit"></i> Send message
        </div>
    </div>
</div>
<div class="ui segment">
    <div class="ui comments">
        <h3 class="ui dividing header">Messages</h3>

        <c:forEach var="message" items="${messages}">
            <div id="message${message.id}" class="comment">
                <div class="avatar">
                    <img src="/images/avatar/small/joe.jpg">
                </div>
                <div class="content">
                    <a class="author">${message.userFrom.fullName}</a>
                    <div class="metadata">
                        <span class="date">${message.createdAt}</span>
                    </div>
                    <div class="text">

                        <c:choose>
                            <c:when test="${message.viewed == false}">
                                <b>${message.text}</b>
                            </c:when>
                            <c:otherwise>
                                ${message.text}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
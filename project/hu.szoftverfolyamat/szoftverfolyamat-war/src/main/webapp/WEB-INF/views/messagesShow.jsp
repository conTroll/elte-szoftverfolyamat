<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment" rel="${otherPartyId}">
    <div class="ui reply form">
        <div class="field">
            <textarea id="messageText"></textarea>
        </div>
        <div class="ui blue labeled submit icon button" onclick="submitMessage($(this).parent().parent().attr('rel'), $( '#messageText' ).val());">
            <i class="icon edit"></i> Send message
        </div>
    </div>
</div>
<div class="ui segment">
    <div class="ui comments">
        <h3 class="ui dividing header">Messages</h3>

        <c:forEach var="message" items="${messages}">
            <div id="message${message.id}" class="comment" rel="${message.id}">
                <div class="avatar">
                    <img src="<c:url value="/getImage/${message.userFrom.avatarId}"/>">
                </div>
                <div class="content">
                    <a class="author">${message.userFrom.fullName}</a>
                    <div class="metadata">
                        <span class="date">${message.createdAt}</span>
                    </div>
                    <div class="text">
                        <c:choose>
                            <c:when test="${message.viewed == false && message.userTo.credentialId == currentUserId}">
                                <b>${message.text}</b>
                            </c:when>
                            <c:otherwise>
                                ${message.text}
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${message.userFrom.credentialId == currentUserId}">
                        <div class="actions">
                            <a class="deleteMessage">Delete</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">
    $(".deleteMessage").click(function() {
        deleteMessage($(this).parent().parent().parent().attr('rel'));
    });
</script>
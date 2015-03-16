<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment">
    <div class="ui comments">
        <h3 class="ui dividing header">Chats</h3>

        <c:forEach var="chat" items="${chats}">
            <div id="chat${chat.key.credentialId}" rel="${chat.key.credentialId}" class="comment">
                <div class="avatar">
                    <img src="/images/avatar/small/joe.jpg">
                </div>
                <div class="content">
                    <a class="author">${chat.key.fullName}</a>
                    <div class="metadata">
                        <span class="date">${chat.value.createdAt}</span>
                    </div>
                    <div class="text">

                        <c:choose>
                            <c:when test="${chat.value.viewed == false && chat.value.userTo.credentialId == currentUserId}">
                                <b>${chat.value.text}</b>
                            </c:when>
                            <c:otherwise>
                                ${chat.value.text}
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="actions">
                        <a class="openChat">Open chat</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">
    $(".openChat").click(function() {
        loadMessagesWithUser($(this).parent().parent().parent().attr('rel'));
    });
</script>
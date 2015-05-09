function loadNews() {
	$.get( "news", function( data ) {
		changeContent(data);
	});
}

function loadShowContacts() {
	$.get( "viewContacts", function( data ) {
		changeContent(data);
	});
}

function loadSearchUsers() {
	$.get( "searchContacts", function( data ) {
		changeContent(data);
	});
}

function loadBrowseChannels() {
	$.get( "channels/search", function( data ) {
		changeContent(data);
	});
}

function loadSubscribedChannels() {
	$.get( "channels/show_subscriptions", function( data ) {
		changeContent(data);
	});
}

function loadInterests() {
    $.get( "interests", function( data ) {
        changeContent(data);
    });
}


function loadInterestPage(id) {
    $.get( "interests/show/" + id, function( data ) {
        changeContent(data);
    });
}

function loadSettings() {
	$.get( "profile", function( data ) {
		changeContent(data);
	});
}

function loadOwnChannels() {
	$.get( "channels/show_own", function( data ){
		changeContent(data);
	});
}

function refreshMessageCounter() {
    $.ajax({
        type: "POST",
        url: "messages/counter",
        contentType: "text/plain",
        success: function( data ) {
            $('#unreadMessageCounter').replaceWith('<span id="unreadMessageCounter">' + data + '</span>');
        }
    });
}

function changeContent( data ) {
	$("i.loader").removeClass("active");
		
	$('#dynamicContent').fadeOut("fast", function() {
		$('#dynamicContent').html( data );
		$('#dynamicContent').fadeIn("fast");
	});
	//Find all inline script tags in the new content and loop through them
	$(data).find("script").each(function(index, scriptContent) {
        eval($(scriptContent).html()); //Execute the content
    });

    refreshMessageCounter();

}

function submitPost() {
	var data = {
		"text" : $( "#postText" ).val()
	};
	$.ajax({
		  type: "POST",
		  url: "createPost",
          //dataType: "json",
          contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

function submitComment(id) {
	var data = {
		"postId" : id,
		"text" : $( "#replyText" ).val()
	};
	$.ajax({
		  type: "POST",
		  url: "createComment",
          //dataType: "json",
          contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

function deletePost(id) {
	var data = {
		"id" : id
	};
	$.ajax({
		  type: "POST",
		  url: "deletePost",
          //dataType: "json",
          contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

function deleteComment(id) {
	var data = {
		"id" : id
	};
	$.ajax({
		  type: "POST",
		  url: "deleteComment",
          //dataType: "json",
          contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

//function addContact(id) {
//	var data = {
//		"id" : id
//	};
//	$.ajax({
//		  type: "POST",
//		  url: "addContact",
//          //dataType: "json",
//          contentType: "application/json",
//		  data: JSON.stringify(data),
//		  success: function( data ) {
//				changeContent(data);
//		  }
//	});
//}

function addContact(id) {
	var data = {
		"id" : id
	};
	$.ajax({
		  type: "POST",
		  url: "addContact",
		  data: data,
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

//function deleteContact(id) {
//	var data = {
//		"id" : id
//	};
//	$.ajax({
//		  type: "POST",
//          //dataType: "json",
//          contentType: "application/json",
//		  url: "deleteContact",
//		  data: JSON.stringify(data),
//		  success: function( data ) {
//				changeContent(data);
//		  }
//	});
//}

function deleteContact(id) {
	var data = {
		"id" : id
	};
	$.ajax({
		  type: "POST",
		  url: "deleteContact",
		  data: data,
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

function searchContacts() {
	var data = {
		"email" : $( "#email" ).val(),
		"fullName" : $( "#fullName" ).val(),
		"place" : $( "#place" ).val(),
		"job" : $( "#job" ).val()
	};
	$.ajax({
		  type: "POST",
		  url: "searchContacts",
          //dataType: "json",
          contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

function searchChannels() {
	var data = {
		"searchTerm" : $( "#searchTerm" ).val()
	};
	$.ajax({
		  type: "POST",
		  url: "channels/search",
          //dataType: "json",
          contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

// MESSAGES

function loadMessages() {
    $.get("messages/show_all", function( data ) {
        changeContent(data);
    });
}

function loadMessagesWithUser( id ) {
    $.get("messages/show/" + id, function( data ) {
        changeContent(data);
    });
}

function submitMessage(id, text) {
    var data = {
        "recipientId" : id,
        "text" : text
    };
    $.ajax({
        type: "POST",
        url: "messages/create",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function( data ) {
            changeContent(data);
        }
    });
}

function deleteMessage( id ) {
    var data = {
        "id" : id
    };
    $.ajax({
        type: "POST",
        url: "messages/delete",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function( data ) {
            changeContent(data);
        }
    });
}
    
function loadProfileView( id ) {
	$.get( "profileView?userId=" + id, function( data ) {
		changeContent(data);
	});
}

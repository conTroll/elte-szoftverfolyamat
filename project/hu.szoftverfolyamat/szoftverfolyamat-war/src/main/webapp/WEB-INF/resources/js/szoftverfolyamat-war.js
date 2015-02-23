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
	$.get( "browseChannels", function( data ) {
		changeContent(data);
	});
}

function changeContent( data ) {
	$('#dynamicContent').fadeOut("fast", function() {
		$('#dynamicContent').html( data );
		$('#dynamicContent').fadeIn("fast");
	});
	//Find all inline script tags in the new content and loop through them
	data.find("script").each(function() {
        var scriptContent = $('#dynamicContent').html(); //Grab the content of this tag
        eval(scriptContent); //Execute the content
    });
}

function submitPost() {
	var data = {
		"text" : $( "#postText" ).val()
	};
	$.ajax({
		  type: "POST",
		  url: "createPost",
		  data: data,
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
		  data: data,
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
		  data: data,
		  success: function( data ) {
				changeContent(data);
		  }
	});
}

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
		  data: JSON.stringify(data),
		  success: function( data ) {
				changeContent(data);
		  }
	});
}
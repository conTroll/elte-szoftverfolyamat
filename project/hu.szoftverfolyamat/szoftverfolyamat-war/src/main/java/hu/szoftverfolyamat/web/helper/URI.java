package hu.szoftverfolyamat.web.helper;

public final class URI {

	private URI() {
		throw new AssertionError("Should not instantiate!");
	}

	// GENERAL
	public static final String SHOW_BY_ID = "/show/{id}";
	public static final String SHOW_ALL = "/show_all";
	public static final String SHOW_OWN = "/show_own";
	public static final String CREATE = "/create";
	public static final String DELETE = "/delete";
	public static final String SEARCH = "/search";

	// INDEX
	public static final String INDEX = "/";

	// POSTS
	public static final String POSTS_SHOW = "/news";
	public static final String POSTS_CREATE = "/createPost";
	public static final String POSTS_DELETE = "/deletePost";

	// COMMENTS
	public static final String COMMENTS_CREATE = "/createComment";
	public static final String COMMENTS_DELETE = "/deleteComment";

	// CHANNELS
	public static final String CHANNELS = "/channels";

	// CONTACTS
	public static final String CONTACTS_SHOW = "/viewContacts";
	public static final String CONTACTS_ADD = "/addContact";
	public static final String CONTACTS_SEARCH = "/searchContacts";
	public static final String CONTACTS_DELETE = "/deleteContact";

	// USER MANAGEMENT
	public static final String USER_LOGIN = "/login";
	public static final String USER_REGISTRATION = "/registration";
	public static final String USER_PROFILE = "/profile";

	// MESSAGES
	public static final String MESSAGES = "/messages";

	// IMAGES
	public static final String GET_IMAGE = "/getImage";
	public static final String UPLOAD_IMAGE = "/uploadImage";
}

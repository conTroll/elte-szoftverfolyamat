package hu.szoftverfolyamat.web.helper;

public final class URI {

    private URI() {
        throw new AssertionError("Should not instantiate!");
    }

    // GENERAL
    public static final String SHOW_BY_ID = "/show/{id}";
    public static final String SHOW_ALL = "/show_all";
    public static final String CREATE = "/create";
    public static final String DELETE = "/delete";

    // INDEX
    public static final String INDEX = "/site";

    // POSTS
    public static final String POSTS_SHOW = "/news";
    public static final String POSTS_CREATE = "/createPost";
    public static final String POSTS_DELETE = "/deletePost";

    // COMMENTS
    public static final String COMMENTS_CREATE = "/createComment";
    public static final String COMMENTS_DELETE = "/deleteComment";

    // CHANNELS
    public static final String CHANNELS_BROWSE = "/browseChannels";
    public static final String CHANNELS_MINE = "/myChannels";

    // CONTACTS
    public static final String CONTACTS_SHOW = "/viewContacts";
    public static final String CONTACTS_ADD = "/addContact";
    public static final String CONTACTS_SEARCH = "/searchContacts";
    public static final String CONTACTS_DELETE = "/deleteContact";

    // USER MANAGEMENT
    public static final String USER_LOGIN = "/login";
    public static final String USER_REGISTRATION = "/registration";

    // MESSAGES
    public static final String MESSAGES = "/messages";
}
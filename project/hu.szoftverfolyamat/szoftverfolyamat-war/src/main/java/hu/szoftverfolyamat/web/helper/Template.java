package hu.szoftverfolyamat.web.helper;

public final class Template {

    private Template() {
        throw new AssertionError("Should not instantiate!");
    }

    public static final String INDEX = "site";

    public static final String POSTS_SHOW = "news";

    public static final String CREATE_CHANNEL = "createChannel";
    public static final String CHANNELS_BROWSER = "browseChannels";
    public static final String CHANNELS_MINE = "myChannels";

    public static final String CONTACTS_SHOW = "viewContacts";
    public static final String CONTACTS_SEARCH = "searchContacts";

    public static final String USER_LOGIN = "login";
    public static final String USER_REGISTRATION = "registration";

    public static final String MESSAGES_SHOW = "messagesShow";
    public static final String MESSAGES_SHOW_ALL = "messagesShowAll";
    
    
}

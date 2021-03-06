package hu.szoftverfolyamat.web.helper;

public final class Template {

    private Template() {
        throw new AssertionError("Should not instantiate!");
    }

    public static final String INDEX = "site";

    public static final String POSTS_SHOW = "news";

    public static final String CREATE_CHANNEL = "createChannel";
    public static final String SEARCH_CHANNELS = "searchChannels";
    public static final String CHANNEL_SEARCH_RESULTS = "channelSearchResults";
    public static final String CHANNELS_MINE = "myChannels";
    public static final String CHANNELS_SUBSCRIPTIONS = "subscriptions";

    public static final String CONTACTS_SHOW = "viewContacts";
    public static final String CONTACTS_SEARCH = "searchContacts";

    public static final String USER_LOGIN = "login";
    public static final String USER_REGISTRATION = "registration";
    public static final String USER_PROFILE = "profile";

    public static final String MESSAGES_SHOW = "messagesShow";
    public static final String MESSAGES_SHOW_ALL = "messagesShowAll";
    
    public static final String PROFILE_VIEW = "profileView";

    public static final String INTEREST_DASHBOARD = "interestDashboard";
    public static final String INTEREST_PAGE = "interestPage";
    
    public static final String RECOMMENDED_FRIENDS = "recommendedFriends";
    public static final String RECOMMENDED_FRIENDS_PAGE = "recommendedFriendsPage";
}

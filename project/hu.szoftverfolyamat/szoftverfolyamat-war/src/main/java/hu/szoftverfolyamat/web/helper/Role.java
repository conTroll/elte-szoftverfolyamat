package hu.szoftverfolyamat.web.helper;

public class Role {

    private Role() {
        throw new AssertionError("Should not instantiate!");
    }

    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
}

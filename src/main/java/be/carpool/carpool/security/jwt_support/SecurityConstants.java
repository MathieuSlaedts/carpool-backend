package be.carpool.carpool.security.jwt_support;

public class SecurityConstants {

    public static final String JWT_ENCRYPTION_KEY = "M@_Cl3F_D'3nCrYp7aG3";
    public static final long JWT_EXPIRATION_TIME = 864000000;
    public static final String JWT_PREFIX = "Bearer ";
    public static final String JWT_HEADER_KEY = "Authorization";
    public static final String REGISTER_URL = "/users/sign-up";
    public static final String LOGIN_URL = "/users/sign-in";
}

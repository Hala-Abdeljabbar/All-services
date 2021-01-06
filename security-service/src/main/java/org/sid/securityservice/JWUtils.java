package org.sid.securityservice;

public class JWUtils {

    public static final String SECRET = "mySecret1234";
    public static String AUTH_HEADER = "authorization";
    public static final long  EXPIRE_ACCESS = 2*60*1000;
    public static final long  REFRESH_ACCESS = 2*60*1000;
}

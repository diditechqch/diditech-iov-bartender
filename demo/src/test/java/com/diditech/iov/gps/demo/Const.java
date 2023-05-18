package com.diditech.iov.gps.demo;

/**
 * @author zhjd <br>
 * @date 2023/5/9 <br>
 */
public class Const {
    public static String GATEWAY_REQUEST = "http://localhost:9000/api";

    private static String AUTH_HOST = "http://localhost:8080";

    public static String AUTH_REQUEST = AUTH_HOST + "/oauth/token?grant_type=client_credentials&client_id=%s&client_secret=%s";

    public static String CLIENT = "";

    public static String CLIENT_SECRET = "";

}

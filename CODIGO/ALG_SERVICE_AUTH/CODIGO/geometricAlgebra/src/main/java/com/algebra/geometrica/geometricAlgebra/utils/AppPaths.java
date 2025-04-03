package com.algebra.geometrica.geometricAlgebra.utils;

public class AppPaths {

    public static class Auth {
        public static final String AUTH_PATH = "/auth";
        public static final String AUTHENTICATE = "/authenticate";
        public static final String AUTH_LOGIN = AUTHENTICATE + "/login";
        public static final String AUTH_REGISTER = AUTHENTICATE + "/register";
        public static final String REFRESH_TOKEN = AUTHENTICATE + "/refresh";

    }

    public static class Security{
        public static final String SECURITY_LOGIN_PATH = "/login";
        public static final String SECURITY_REGISTER_PATH = "/register";
        public static final String SECURITY_REFRESH_PATH = "/refresh";
    }


}

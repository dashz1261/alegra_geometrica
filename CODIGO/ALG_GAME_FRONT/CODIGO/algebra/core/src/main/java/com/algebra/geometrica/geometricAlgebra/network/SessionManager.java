package com.algebra.geometrica.geometricAlgebra.network;

public class SessionManager {

    private static String token;

    public static void setToken(String jwt){
        token = jwt;
    }

    public static String getToken(){
        return token;
    }
}

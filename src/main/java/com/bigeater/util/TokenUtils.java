package com.bigeater.util;

/**
 * 描述:
 *
 * @author J
 * @create 2018-12-23 15:07
 */
public class TokenUtils {

    private static final ThreadLocal<String> LOCAL_TOKEN = new ThreadLocal<>();

    /**
     * 设置 token
     * @param token
     */
    public static final void setToken(String token){
        LOCAL_TOKEN.set(token);
    }

    /**
     * 获取 token
     * @return
     */
    public static final String getToken(){
        String token = LOCAL_TOKEN.get();
        LOCAL_TOKEN.remove();
        return token;
    }
}
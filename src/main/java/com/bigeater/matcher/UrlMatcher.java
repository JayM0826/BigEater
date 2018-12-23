package com.bigeater.matcher;

/**
 * 描述:
 *
 * @author J
 * @create 2018-12-22 1:02
 */
public interface UrlMatcher {
    Object compile(String paramString);
    boolean pathMatchesUrl(Object paramObject, String paramString);
    String getUniversalMatchPattern();
    boolean requiresLowerCaseUrl();
}

package xin.stxkfzx.weekend.auth.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie工具类
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@SuppressWarnings("all")
public final class CookieUtils {

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request    request
     * @param cookieName cookieName
     * @return Cookie值
     * @author ViterTian
     * @date 2019-04-13
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, null);
    }

    /**
     * 得到Cookie的值,
     *
     * @param request    request
     * @param cookieName cookieName
     * @return Cookie值
     * @author ViterTian
     * @date 2019-04-13
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String charset) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    if (charset != null && charset.length() > 0) {
                        retValue = URLDecoder.decode(cookie.getValue(), charset);
                    } else {
                        retValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException ignored) {
            ignored.printStackTrace();
        }
        return retValue;
    }

    public static CookieBuilder newBuilder(HttpServletResponse response) {
        return new CookieBuilder(response);
    }

    public static class CookieBuilder {
        private HttpServletRequest request;
        private HttpServletResponse response;
        private Integer maxAge;
        private String charset;
        private boolean httpOnly = false;

        public CookieBuilder(HttpServletResponse response) {
            this.response = response;
        }

        public CookieBuilder request(HttpServletRequest request) {
            this.request = request;
            return this;
        }

        public CookieBuilder maxAge(int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public CookieBuilder charset(String charset) {
            this.charset = charset;
            return this;
        }

        public CookieBuilder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public void build(String cookieName, String cookieValue) {
            try {
                if (StringUtils.isBlank(charset)) {
                    charset = "utf-8";
                }

                if (cookieValue == null) {
                    cookieValue = "";
                } else if (StringUtils.isNotBlank(charset)) {
                    cookieValue = URLEncoder.encode(cookieValue, charset);
                }
                Cookie cookie = new Cookie(cookieName, cookieValue);
                if (maxAge != null && maxAge > 0) {
                    cookie.setMaxAge(maxAge);
                }
                // 设置域名的cookie
                if (null != request) {
                    cookie.setDomain(getDomainName(request));
                }
                cookie.setPath("/");

                cookie.setHttpOnly(httpOnly);
                response.addCookie(cookie);
            } catch (Exception e) {
            }
        }

        /**
         * 得到cookie的域名
         *
         * @param request request
         * @return cookie的域名
         * @author ViterTian
         * @date 2019-04-13
         */
        private String getDomainName(HttpServletRequest request) {
            String domainName = null;

            String serverName = request.getRequestURL().toString();
            if ("".equals(serverName)) {
                domainName = "";
            } else {
                serverName = serverName.toLowerCase();
                serverName = serverName.substring(7);
                final int end = serverName.indexOf("/");
                serverName = serverName.substring(0, end);
                final String[] domains = serverName.split("\\.");
                int len = domains.length;
                if (len > 3) {
                    // www.xxx.com.cn
                    domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
                } else if (len <= 3 && len > 1) {
                    // xxx.com or xxx.cn
                    domainName = domains[len - 2] + "." + domains[len - 1];
                } else {
                    domainName = serverName;
                }
            }

            if (domainName != null && domainName.indexOf(":") > 0) {
                String[] ary = domainName.split("\\:");
                domainName = ary[0];
            }
            return domainName;
        }
    }


}

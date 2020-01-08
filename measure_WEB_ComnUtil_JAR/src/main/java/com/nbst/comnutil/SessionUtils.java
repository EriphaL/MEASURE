/**
 * 
 */
package com.nbst.comnutil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;


/**
 * @author hellozjf
 *
 */
public class SessionUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
    
    public static Object get(Object key) {
        return getSession().getAttribute(key);
    }
    
    public static void set(Object key, Object value) {
        getSession().setAttribute(key, value);
    }
}

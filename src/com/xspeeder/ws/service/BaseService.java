/**
 * Copyright Jog Web Development Team
 */


package com.xspeeder.ws.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.xspeeder.common.exception.XException;
import com.xspeeder.common.util.ContextUtils;
import com.xspeeder.ws.common.BaseLocale;
import com.xspeeder.ws.common.WSErrors;

import strongshine.base.Const;

@Controller
public class BaseService extends BaseLocale {

    static Logger log = Logger.getLogger(BaseService.class.getName());

    public static final String DEFAULT_LOCALE = "en_US";

    protected Long uid() {
        Long uid = (Long) ContextUtils.session().getAttribute(Const.SESS_UID);
        return uid;

//		return new Long(3);
    }

    protected String locale() {
        String locale = (String) this.getSessionData(Const.SESS_LOCALE);

        if (locale == null) return DEFAULT_LOCALE;

        return locale;
    }

    protected Object getSessionData(String key) {
        return ContextUtils.session().getAttribute(key);
    }

    protected void setSessionData(String key, Object value) {
        ContextUtils.session().setAttribute(key, value);
    }

    protected String getIpAddr() {
        HttpServletRequest req = ContextUtils.request();

        //is client behind something?
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        return ipAddress;
    }

    protected String referer() {
        HttpServletRequest req = ContextUtils.request();
        return req.getHeader("Referer");
    }

    protected String configErrorResult(String errCode, Object errContent) {

        String jsonErr;

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("type", errCode);
        result.put("content", errContent);

        Gson gson = new Gson();
        jsonErr = gson.toJson(result);

        return jsonErr;
    }

    protected String parseException(Exception e) {
        if (e instanceof XException)
            return this.configErrorResult(WSErrors.E_FAIL, this.getMsg(((XException) e).getErrCode()));
        else return this.configErrorResult(WSErrors.E_FAIL, this.getMsg(WSErrors.ERR_UNKNOWN));
    }

    protected String getLanguage(String lang) {
        String language = this.locale();
        if (lang != null) {
            this.setSessionData(Const.SESS_LOCALE, lang);
            language = lang;
        } else if (language == null) {
            this.setSessionData(Const.SESS_LOCALE, BaseService.DEFAULT_LOCALE);
            language = BaseService.DEFAULT_LOCALE;
        }
        return language;
    }
}

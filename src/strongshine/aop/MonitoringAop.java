/**
 * Copyright University Development Team
 * All rights reserved.
 */

package strongshine.aop;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.xspeeder.common.exception.XException;
import com.xspeeder.ws.service.BaseService;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xspeeder.common.util.ContextUtils;
import com.xspeeder.common.util.DateTimeUtil;
import com.xspeeder.ws.common.MediaType;
import com.xspeeder.ws.common.WSErrors;

import strongshine.base.Const;
import strongshine.aop.annotations.CrossCut;

/**
 * 언어설정 같은 횡단처리를 진행하기 위한 Aspect 클라스
 *
 * @author :  rgy713
 * @version : 1.0
 * @date : 2017. 5. 12.
 */
@Aspect
@Component
@Scope("request")
public class MonitoringAop {

    @Pointcut("@annotation(strongshine.aop.annotations.CrossCut)")
    private void monitorPointCut() {
    }

    static Logger log = Logger.getLogger(MonitoringAop.class.getName());

    /**
     * 오유코드와 오유통보문으로부터 JSON형태의 오유통보문자렬을 생성한다.
     *
     * @param errCode    - 오유코드
     * @param errContent - 오유내용
     * @return json형식의 문자렬
     * @author Ryang.WunHyok
     * @date Mar 10, 2015
     */
    private String configErrorResult(String errCode, Object errContent) {

        String jsonErr;

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("type", errCode);
        result.put("content", errContent);

        Gson gson = new Gson();
        jsonErr = gson.toJson(result);

        return jsonErr;
    }

    @Around("monitorPointCut()")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method jp_method = signature.getMethod();

        // 메쏘드 @CrossCutting annotation 얻기
        CrossCut crossCuttingAnno = (CrossCut) jp_method.getAnnotation(CrossCut.class);
        Object ret = null;

        try {

            if (crossCuttingAnno.setLanguage()) {
                //---------------------------------------------
                // TODO add logic to log the operation
                //---------------------------------------------
                    /*
                     * 파라메터목록을 만든다.
	        		 */
                Map<String, Object> methodParams = new HashMap<String, Object>();

                Object[] params = pjp.getArgs();
                Object[] paramNames = signature.getParameterNames();
                for (int i = 0; i < params.length; i++) {

                    methodParams.put(paramNames[i].toString(), params[i]);

                }
                // 언어종류를 얻는다.
                String lang = methodParams.get("lang").toString();
                String language = (String) ContextUtils.session().getAttribute(Const.SESS_LOCALE);
                if (lang != null) {
                    ContextUtils.session().setAttribute(Const.SESS_LOCALE, "en_US");
                } else if (language == null) {
                    ContextUtils.session().setAttribute(Const.SESS_LOCALE, BaseService.DEFAULT_LOCALE);
                }
            }

            ret = pjp.proceed();

            return null;

        } catch (Throwable e) {

            e.printStackTrace();
            writeErrorResponse(crossCuttingAnno.respType(), WSErrors.ERR_UNKNOWN);
        }

        return null;

    }

    /**
     * 문자렬결과를 응답객체에 넣는다.
     *
     * @param respType 응답형태
     * @param ret      응답결과문자렬
     * @author Ryang.WunHyok
     * @date May 12, 2015
     */
    public void writeResultToResp(String respType, String ret) {
        ContextUtils.response().setCharacterEncoding("UTF-8");
        ContextUtils.response().setContentType(respType);
        ContextUtils.response().setContentLengthLong(ret.getBytes().length);

        try {
            ContextUtils.response().getOutputStream().write(ret.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeErrorResponse(String resptype, String errcode) {
        String result = this.configErrorResult(WSErrors.E_FAIL, errcode);

        if (MediaType.APPLICATION_OCTET_STREAM.equals(resptype))
            this.writeResultToResp(MediaType.APPLICATION_JSON, result);
        else this.writeResultToResp(resptype, result);
    }
}
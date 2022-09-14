package com.qingtian.lcpes.base.web;

import com.qingtian.lcpes.base.exception.QTBusinessException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author zail
 * @date 2022/2/24
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ApplicationErrorController implements ErrorController {

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, ModelMap model) {
        model.put("status", getStatusCode(request));
        model.put("timestamp", new Date());
        model.put("path", getErrorUri(request));
        model.put("code", ResponseStatusEnum.ERR.getCode());
        model.put("message", getError(request).getMessage());
        return new ModelAndView("thymeleaf/error", model);
    }

    @RequestMapping
    @ResponseBody
    public JsonResponse<String> error(HttpServletRequest request) {
        JsonResponse<String> jsonResponse = new JsonResponse<>();
        jsonResponse.setRetCode("500");
        jsonResponse.setRetDesc("访问错误！");
        return jsonResponse;
    }

    public static Exception getError(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute(DefaultErrorAttributes.class.getName() + ".ERROR");
        if (exception == null) {
            exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        }
        if (exception == null) {
            exception = new QTBusinessException(ResponseStatusEnum.ERR.getMessage());
        }
        return exception;
    }

    public static String getErrorUri(HttpServletRequest request) {
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (request.getQueryString() != null) {
            uri += "?" + request.getQueryString();
        }
        return uri;
    }

    public static Integer getStatusCode(HttpServletRequest request) {
        return (Integer) request.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    @Deprecated // 在 2.5.0 版本以后就移除了
    public String getErrorPath() {
        return null;
    }
}

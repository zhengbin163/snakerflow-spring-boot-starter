package com.ai.k8splus.snaker.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 透传header 和attribute 到feign中
 */

public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
        // 设置request中的attribute到header:主要是设置自行设置的token、userId等信息，以便转发到Feign调用的服务
//        Enumeration<String> reqAttrbuteNames = request.getAttributeNames();
//        if (reqAttrbuteNames != null) {
//            while (reqAttrbuteNames.hasMoreElements()) {
//                String attrName = reqAttrbuteNames.nextElement();
//                String values = request.getAttribute(attrName).toString();
//                requestTemplate.header(attrName, values);
//            }
//        }
    }


}

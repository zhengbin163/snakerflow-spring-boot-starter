package com.ai.k8splus.snaker.config;

import com.ai.k8splus.core.bean.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.reflect.Type;

public class FeignResultDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.body() == null) {
            throw new DecodeException("没有返回有效的数据");
        }
        String bodyStr = Util.toString(response.body().asReader());

        try {
            Result result = JSONObject.parseObject(bodyStr, Result.class);
            if(result.getContent() instanceof JSONArray){
                return JSONArray.parseArray(JSONObject.toJSONString(result.getContent()), (Class)((ParameterizedTypeImpl) type).getActualTypeArguments()[0]);
            }else{
                return JSONObject.parseObject(JSONObject.toJSONString(result.getContent()), (Class) type);
            }

        } catch (Exception e) {
            Object o = this.json2obj(bodyStr, type);
            return o;
        }
    }

    private <T> T json2obj(String jsonStr, Type targetType) {
        try {
            JavaType javaType = TypeFactory.defaultInstance().constructType(targetType);
            return new ObjectMapper().readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException("将JSON转换为对象时发生错误:" + jsonStr, e);
        }
    }

}

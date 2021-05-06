package com.ai.k8splus.snaker.service;

import com.ai.k8splus.core.util.Assert;
import com.ai.k8splus.snaker.common.exception.SnokerflowMsgEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 包装的工作流 总处理类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SnokerEngine {

    @Autowired
    private ISnakerService snokerService;

    /**
     * 提交业务申请数据，启动当前工作流
     */
    public void startProcessByName(String processName, Integer userId, Object data) throws Exception {
        Assert.isNotNull(data, SnokerflowMsgEnum.ARG_OBJS_IS_NOT_NULL);
        Assert.isNotNull(processName, SnokerflowMsgEnum.PROCESS_NAME_IS_NOT_NULL);
        Assert.isNotNull(userId, SnokerflowMsgEnum.OPERATOR_ID_IS_NOT_NULL);

        Map<String, Object> args = new HashMap<>();
        args.put("data", JSONObject.toJSONString(data));
        snokerService.startProcessByName(processName, JSONObject.toJSONString(args));
    }

    /**
     * 提交业务申请数据，启动当前工作流，并执行第一步操作
     */
    public void startProcessAndNext(String processName, Integer userId, Object data) throws Exception {
        Assert.isNotNull(data, SnokerflowMsgEnum.ARG_OBJS_IS_NOT_NULL);
        Assert.isNotNull(processName, SnokerflowMsgEnum.PROCESS_NAME_IS_NOT_NULL);
        Assert.isNotNull(userId, SnokerflowMsgEnum.OPERATOR_ID_IS_NOT_NULL);

        Map<String, Object> args = new HashMap<>();
        args.put("data", JSONObject.toJSONString(data));
        snokerService.startProcessAndNext(processName, JSONObject.toJSONString(args));
    }

    /**
     * 提交业务申请数据，启动当前工作流
     */
    public void next(String orderId, Integer userId, Object data) throws Exception {
        Assert.isNotNull(data, SnokerflowMsgEnum.ARG_OBJS_IS_NOT_NULL);
        Assert.isNotNull(orderId, SnokerflowMsgEnum.PROCESS_NAME_IS_NOT_NULL);
        Assert.isNotNull(userId, SnokerflowMsgEnum.OPERATOR_ID_IS_NOT_NULL);

        Map<String, Object> args = new HashMap<>();
        args.put("data", JSONObject.toJSONString(data));
        snokerService.nextByOrderId(orderId, JSONObject.toJSONString(args));
    }
}

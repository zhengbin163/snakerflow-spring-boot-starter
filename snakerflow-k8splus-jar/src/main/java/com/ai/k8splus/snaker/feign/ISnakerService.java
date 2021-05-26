package com.ai.k8splus.snaker.feign;

import com.ai.k8splus.core.bean.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(url = "${edge-server.registry}/snaker",value = "snakerService")
public interface ISnakerService {

    @PostMapping(value = "/start/{processId}")
    public Result<String> startProcessById(@PathVariable("processId") String processId, @RequestParam("data") String data, @RequestParam(value = "orderNo", required = false) String orderNo);
    @PostMapping(value = "/start/name/{processName}")
    public Result<String> startProcessByName(@PathVariable("processName") String processName, JSONObject data, @RequestParam(value = "orderNo", required = false) String orderNo);
    @PostMapping(value = "/start/next/{processName}")
    Result<String> startProcessAndNext(@PathVariable("processName") String processName, JSONObject data, @RequestParam(value = "orderNo", required = false) String orderNo);
    @PostMapping(value = "/next/order/{orderId}")
    Result<String> nextByOrderId(@PathVariable("orderId") String orderId, JSONObject data);
    @PostMapping(value = "/next/task/{taskId}")
    public Result<String> nextByTaskId(@PathVariable("taskId") String taskId, JSONObject data);
    /**
     * 终止当前工单
     * @param orderId
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/terminate/orderid/{orderId}")
    public Result<String> terminateByOrderId(@PathVariable("orderId") String orderId, @RequestParam("errMsg") String errMsg) throws Exception;

    /**
     * 终止当前工单
     * @param type
     * @param orderNo
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/terminate/{type}/{orderNo}")
    public Result<String> terminateByOrderNo(@PathVariable("type") String type, @PathVariable("orderNo") String orderNo, @RequestParam("errMsg") String errMsg);

    /**
     * 获取当前工单的业务数据
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/task/current/busidata/{orderId}")
    public Map<String, Object> getCurrTaskBusiData(@PathVariable("orderId") String orderId);

    @GetMapping(value = "/task/current/busidata/type/{type}/orderno/{orderNo}")
    public JSONObject getCurrTaskBusiData(@PathVariable("type") String type, @PathVariable("orderNo") String orderNo) throws Exception;
}

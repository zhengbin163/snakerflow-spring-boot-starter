package com.ai.k8splus.snaker.feign;

import com.ai.k8splus.core.bean.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${edge-server.registry}/snaker",value = "snakerService")
public interface ISnakerService {

    @PostMapping(value = "/start/{processId}")
    public Result<String> startProcessById(@PathVariable("processId") String processId, @RequestParam("data") String data);
    @PostMapping(value = "/start/name/{processName}")
    public void startProcessByName(@PathVariable("processName") String processName, JSONObject data);
    @PostMapping(value = "/start/next/{processName}")
    String startProcessAndNext(@PathVariable("processName") String processName, JSONObject data);
    @PostMapping(value = "/next/order/{orderId}")
    Result<String> nextByOrderId(@PathVariable("orderId") String orderId, JSONObject data);
    @PostMapping(value = "/next/task/{taskId}")
    public Result<String> nextByTaskId(@PathVariable("taskId") String taskId, JSONObject data);
}

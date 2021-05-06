package com.ai.k8splus.snaker.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${REGISTRY_URL}/snaker",value = "snokerService")
public interface ISnakerService {

    @PostMapping("/start/{processId}")
    void startProcessByName(@PathVariable String processId, @RequestParam("data") String data);

    @PostMapping(value = "/start/next/{processName}")
    void startProcessAndNext(@PathVariable String processName, @RequestBody String data);

    @PostMapping(value = "/next/order/{orderId}")
    void nextByOrderId(@PathVariable String orderId, @RequestBody String data);

    @PostMapping(value = "/next/task/{taskId}")
    JSONObject nextByTaskId(@PathVariable String taskId, @RequestBody String data);
}

package com.ai.k8splus.snaker.common.bean;

import java.util.HashMap;
import java.util.Map;

public class SFTaskInfoBean {

    /**
     * 当前流程
     */
    protected Process process;

    /**
     * 当前操作者
     */
    protected String operator;

    /**
     * snokerflow 传值
     */
    protected Map<String, Object> args = new HashMap<>();

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public void argsPutData(Object data){
        this.args.put("data", data);
    }
}

package com.ai.k8splus.snaker.common.exception;

import com.ai.k8splus.core.exception.IBizExceptionMes;

public enum SnokerflowMsgEnum implements IBizExceptionMes {
    ARG_OBJS_IS_NOT_NULL(16001, "页面参数填写不能为空！"),
    PROCESS_NAME_IS_NOT_NULL(16002, "流程编码不能为空！"),
    OPERATOR_ID_IS_NOT_NULL(16003, "任务执行人ID不能为空！"),
    ORDER_ID_IS_NOT_NULL(16004, "工单ID不能为空！"),
    ORDER_NO_IS_NOT_NULL(16005, "业务单号不能为空！"),
    ORDER_TYPE_IS_NOT_NULL(16006, "业务类型不能为空！"),





    FLOW_TYPE_IS_NOT_CORRECT(16001, "工作流类型值不正确，无法匹配到有效的工作流定义！"),
    FLOW_PROCESS_IS_NOT_FOUND(16002, "工作流定义在数据库中不存在！"),
    HANDLER_SERVICE_IS_NOT_CORRECT(16004, "任务处理类不正确，请与管理员联系！"),


    ;

    /**
     * 异常信息
     */
    private String msg;
    /**
     * 异常编码
     */
    private int code;

    /**
     * 构造函数
     *
     * @param code 异常编码
     * @param mes  异常信息
     */
    SnokerflowMsgEnum(int code, String mes) {
        this.msg = mes;
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getCode() {
        return code;
    }


}
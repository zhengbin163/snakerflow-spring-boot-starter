package com.ai.k8splus.snaker.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作流类别，code要保证与process的name字段一致
 */
public enum SFProcessTypeEnum {

    //项目审批
    APP_APPLY("appapply", "", ""),
    METALB_APPLY("metalbapply", "", ""),

    ;
    private static final Map<String, SFProcessTypeEnum> map = new HashMap<>();

    private static final String[] codes = new String[SFProcessTypeEnum.values().length];
    static {
        for (int i = 0; i < SFProcessTypeEnum.values().length; i++) {
            SFProcessTypeEnum n = SFProcessTypeEnum.values()[i];
            map.put(n.getCode(), n);
            codes[i] = n.getCode();
        }
    }

    SFProcessTypeEnum(String code, String handlerServiceName, String handlerBeanName) {
        this.code = code;
        this.handlerServiceName = handlerServiceName;
        this.handlerBeanName = handlerBeanName;
    }

    /**
     * 编码
     */
    private String code;

    private String handlerServiceName;

    private String handlerBeanName;

    public static SFProcessTypeEnum valueOfByCode(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }

    public String getHandlerServiceName() {
        return handlerServiceName;
    }

    public String getHandlerBeanName() {
        return handlerBeanName;
    }

    public static String[] getCodes() {
        return codes;
    }
}

package com.test.service2.core;

import org.springframework.stereotype.Component;

@Component("requestScope")
@org.springframework.web.context.annotation.RequestScope
public class RequestScope {
    String tenantid;

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }
}

package com.test.service2.component;

import com.test.service2.core.TenantContext;
import com.test.service2.service.MultitenantService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MultiTenantAspect {

    @Before("execution(* com.test.service2.service.MultitenantService.* (..)) && target(multitenantService)")
    public void aroundExecution(JoinPoint pjp, MultitenantService multitenantService) throws Throwable {
        org.hibernate.Filter filter = multitenantService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenant_id", TenantContext.getCurrentTenant());
        filter.validate();
    }
}

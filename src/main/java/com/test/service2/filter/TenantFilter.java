package com.test.service2.filter;

import com.test.service2.core.TenantContext;
import com.test.service2.core.RequestScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter implements Filter {

    @Autowired
    RequestScope requestScope;

    @Autowired
    CacheManager cacheManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("Initialized filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tenantHeader = request.getHeader("X-TenantID");
        //System.out.println("tenant Hedaer " + tenantHeader);
        requestScope.setTenantid(tenantHeader);
        TenantContext.setCurrentTenant(tenantHeader);

        //System.out.println("***************Running Filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroyed filter");
    }
}

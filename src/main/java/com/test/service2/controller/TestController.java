package com.test.service2.controller;

import com.test.service2.entity.MultiTenant;
import com.test.service2.service.MultitenantService;
import com.test.service2.core.RequestScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/test/v1/service2"})
public class TestController {

    @Autowired
    RequestScope requestScope;

    public String GetTenantID()
    {
        return requestScope.getTenantid();
    }

    @Autowired
    MultitenantService multitenantService;

    @RequestMapping(value = "test1")
    @ResponseBody
    public String test1()
    {
        return "service2 : "+requestScope.getTenantid();
    }

    @RequestMapping(value = "list/{name}")
    @ResponseBody
    //(value = "tenants", key="#root.target.GetTenantID()+\":\"+#name",sync = true)
    @Cacheable(value = "tenants", key="#name",sync = true)
    public MultiTenant list(@PathVariable String name)
    {
        try {//simulate long running operation
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Getting List");
        return multitenantService.listTenant(name);
    }

    @RequestMapping(value = "create/{name}/{company}")
    @ResponseBody
    @CachePut(value = "tenants", key="#name")
    public MultiTenant create(@PathVariable String name,@PathVariable String company)
    {
        MultiTenant tenant1 = new MultiTenant();
        tenant1.setCompany(company);
        tenant1.setName(name);
        return multitenantService.CreateTenant(tenant1);
    }

    @RequestMapping(value = "delete/{name}")
    @ResponseBody
    @CacheEvict(value = "tenants", key="#name")
    public String delete(@PathVariable String name)
    {
        multitenantService.deleteTenant(name);
        return "deleted";
    }


}

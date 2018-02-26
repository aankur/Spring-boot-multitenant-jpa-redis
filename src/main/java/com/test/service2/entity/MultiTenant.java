package com.test.service2.entity;

import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "multitenant")
@Data
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenant_id", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenant_id")
public class MultiTenant extends Tenant implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String company;

}

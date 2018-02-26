package com.test.service2.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class Tenant implements Serializable{

    protected String tenant_id;

}

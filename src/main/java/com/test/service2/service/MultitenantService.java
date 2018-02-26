package com.test.service2.service;

import com.test.service2.entity.MultiTenant;
import com.test.service2.repo.MultiTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class MultitenantService {

    private final MultiTenantRepository multiTenantRepository;

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    public MultitenantService(MultiTenantRepository multiTenantRepository)
    {
        this.multiTenantRepository = multiTenantRepository;
    }

    @Transactional
    public MultiTenant listTenant(String name) {
        return multiTenantRepository.findByName(name);
    }

    @Transactional
    public MultiTenant CreateTenant(MultiTenant tenant) {
        return multiTenantRepository.save(tenant);
    }

    @Transactional
    public  void deleteTenant(String name)
    {
        multiTenantRepository.deleteByName(name);
    }
}

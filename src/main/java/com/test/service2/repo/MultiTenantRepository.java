package com.test.service2.repo;

import com.test.service2.entity.MultiTenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiTenantRepository  extends JpaRepository<MultiTenant,Long> {

    MultiTenant findByName(String name);

    void deleteByName(String name);
}

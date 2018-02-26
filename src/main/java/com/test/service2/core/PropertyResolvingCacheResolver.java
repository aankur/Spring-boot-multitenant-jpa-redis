package com.test.service2.core;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.core.env.PropertyResolver;

public class PropertyResolvingCacheResolver
        extends SimpleCacheResolver {

    private final PropertyResolver propertyResolver;

    public PropertyResolvingCacheResolver(CacheManager cacheManager, PropertyResolver propertyResolver) {
        super(cacheManager);
        this.propertyResolver = propertyResolver;
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        String currentTenant = TenantContext.getCurrentTenant();

        Collection<String> unresolvedCacheNames = super.getCacheNames(context);

        Collection<String> previousCollection = unresolvedCacheNames.stream()
                .map(unresolvedCacheName -> propertyResolver.resolveRequiredPlaceholders(unresolvedCacheName)).collect(Collectors.toList());

       return Collections.singletonList(currentTenant + ":" + previousCollection.iterator().next());
    }
}
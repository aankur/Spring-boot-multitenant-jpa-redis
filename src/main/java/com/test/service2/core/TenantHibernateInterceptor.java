package com.test.service2.core;

import com.test.service2.core.TenantContext;
import com.test.service2.entity.Tenant;
import org.hibernate.EmptyInterceptor;

import java.io.Serializable;

public class TenantHibernateInterceptor extends EmptyInterceptor {

    public TenantHibernateInterceptor()
    {
        System.out.println("Created TenantInterceptor");
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
        if (!(entity instanceof Tenant)) {
            return;
        }
        final int length = state.length;
        for (int i = 0; i < length; i++) {
            if(propertyNames[i].equals("tenant_id"))
            {
                state[i] = TenantContext.getCurrentTenant();
                break;
            }
        }
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, org.hibernate.type.Type[] types) {
        if (!(entity instanceof Tenant)) {
            return false;
        }
        final int length = currentState.length;
        for (int i = 0; i < length; i++) {
            if(propertyNames[i].equals("tenant_id"))
            {
                currentState[i] = TenantContext.getCurrentTenant();
                break;
            }
        }
        return true;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
        if (!(entity instanceof Tenant)) {
            return false;
        }
        final int length = state.length;
        for (int i = 0; i < length; i++) {
            if(propertyNames[i].equals("tenant_id"))
            {
                state[i] = TenantContext.getCurrentTenant();
                break;
            }
        }
        return true;
    }
}

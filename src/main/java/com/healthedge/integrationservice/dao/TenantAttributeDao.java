package com.healthedge.integrationservice.dao;

import com.healthedge.integrationservice.entity.TenantAttribute;
import com.healthedge.integrationservice.repository.TenantAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TenantAttributeDao {

    @Autowired
    private TenantAttributeRepository tenantAttributeRepository;

    public Map<String, String> getTenantAttributesForAttributeType(Long tenantId, String attributeType){
        List<TenantAttribute> tenantAttributes=tenantAttributeRepository.getTenantAttributesForAttributeType(tenantId, attributeType);
        Map<String, String> tenantAttributeTransformationMap=new HashMap<>();
        tenantAttributes.forEach(tenantAttribute -> {
            tenantAttributeTransformationMap.put(tenantAttribute.getAttributeKey(), tenantAttribute.getAttributeValue());
        });
        return tenantAttributeTransformationMap;
    }

    @Transactional
    public void saveTenantAttributeDao(TenantAttribute tenantAttribute) {
        tenantAttributeRepository.save(tenantAttribute);
    }

    @Transactional
    public void saveTenantAttributeDao(List<TenantAttribute> tenantAttributes) {
        tenantAttributeRepository.saveAll(tenantAttributes);
    }
}

package com.healthedge.integrationservice.service;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dao.MemberTenantDetailsDao;
import com.healthedge.integrationservice.dao.TenantAttributeDao;
import com.healthedge.integrationservice.dto.TenantAttrubuteRequest;
import com.healthedge.integrationservice.entity.MemberTenantDetails;
import com.healthedge.integrationservice.entity.TenantAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantAttributeDao tenantAttributeDao;

    @Autowired
    private MemberTenantDetailsDao memberTenantDetailsDao;

    public void addTenantAttributes(TenantAttrubuteRequest tenantAttributeRequest) {

        List<TenantAttribute> tenantAttributes = new ArrayList<>();
        tenantAttributeRequest.getMappings().forEach((key, value) -> {
            TenantAttribute tenantAttribute = createTenantAttribute(tenantAttributeRequest.getTenantId(), key, value, IntegrationServiceConstants.ATTRIBUTE_TYPE_MAPPING);
            tenantAttributes.add(tenantAttribute);
        });
        tenantAttributeRequest.getPrimaryAttributes().forEach((key, value) -> {
            tenantAttributes.add(createTenantAttribute(tenantAttributeRequest.getTenantId(), key, value, IntegrationServiceConstants.ATTRIBUTE_TYPE_PRIMARY));
        });
        tenantAttributeDao.saveTenantAttributeDao(tenantAttributes);
    }


    private TenantAttribute createTenantAttribute(Long tenantId, String key, String value, String type) {
        TenantAttribute tenantAttribute = new TenantAttribute();
        tenantAttribute.setTenantId(tenantId);
        tenantAttribute.setAttributeKey(key);
        tenantAttribute.setAttributeValue(value);
        tenantAttribute.setAttributeType(type);
        tenantAttribute.setCreatedBy(IntegrationServiceConstants.ADMIN_INTEGRATION_SERVICE);
        tenantAttribute.setUpdatedBy(IntegrationServiceConstants.ADMIN_INTEGRATION_SERVICE);
        tenantAttribute.setCreatedDate(new Date());
        tenantAttribute.setUpdatedDate(new Date());
        return tenantAttribute;
    }

    public List<MemberTenantDetails> getMemberTenantDetailsForTenant(Long tenantId){
        return memberTenantDetailsDao.getMemberTenantDetails(tenantId);
    }
}

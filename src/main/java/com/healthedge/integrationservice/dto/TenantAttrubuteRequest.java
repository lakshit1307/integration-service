package com.healthedge.integrationservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TenantAttrubuteRequest {

    private Long tenantId;

    private Map<String, String> mappings;

    private Map<String, String> primaryAttributes;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }

    public Map<String, String> getPrimaryAttributes() {
        return primaryAttributes;
    }

    public void setPrimaryAttributes(Map<String, String> primaryAttributes) {
        this.primaryAttributes = primaryAttributes;
    }
}

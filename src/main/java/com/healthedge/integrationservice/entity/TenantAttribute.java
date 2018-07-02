package com.healthedge.integrationservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TENANT_ATTRIBUTE")
public class TenantAttribute {

    @Id
    @Column(name = "TENANT_ATTRIBUTE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantAttributeId;

    @Column(name = "TENANT_ID")
    private Long tenantId;

    @Column(name = "ATTRIBUTE_KEY")
    private String attributeKey;

    @Column(name = "ATTRIBUTE_VALUE")
    private String attributeValue;

    @Column(name = "ATTRIBUTE_TYPE")
    private String attributeType;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public Long getTenantAttributeId() {
        return tenantAttributeId;
    }

    public void setTenantAttributeId(Long tenantAttributeId) {
        this.tenantAttributeId = tenantAttributeId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

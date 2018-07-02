package com.healthedge.integrationservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER_TENANT_DETAILS")
public class MemberTenantDetails {

    @Id
    @Column(name = "MEMBER_TENANT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTenantId;

    @Column(name = "TENANT_ID")
    private Long tenantId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_GENDER")
    private String memberGender;

    @Column(name = "MEMBER_AGE")
    private Integer memberAge;

    @Column(name = "MOST_RECENT_CONDITION")
    private String mostRecentCondition;

    @Column(name = "RISK_SCORE")
    private Double riskScore;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public Long getMemberTenantId() {
        return memberTenantId;
    }

    public void setMemberTenantId(Long memberTenantId) {
        this.memberTenantId = memberTenantId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public Integer getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(Integer memberAge) {
        this.memberAge = memberAge;
    }

    public String getMostRecentCondition() {
        return mostRecentCondition;
    }

    public void setMostRecentCondition(String mostRecentCondition) {
        this.mostRecentCondition = mostRecentCondition;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
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

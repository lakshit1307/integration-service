package com.healthedge.integrationservice.repository;

import com.healthedge.integrationservice.entity.MemberTenantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTenantDetailsRepository extends JpaRepository<MemberTenantDetails, Long> {

    @Query(value = "SELECT * FROM MEMBER_TENANT_DETAILS where TENANT_ID=:tenantId AND MEMBER_ID=:memberId", nativeQuery = true)
    List<MemberTenantDetails> getMemberTenantDetails(@Param("tenantId") Long tenantId, @Param("memberId") Long memberId);

    @Query(value = "UPDATE MEMBER_TENANT_DETAILS SET RISK_SCORE = :riskScore where TENANT_ID=:tenantId AND MEMBER_ID=:memberId", nativeQuery = true)
    List<MemberTenantDetails> updateRiskScoreForMemberTenant(@Param("tenantId") Long tenantId, @Param("memberId") Long memberId, @Param("riskScore") Double riskScore);

    @Query(value = "SELECT * FROM MEMBER_TENANT_DETAILS where TENANT_ID=:tenantId", nativeQuery = true)
    List<MemberTenantDetails> getMemberTenantDetails(@Param("tenantId") Long tenantId);
}

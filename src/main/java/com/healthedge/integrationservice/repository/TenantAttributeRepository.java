package com.healthedge.integrationservice.repository;

import com.healthedge.integrationservice.entity.TenantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantAttributeRepository extends JpaRepository<TenantAttribute, Long> {

    @Query(value = "SELECT * FROM TENANT_ATTRIBUTE where TENANT_ID=:tenantId AND ATTRIBUTE_TYPE=:attributeType", nativeQuery = true)
    List<TenantAttribute> getTenantAttributesForAttributeType(@Param("tenantId") Long tenantId, @Param("attributeType") String attributeType);
}

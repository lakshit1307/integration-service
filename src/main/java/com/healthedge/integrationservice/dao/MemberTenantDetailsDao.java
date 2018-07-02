package com.healthedge.integrationservice.dao;

import com.healthedge.integrationservice.entity.MemberTenantDetails;
import com.healthedge.integrationservice.repository.MemberTenantDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MemberTenantDetailsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberTenantDetailsDao.class);

    @Autowired
    private MemberTenantDetailsRepository memberTenantDetailsRepository;

    @Transactional
    public void saveTenantAttributeDao(MemberTenantDetails memberTenantDetails) {
        if (CollectionUtils.isEmpty(getMemberTenantDetails(memberTenantDetails.getTenantId(), memberTenantDetails.getMemberId()))) {
            memberTenantDetailsRepository.save(memberTenantDetails);
        }
        else{
            LOGGER.info("Member: " + memberTenantDetails.getMemberId() + " already present for tenant: " + memberTenantDetails.getTenantId());

        }
    }

    public List<MemberTenantDetails> getMemberTenantDetails(Long tenantId, Long memberId) {
        return memberTenantDetailsRepository.getMemberTenantDetails(tenantId, memberId);
    }

    public List<MemberTenantDetails> getMemberTenantDetails(Long tenantId){
        return memberTenantDetailsRepository.getMemberTenantDetails(tenantId);
    }

    @Transactional
    public void updateRiskScoreForMemberTenant(Long tenantId, Long memberId, Double riskScore){
        memberTenantDetailsRepository.updateRiskScoreForMemberTenant(tenantId, memberId, riskScore);
    }
}

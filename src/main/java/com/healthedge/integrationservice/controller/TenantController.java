package com.healthedge.integrationservice.controller;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dto.BaseResponse;
import com.healthedge.integrationservice.dto.TenantAttrubuteRequest;
import com.healthedge.integrationservice.entity.MemberTenantDetails;
import com.healthedge.integrationservice.service.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tenant")
public class TenantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

    @Autowired
    private TenantService tenantService;

    @PostMapping("/addtenantattribute")
    public @ResponseBody
    ResponseEntity<BaseResponse> addTenantAttributes(@RequestBody TenantAttrubuteRequest tenantAttrubuteRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            tenantService.addTenantAttributes(tenantAttrubuteRequest);
            baseResponse.setStatus(IntegrationServiceConstants.SUCCESS);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error while adding tenant Attributes for tenantId: " + tenantAttrubuteRequest.getTenantId(), e);
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatus(IntegrationServiceConstants.FAILURE);
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gettenantdetailsfortenant/{tenantId}")
    public @ResponseBody
    ResponseEntity<List<MemberTenantDetails>> getTenantDetailsForTenant(@PathVariable(value = "tenantId") Long tenantId) {
        try {
            List<MemberTenantDetails> memberTenantDetails = tenantService.getMemberTenantDetailsForTenant(tenantId);
            return new ResponseEntity<>(memberTenantDetails, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error while getting member details for tenantId: " + tenantId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

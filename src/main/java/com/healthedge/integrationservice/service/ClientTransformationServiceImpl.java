package com.healthedge.integrationservice.service;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dao.TenantAttributeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class ClientTransformationServiceImpl implements ClientTransformationService {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private TenantAttributeDao tenantAttributeDao;

    @Override
    public List<Map<String, String>> getTransformedDataSet(Long tenantId) {
        Map<String, String> tenantAttributes = tenantAttributeDao.getTenantAttributesForAttributeType(tenantId, IntegrationServiceConstants.ATTRIBUTE_TYPE_MAPPING);
        List<Map<String, String>> currentDataMap = readerService.readFile();
        List<Map<String, String>> tenantTransformedDataMaps = new ArrayList<>();
        currentDataMap.forEach(dataFileDetails -> {
            Map<String, String> transformedDataMap = dataSetMapper(tenantAttributes, dataFileDetails, tenantId);
            tenantTransformedDataMaps.add(transformedDataMap);
        });
        return tenantTransformedDataMaps;
    }

    private Map<String, String> dataSetMapper(Map<String, String> tenantAttributes,
                                              Map<String, String> dataFileDetails, Long tenantId) {
        return dataSetMapper(tenantAttributes, dataFileDetails);
    }

    private Map<String, String> dataSetMapper(Map<String, String> tenantAttributes, Map<String, String> dataFileDetails) {
        Map<String, String> transformedDataMap = new HashMap<>();
        tenantAttributes.forEach((tenantAttributeKey, tenantAttributeValue) -> {
            String dataFileValue = dataFileDetails.get(tenantAttributeKey);

            if (tenantAttributeKey.equalsIgnoreCase(IntegrationServiceConstants.MEMBER_ID)) {
                dataFileValue = jumbleMemberId(dataFileValue);
            }
            transformedDataMap.put(tenantAttributeValue, dataFileValue);

        });
        return transformedDataMap;
    }

    @Override
    public String transformToCsv(List<Map<String, String>> csvObject, Long tenantId) throws IOException {
        Long currentTime = new Long(new Date().getTime());
        String fileName = IntegrationServiceConstants.TEMP_FILE_PREFIX + tenantId.toString() + currentTime.toString() + ".csv";
        BufferedWriter bw = null;
        FileWriter fw = null;
        String fileText = getFileText(csvObject);

        try {
            File tenantFile = new File(IntegrationServiceConstants.TEMP_DIR_PATH + fileName);
            // if file doesnt exists, then create it
            if (!tenantFile.exists()) {
                tenantFile.createNewFile();
            }

            // true = append file
            fw = new FileWriter(tenantFile.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(fileText);


        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                throw ex;
            }
        }
        return fileName;
    }

    private String getFileText(List<Map<String, String>> csvObject) {
        StringBuilder stringBuilder = new StringBuilder();
        csvObject.get(0).forEach((key, value) -> {
            stringBuilder.append(key + ",");
        });
        stringBuilder.subSequence(0, stringBuilder.length() - 1);
        stringBuilder.append("\n");
        csvObject.forEach(dataRow -> {
            dataRow.forEach((key, value) -> {
                stringBuilder.append(value + ",");
            });
            stringBuilder.subSequence(0, stringBuilder.length() - 1);
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }

    private String jumbleMemberId(String memberId) {
        //TODO change implementation for converting member id
        return memberId;
    }
}

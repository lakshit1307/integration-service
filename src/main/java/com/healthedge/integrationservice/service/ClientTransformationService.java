package com.healthedge.integrationservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ClientTransformationService {

    List<Map<String, String>> getTransformedDataSet(Long tenantId);

    String transformToCsv(List<Map<String, String>> csvObject, Long tenantId) throws IOException;
}

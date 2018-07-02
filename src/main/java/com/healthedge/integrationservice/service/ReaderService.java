package com.healthedge.integrationservice.service;

import java.util.List;
import java.util.Map;

public interface ReaderService {

    List<Map<String, String>> readFile();

    List<Map<String, String>> readFileFromPath(String path);

}

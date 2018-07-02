package com.healthedge.integrationservice.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReaderServiceImpl implements ReaderService {


    @Override
    public List<Map<String, String>> readFile() {
        //TODO place input file path
        String path="src/main/resources/input/datalake.csv";
        return readFileFromPath(path);
    }

    @Override
    public List<Map<String, String>> readFileFromPath(String path) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> datas = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                datas.add(line.split(cvsSplitBy));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        boolean isFirstLine=true;
        String[] headers=null;
        List<Map<String,String>> csvData=new ArrayList<>();
        for(String[] fileRecord:datas){
            Map<String, String> dataMap=new HashMap<>();
            if(isFirstLine){
                headers=fileRecord;
            }
            else{
                for(int i=0; i<headers.length; i++){
                    dataMap.put(headers[i], fileRecord[i]);
                }
                csvData.add(dataMap);
            }
            isFirstLine=false;
        }
        return csvData;
    }

}

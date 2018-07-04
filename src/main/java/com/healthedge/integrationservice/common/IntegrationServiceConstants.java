package com.healthedge.integrationservice.common;

import java.io.File;

public class IntegrationServiceConstants {

    public static String FILE_SEPARATOR= File.separator;

    public static String ATTRIBUTE_TYPE_MAPPING="ATTRIBUTE_TYPE_MAPPING";
    public static String ATTRIBUTE_TYPE_PRIMARY="ATTRIBUTE_TYPE_PRIMARY";
    public static String ATTRIBUTE_TYPE_SECONDARY="ATTRIBUTE_TYPE_SECONDARY";

    public static String ATTRIBUTE_NAME_CONSUMPTION_PROTOCOL="CONSUMPTION_PROTOCOL";
    public static String ATTRIBUTE_NAME_FTP_URL="FTP_URL";

    public static String CLIENT_ROUTE="CLIENT_ROUTE";

    public static String CONSUMPTION_PROTOCOL_FTP="FTP";

    public static String SUCCESS="SUCCESS";
    public static String FAILURE="FAILURE";

    public static String ADMIN_INTEGRATION_SERVICE="integration_service_admin";

    public static String MEMBER_ID="MEMBER_ID";
    public static String MEMBER_NAME="MEMBER_NAME";
    public static String MEMBER_GENDER="MEMBER_GENDER";
    public static String MEMBER_AGE="MEMBER_AGE";
    public static String MEMBER_MOST_RECENT_CONDITION="MOST_RECENT_CONDITION";


    public static String TEMP_DIR_PATH="src"+FILE_SEPARATOR+"main"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"temp"+FILE_SEPARATOR;

    public static String OUTPUT_DIR_PATH="src"+FILE_SEPARATOR+"main"+FILE_SEPARATOR+"resources"+FILE_SEPARATOR+"output"+FILE_SEPARATOR;

    public static String TEMP_FILE_PREFIX="TEMP_TENANT_";
}

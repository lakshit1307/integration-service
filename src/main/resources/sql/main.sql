CREATE TABLE TENANT_ATTRIBUTE (
  TENANT_ATTRIBUTE_ID SERIAL NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  ATTRIBUTE_KEY VARCHAR(200) DEFAULT NULL,
  ATTRIBUTE_VALUE VARCHAR(200) DEFAULT NULL,
  ATTRIBUTE_TYPE VARCHAR(200) DEFAULT NULL,
  CREATED_DATE DATE DEFAULT NULL,
  CREATED_BY VARCHAR(200) DEFAULT NULL,
  UPDATED_DATE DATE DEFAULT NULL,
  UPDATED_BY VARCHAR(200) DEFAULT NULL,
  PRIMARY KEY (TENANT_ATTRIBUTE_ID),
  UNIQUE ( TENANT_ID,ATTRIBUTE_KEY)
);

CREATE TABLE MEMBER_TENANT_DETAILS (
  MEMBER_TENANT_ID SERIAL NOT NULL,
  MEMBER_ID INTEGER NOT NULL,
  TENANT_ID INTEGER NOT NULL,
  MEMBER_NAME VARCHAR(200) DEFAULT NULL,
  MEMBER_GENDER VARCHAR(1) DEFAULT NULL,
  MEMBER_AGE INTEGER NULL,
  MOST_RECENT_CONDITION VARCHAR(200) DEFAULT NULL,
  RISK_SCORE INTEGER DEFAULT NULL,
  CREATED_DATE DATE DEFAULT NULL,
  CREATED_BY VARCHAR(200) DEFAULT NULL,
  UPDATED_DATE DATE DEFAULT NULL,
  UPDATED_BY VARCHAR(200) DEFAULT NULL,
  PRIMARY KEY (MEMBER_TENANT_ID),
  UNIQUE ( TENANT_ID,MEMBER_ID)
);
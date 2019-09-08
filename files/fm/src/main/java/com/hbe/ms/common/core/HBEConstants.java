package com.hbe.ms.common.core;

public class HBEConstants {

	private HBEConstants() {
	}


    public static final String TOKEN_NOTVALIDTED = "Token is not valid";
    public static final String SERVICE_NOT_AVAILABLE = "Service Unavailable";
    public static final String HTTP_503 = "503";
    public static final String CONNECTION_REFUSED = "Connection refused";
    public static final String CONNECTION_TIMED_OUT = "Connection timed out";
    public static final String UNKNOWNHOSTEXCEPTION = "UnknownHostException";
    public static final String TRANSPORT_ERROR = "Transport error";
	
	
	
	public static final String ISMOCKURL = "0";
	public static final String MODULE_TYPE_DATASERVICE = "HBEDataServie";
	public static final String HPF_MS_TC_001 = "HPF_MS_TC_001";
	public static final String HPF_MS_BS001 = "HPF_MS_BS001";
	public static final String HPF_MS_BS_002 = "HPF_MS_BS_002";
	public static final String HPF_MS_BS_003 = "HPF_MS_BS_003";
	public static final String HS000000 = "HS000000";

	// VLP Call Names
	public static final String VLP1 = "VLP1";
	public static final String VLP1A = "VLP1A";
	public static final String VLP1B = "VLP1B";
	public static final String VLP2 = "VLP2";
	public static final String VLPRETRIEVESTEP2CASE = "VLPRETRIEVESTEP2CASE";
	public static final String VLPCLOSECASE = "VLPCLOSECASE";
	public static final String TOKENSERVICE = "TOKENSERVICE";
	public static final String DETERMINATIONSERVICE = "DETERMINATIONSERVICE";
	public static final String VLPDATASERVICE = "VLPDATASERVICE";

	public static final String VLPRESTSERTVICE = "VLPRESTSERVICE";

	// VLP Eligibility Statement Code
	public static final String THIRTY_TWO = "32";
	public static final String THIRTY_SEVEN = "37";
	public static final String FORTY = "40";
	public static final String FIVE = "5";

	public static final String PENDING = "P";
	public static final String T1 = "T1";

	// COA Codes for Student
	public static final String F1 = "F1";
	public static final String F2 = "F2";
	public static final String J1 = "J1";
	public static final String J2 = "J2";
	public static final String M1 = "M1";
	public static final String M2 = "M2";
	// VLP Verification Status Yes
	public static final String Y = "Y";

	public static final String TIMEOUT = "11";
	public static final String HPF_MS_TC_002 = "HPF_MS_TC_002";

	public static final String HPF_MS_TC_003 = "HPF_MS_TC_003";

	public static final String HPF_MS_TC_000 = "HPF_MS_TC_000";

	public static final String HPF_MS_TC_004 = "HPF_MS_TC_004";

	public static final String HPF_MS_TC_401 = "HPF_MS_TC_401";// UNAUTHORIZED
	public static final String HPF_MS_TC_403 = "HPF_MS_TC_403";// FORBIDDEN
	public static final String HPF_MS_TC_400 = "HPF_MS_TC_400";// INVALID_FORMAT_EXCEPTION

	public static final String USERNAME = "username";
	public static final String PASS_LABLE = "password";
	public static final String GRANT_TYPE = "grant_type";
	public static final String VLP_CLIENT = "VLP_CLIENT";
	public static final String VLP_SECRET = "VLP_SECRET";

	public static final String ESC_CODE_37 = "37";
	public static final String ESC_CODE_40 = "40";

	public static final String RESTCOMMONSERVICE = "RESTCOMMONSERVICE";

	public static final String HS = "HS";
	public static final String HX = "HX";
	public static final String HE = "HE";

	public static final int HTTP_STATUS_200 = 200;
    public static final String HTTP_200 = "200";
	
	public static final String FAILURE="FAILURE";
	public static final String SUCCESS="SUCCESS";
	public static final String RETRY="RETRY";
	public static final String INACTIVE="INACTIVE";
	public static final String CLOSED="CLOSED";
	public static final String INVALID="INVALID";
	public static final int INT_TWO_THOUSAND = 2000;
	public static final int INT_ZERO = 0;
	public static final int INT_TWO_HUNDRED = 200;

    //Pattern field names and pattern formatters
    public static final String ALIENNUMBER = "ALIENNUMBER";
    public static final String ALIENNUMBER_PATTERN = "^[0-9]{9}$";

    public static final String RECEIPTNUMBER = "RECEIPTNUMBER";
    public static final String RECEIPTNUMBER_PATTERN = "^[a-zA-Z]{3}[0-9]{10}$";

    public static final String CITIZENSHIPNUMBER = "CITIZENSHIPNUMBER";
    public static final String CITIZENSHIPNUMBER_PATTERN = "^[a-zA-Z0-9]{6,12}$";

    public static final String NATURALIZATIONNUMBER = "NATURALIZATIONNUMBER";
    public static final String NATURALIZATIONNUMBER_PATTERN = "^[a-zA-Z0-9]{6,12}$";

    public static final String VISANNUMBER = "VISANNUMBER";
    public static final String VISANNUMBER_PATTERN = "^[a-zA-Z0-9]{8}$";

    public static final String COUNTRYOFISSUANCE = "COUNTRYOFISSUANCE";
    public static final String COUNTRYOFISSUANCE_PATTERN = "^[a-zA-Z]{3}$";

    public static final String PASSPORTNUMBER = "PASSPORTNUMBER";
    public static final String PASSPORTNUMBER_PATTERN = "^[a-zA-Z0-9]{6,12}$";

    public static final String I94NUMBER = "I94NUMBER";
    //Updated for PROD-24435
    public static final String I94NUMBER_PATTERN = "^\\d{9}[0-9A-Za-z][0-9]+$";

    public static final String SEVISID = "SEVISID";
    public static final String SEVISID_PATTERN = "^[0-9]{10}$";

    public static final String DOCDESCREQ = "DOCDESCREQ";

    public static final String DOCDESCREQE_MSG = "DocDescReq is required";

    public static final String DOCEXPIRATIONDATE = "DOCEXPIRATIONDATE";
    public static final String DOCEXPIRATIONDATE_MSG = "DocExpirationDate is required";

    public static final String PASSPORTXPIRATIONDATE_MSG = "PassporExpirationDate is required";
    public static final String PASSPORTXPIRATIONDATE = "PASSPORTXPIRATIONDATE";

    public static final String PASSPORTCOUNTRY = "PASSPORTCOUNTRY";
    public static final String PASSPORTCOUNTRY_PATTERN = "^[a-zA-Z]{3}$";
    // END
    
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String CASEPOC_NO_EXTN = "0";
	
   
}

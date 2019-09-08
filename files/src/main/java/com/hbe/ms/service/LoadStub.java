package com.hbe.ms.service;

//@Component
public class LoadStub {

 /*   private static final Logger LOGGER = LoggerFactory.getLogger(LoadStub.class);
    @Value("${is.soap.mock.url}")
    String isSoapMockUrl;
    private VerifyLawfulPresenceServiceV37Stub stub;
    @Value("${soap.lawfulpresence.url.mock}")
    private String soapUrlMock;
    @Value("${soap.lawfulpresence.url.actual}")
    private String soapUrlActual;

    public LoadStub() {
        //Constructor

    }

    @PostConstruct
    public void init() {
        loadVLPStub();
    }

    public void loadVLPStub() {
        String url = null;
        String errorMsg = null;
        try {
            LOGGER.error("START LOADING Stub");
            if (HBEConstants.ISMOCKURL.equals(isSoapMockUrl)) {
                url = soapUrlMock;
                LOGGER.info("Using Mock URL {}", url);
            } else {
                url = soapUrlActual;
            }
            stub = new VerifyLawfulPresenceServiceV37Stub(url);
            LOGGER.error("END LOADING Stub");
        } catch (Exception e) {
            errorMsg = "Received error while initializing Stub";
            LOGGER.error(errorMsg, e);
        }
    }

    public VerifyLawfulPresenceServiceV37Stub getStub() {
        VerifyLawfulPresenceServiceV37Stub vlpStub = null;
        String errorMsg = null;
        try {
            vlpStub = stub;
        } catch (Exception e) {
            errorMsg = "Received error while fetching initialized Stub";
            LOGGER.error(errorMsg, e);
        }
        return vlpStub;
    }

*/
}

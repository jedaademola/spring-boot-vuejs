package com.hbe.ms.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbe.ms.common.core.HBEConstants;


public class CustomPatternSelector {

    private static final Logger logger = LoggerFactory.getLogger(CustomPatternSelector.class);

    private static final Map<String, String> codes = new HashMap<>();

    static {
        codes.put(HBEConstants.ALIENNUMBER, HBEConstants.ALIENNUMBER_PATTERN);
        codes.put(HBEConstants.RECEIPTNUMBER, HBEConstants.RECEIPTNUMBER_PATTERN);
        codes.put(HBEConstants.CITIZENSHIPNUMBER, HBEConstants.CITIZENSHIPNUMBER_PATTERN);
        codes.put(HBEConstants.NATURALIZATIONNUMBER, HBEConstants.NATURALIZATIONNUMBER_PATTERN);
        codes.put(HBEConstants.VISANNUMBER, HBEConstants.VISANNUMBER_PATTERN);
        codes.put(HBEConstants.COUNTRYOFISSUANCE, HBEConstants.COUNTRYOFISSUANCE_PATTERN);
        codes.put(HBEConstants.PASSPORTNUMBER, HBEConstants.PASSPORTNUMBER_PATTERN);
        codes.put(HBEConstants.I94NUMBER, HBEConstants.I94NUMBER_PATTERN);
        codes.put(HBEConstants.SEVISID, HBEConstants.SEVISID_PATTERN);
        codes.put(HBEConstants.PASSPORTCOUNTRY, HBEConstants.PASSPORTCOUNTRY_PATTERN);
    }

    private CustomPatternSelector() {

    }

    public static String getRegPattern(String fieldName) {
        String pattern = "";
        try {
            pattern = codes.get(fieldName);
        } catch (Exception ex) {
            logger.error("Error in getRegPattern", ex);
        }

        return pattern;
    }

}

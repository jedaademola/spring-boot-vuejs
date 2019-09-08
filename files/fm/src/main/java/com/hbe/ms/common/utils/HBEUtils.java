package com.hbe.ms.common.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.hbe.ms.common.vo.Error;

/**
 * @author abhijeekumar
 *
 */
public class HBEUtils {

	private static final String DD_MM_YYYY = "dd-MMM-yyyy";
	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private static final String TIMESTAMP_FORMATE = "yyyy-MM-dd hh:mm:ss";
    private static final Logger LOGGER = LoggerFactory.getLogger(HBEUtils.class);

	private HBEUtils() {
		// No new object
	}


    public static boolean hasValidValue(String name, String pattern) {
        return name.matches(pattern);
    }

	/**
	 * This method is used to validate the data using regular expression
	 *
	 * @param value
	 * @param fieldName
	 * @param isRequired
	 * @return
	 */
    public static Error validateField(String value, String fieldName, boolean isRequired) {
        Error error = null;
        if (isRequired && value == null) {
            error = new Error(fieldName, fieldName + " is required");
            return error;
        }

        if (value != null) {
            if (!HBEUtils.hasValidValue(value,
                    CustomPatternSelector.getRegPattern(fieldName))) {
                error = new Error(fieldName, "Invalid " + fieldName);
            }
        }


        return error;
    }

    public static boolean isNumeric(String number) {
		boolean isValid = false;

		String expression = "^[-+]?[0-9]*\\.?[0-9]+$";
		CharSequence inputStr = number;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static Date convertDate(String dateString) {

		SimpleDateFormat format1 = new SimpleDateFormat(DD_MM_YYYY);
		Date date = null;
		try {
			date = format1.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static Date convertDateYYYYMMDD(String dateString) {

		SimpleDateFormat format1 = new SimpleDateFormat(YYYY_MM_DD);
		Date date = null;
		try {
			date = format1.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static String convertTimeStampToString(Timestamp inputDate) {
		if (null != inputDate) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_FORMATE);
			return dateFormat.format(inputDate);
		}
		return null;
	}

	public static String getFormattedMessage(final Object... parms) {

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < parms.length; i++) {
			str.append(parms[i]);
		}
		return str.toString();
	}

	public static Timestamp convertStringToTimestampWithTime(String strDate) {
		if (null != strDate) {
			try {
				DateFormat formatter;
				formatter = new SimpleDateFormat(TIMESTAMP_FORMATE);
				Date date =  formatter.parse(strDate);
				return new Timestamp(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public static Timestamp convertStringToTimestamp(String strDate) {
		if (null != strDate) {
			try {
				DateFormat formatter;
				formatter = new SimpleDateFormat(YYYY_MM_DD);
				Date date = formatter.parse(strDate);
				return new Timestamp(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public static Timestamp currentTimestamp() {
		try {
			java.util.Date today = new java.util.Date();

			return new java.sql.Timestamp(today.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String currentDateString() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
			Date date = new Date();
			return formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static XMLGregorianCalendar convertStringtoGreCal(String date) {
		XMLGregorianCalendar xmlGregCal = null;
		try {
			DateFormat format = new SimpleDateFormat(YYYY_MM_DD);
			Date sdf = format.parse(date);

			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(sdf);
			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlGregCal;
	}

	public static String convertXmlGregorianToString(XMLGregorianCalendar xc) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a z");
		if (null != xc) {
			GregorianCalendar gCalendar = xc.toGregorianCalendar();
			Date date = gCalendar.getTime();
			return df.format(date);
		}
		return null;
	}

	public static Timestamp convertXmlGregorianToTimeStamp(XMLGregorianCalendar xc) {
		if (null != xc) {
			GregorianCalendar gCalendar = xc.toGregorianCalendar();
			// Converted to date object
			Date date = gCalendar.getTime();
			return new Timestamp(date.getTime());
		}
		return null;
	}
	public static String convertXmlGregorianToSTRTimeStampFormat(XMLGregorianCalendar xc) {
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		if (null != xc) {
			GregorianCalendar gCalendar = xc.toGregorianCalendar();
			Date date = gCalendar.getTime();
			return df.format(date);
		}
		return null;
	}

	public static XMLGregorianCalendar convertTimeStampToGreCal(Timestamp date) {
		if (null != date) {
			XMLGregorianCalendar gc = null;
			try {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(date);
				gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return gc;
		} else {
			return null;
		}

	}

	public static XMLGregorianCalendar convertSQLDatetoGreCal(java.sql.Date date) {
		XMLGregorianCalendar gc = null;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	public static XMLGregorianCalendar toXmlGregorianCalendarFormat(Timestamp inputTimeStamp) {
		final GregorianCalendar calendar = new GregorianCalendar();
		XMLGregorianCalendar xmlGregorianCalendar = null;

		try {

			calendar.setTime(inputTimeStamp);
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			xmlGregorianCalendar.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED);
		} catch (final DatatypeConfigurationException ex) {
			LOGGER.error("Unable to convert date '%s' to an XMLGregorianCalendar object: " + ex.getMessage(), ex);
		}

		return xmlGregorianCalendar;
	}

	public static String convertToXMLString(Object xml) {

		XmlMapper xmlMapper = new XmlMapper();
		// use the line of code for pretty-print XML on console. We should remove it in
		// production.
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			if (xml != null)
				return xmlMapper.writeValueAsString(xml);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to convert XML Object '%s' to an XML String: " + e.getMessage(), e);
		}
		return null;
	}

	public static String convertToJsonString(Object json) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			if (json != null)
				return mapper.writeValueAsString(json);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to convert JSON Object '%s' to an JSON String: " + e.getMessage(), e);
		}

		return null;
	}

	/**
	 * Add/subtract days from the given Timestamp
	 * 
	 * @param timestamp
	 * @param days
	 * @return
	 */
	public static Timestamp addDays(Timestamp timestamp, int days) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);

		calendar.add(Calendar.DATE, days);

		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * Add/subtract months from the given Timestamp
	 * 
	 * @param timestamp
	 * @param months
	 * @return
	 */
	public static Timestamp addMonths(Timestamp timestamp, int months) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);

		calendar.add(Calendar.MONTH, months);

		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * Returns TRUE if given date is after the currentDate
	 * 
	 * @param date
	 * @param when
	 * @return
	 */
	public static boolean dateAfterIgnoreTime(Timestamp date, Timestamp when) {
		return trimeTime(date).after(trimeTime(when));
	}

	/**
	 * Returns Date after setting HOUR, MINUTE, SECOND and MILLISECOND to 0
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Date trimeTime(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/*
	 * format request and response payload by converting date parameter return
	 * String date
	 */
	public static String formatPayload(String payload) {
		String newxml = "";
		if (null != payload) {
			NodeList name = null;

			try {

				DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource inputSource = new InputSource();
				inputSource.setCharacterStream(new StringReader(payload));

				Document document = documentBuilder.parse(inputSource);
				NodeList nodes = document.getElementsByTagName(document.getDocumentElement().getNodeName());

				/*
				 * Adding all timestamp parameter to list
				 */
				List<String> list = new ArrayList<String>();
				list.add("<dateOfBirth>");
				list.add("<docExpirationDate>");
				list.add("<passportExpirationDate>");
				list.add("<entryDate>");
				list.add("<systemDate>");
				list.add("<createdDate>");
				list.add("<updatedDate>");
				list.add("<effectiveDate>");
				list.add("<passportExpirationDate>");
				list.add("<nonCitEntryDate>");
				list.add("<nonCitEadsExpireDate>");
				list.add("<nonCitBirthDate>");
				list.add("<responseDate>");
				list.add("<serviceReceiptDate>");
				list.add("<lprStatusDate>");
				list.add("<admittedToDate>");

				for (int position = 0; position < nodes.getLength(); position++) {
					Element element = (Element) nodes.item(position);
					for (String str : list) {
						if (StringUtils.contains(payload, str)) {
							String strName = str.substring(1, str.length() - 1);
							name = element.getElementsByTagName(strName);
							Element line = (Element) name.item(0);
							String strDate = DateUtils
									.formatLongTimestampToString(Long.parseLong(getCharacterDataFromElement(line)));
							line.setTextContent(strDate);
						}
					}
				}
				newxml = DocumentToString(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newxml;
	}

	/**
	 * @param e
	 * @return String Read the element value from payload xml
	 */
	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	/**
	 * @param doc
	 * @return
	 * @throws TransformerException
	 * @throws Exception
	 *             generate output after payload conversion
	 */
	private static String DocumentToString(Document doc) throws TransformerException {

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString();
	}

	/*
	 * format json payload by converting date parameter return String date
	 */

	public static String jsonFormator(String payLoad) {
		try {
			List<String> list = new ArrayList<String>();
			list.add("dateOfBirth");
			list.add("docExpirationDate");
			list.add("passportExpirationDate");
			list.add("entryDate");
			list.add("systemDate");
			list.add("createdDate");
			list.add("updatedDate");
			list.add("effectiveDate");
			list.add("passportExpirationDate");
			list.add("nonCitEntryDate");
			list.add("nonCitEadsExpireDate");
			list.add("nonCitBirthDate");
			list.add("responseDate");
			list.add("serviceReceiptDate");
			list.add("lprStatusDate");
			list.add("admittedToDate");
			for (String str : list) {
				JSONObject jObject = new JSONObject(payLoad);
				if (StringUtils.contains(payLoad, str) && !jObject.isNull(str) && 
					(StringUtils.isNotBlank(jObject.get(str).toString()) &&  !(StringUtils.equalsIgnoreCase(jObject.get(str).toString().substring(4,5),"-"))))
					{
					String formattedDate = DateUtils
							.formatLongTimestampToString(Long.parseLong(jObject.get(str).toString()));					
					payLoad = jObject.put(str, formattedDate).toString();
					}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payLoad;

	}

}

package com.hbe.ms.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import gov.hhs.cms.dsh.services.ee.vlp.wsdl.VerifyLawfulPresenceServiceV37Stub;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationIndividualResponseType;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationRequestType;

public class LoadTestAxis {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoadTestAxis.class);
	private static  VerifyLawfulPresenceServiceV37Stub stub;
		
	public static void main(String args[]) throws RemoteException {
		InitialVerificationIndividualResponseType initialVerificationResponse = null;
		InitialVerificationRequestType initialVerificationRequest = null;
		 stub = new VerifyLawfulPresenceServiceV37Stub("https://int-ws.wahpf.org:443/MockVerifyLawfulPresenceServiceV37");
		 
		 stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(3000);//Read timed out
		    
	   // stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(2000);//TODO: Read timed out: from properties file
			
		 stub._getServiceClient().cleanupTransport();//timeout waiting for connection:Release resources allocated by the transport during the last service invocation.
		 stub._getServiceClient().cleanup();//Read timed out: Cleanup associated resources/Clean up configuration created with this client.
		 
		   for(int i=1;i<230 ; i++) {
               LOGGER.error("Iteration of call is :: {}",i);
               initialVerificationResponse = stub.initialVerificationV37(initialVerificationRequest)
                      .getInitialVerificationResponseSet().getInitialVerificationIndividualResponse().get(0);
         }

	}

}

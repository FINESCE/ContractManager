/*
 * (C) Copyright 2014 FINESCE-WP4.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package eu.finesce.emarketplace;

import java.util.GregorianCalendar;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.finesce.emarketplace.domain.Contract;
import eu.finesce.emarketplace.domain.SocialEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class contract  roxy.
 */
public class ContractManagerProxy {

	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(ContractManagerProxy.class);
	
	/** The application path. */
	private static String APPLICATION_PATH = "http://localHost:8080/contractManager/rest";
	
	/** The Constant SOCIAL_DATA_INPUT_PATH. */
	private static final String CONTRACT_DATA_INPUT_PATH = "sendContractData";
	
	/** The event id. */
	private String EVENT_ID = "contractManager";
	
	/** The today. */
	GregorianCalendar today = new GregorianCalendar();
	
	/** The prop. */
	Properties prop;

	/**
	 * Instantiates a new contractManager proxy.
	 */
	public ContractManagerProxy(){
	
	}

	/**
	 * Send social data.
	 *
	 * @return the response
	 */
	public Response sendContractManagerlData() {
		
		Contract contract = new Contract();

		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(APPLICATION_PATH);
		WebTarget resourceWebTarget = webTarget.path(CONTRACT_DATA_INPUT_PATH);
		Response responseEntity = resourceWebTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(contract));
		return responseEntity;
	}
	

}

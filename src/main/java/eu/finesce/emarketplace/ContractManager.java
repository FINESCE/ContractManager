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

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.finesce.emarketplace.context.ContractManagerContextElement;
import eu.finesce.emarketplace.domain.Contract;
import eu.finesce.emarketplace.domain.IncentivePlanList;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.ContextElementList;
import eu.fiware.ngsi.official.UpdateActionType;
import eu.fiware.ngsi.official.UpdateContextRequest;


@Path("/sendContractData")
public class ContractManager {
	
	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(ContractManager.class);
	
	/** The register context path. */
	private static  String REGISTER_CONTEXT_PATH = "";
	
	/** The orion server url. */
	private static  String ORION_SERVER_URL = "";
	
	private static  String CONTRACT_APP_PATH = "";
	
	/** The orion server url. */
	private static  String CONTRACT_REST_URL = "";
	
	/** The prop. */
	Properties prop;
	
	/** The Constant REG_CTX_PATH. */
	private static final String REG_CTX_PATH = "contractManager.registerContexPath";
	
	/** The Constant ORION_SVR_URL. */
	private static final String ORION_SVR_URL = "contractManager.orionServerUrl";
	
	/** The Constant propertyPath. */
	private static final String propertyPath = "contractManager.properties";
	
	private static final String contractRestUrl = "contractManager.contractRestUrl";
	private static final String contractAppPath = "contractManager.contractAppPath";
    private String contractId = null;
	private String ip1_customerApproval;
	private String ip2_customerApproval;
	private String ip3_customerApproval;
	private String ip4_customerApproval;
	private String ip5_customerApproval;
	private String state;
    
    
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/byIncentivePlan")
	public final Response sendContractCtxEvDataToOrion(	String request) {
		
		IncentivePlanList incentivePlan = getContractFromRequest(request);
		Response responseEntity = null;
		
		logger.info("INCENTIVE PLAN STATE =" + incentivePlan.getState());
		
		if ("Compliant".equals(incentivePlan.getState())){
			setConfigProperties();
			logger.info("Sending contract data to Orion  ");
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(ORION_SERVER_URL);
			WebTarget resourceWebTarget = webTarget.path(REGISTER_CONTEXT_PATH);
	
			UpdateContextRequest updContextRequest = new UpdateContextRequest();
			updContextRequest.setUpdateAction(UpdateActionType.APPEND);
			ContextElement element = new ContractManagerContextElement(getContractFromIcentivePlan(incentivePlan));
			ContextElementList elementList = new ContextElementList();
			elementList.getContextElements().add(element);
			updContextRequest.setContextElementList(elementList);
			Entity<UpdateContextRequest> sendXml = Entity.xml(updContextRequest);
			responseEntity = resourceWebTarget.request(
					MediaType.APPLICATION_XML).post(sendXml);
			
		}
		logger.info(responseEntity.readEntity(String.class));
		return responseEntity;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/byCustomer")
	public final Response sendContractDataByCustomerToOrion(String request)  {
		
		setConfigProperties();
		logger.info("STO ENTRANDO NEL METODO");
		logger.info("ECCO COSA RICEVO " + request);
		//set the contract user choise from the request
		setValueFromRequest(request);
		//read the contract from bigData and set the previus contract user choise
		Contract contractToUpdate = retrieveContractFromResponse();
		//
		Response responseEntity = null;
		
		logger.info("Sending contract data to Orion  ");
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ORION_SERVER_URL);
		WebTarget resourceWebTarget = webTarget.path(REGISTER_CONTEXT_PATH);

		UpdateContextRequest updContextRequest = new UpdateContextRequest();
		updContextRequest.setUpdateAction(UpdateActionType.APPEND);
		ContextElement element = new ContractManagerContextElement(contractToUpdate);
		ContextElementList elementList = new ContextElementList();
		elementList.getContextElements().add(element);
		updContextRequest.setContextElementList(elementList);
		Entity<UpdateContextRequest> sendXml = Entity.xml(updContextRequest);
		responseEntity = resourceWebTarget.request(
				MediaType.APPLICATION_XML).post(sendXml);
	    logger.info(responseEntity.readEntity(String.class));	
		return responseEntity;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Contract getContractSample() {
		Contract contract = new Contract();
		contract.setContractID("Contract1");
		contract.setCurrentTime(new Date().getTime());
		contract.setCustomerID("customer1");
		contract.setIncentivePlanID("incentive_1");
		
		return contract;
	}
	
	
	public IncentivePlanList getContractFromRequest(String request) {

		IncentivePlanList incentivePlan = new IncentivePlanList();
		
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = builder.build(new StringReader(request));
			// Get xml root
			Element rootElement = document.getRootElement();
			
			// get root's childs
			Element children = (Element)rootElement.getChildren("contextResponseList").get(0);
			Element children2 = (Element)children.getChildren("contextElementResponse").get(0);
			Element children3 = (Element)children2.getChildren("contextElement").get(0);
			Element children4 = (Element)children3.getChildren("contextAttributeList").get(0);
			List<?> children5 = children4.getChildren();
			Iterator<?> iterator = children5.iterator();
			while (iterator.hasNext()) {				
				Element item = (Element) iterator.next();
				if ("contextAttribute".equals(item.getName())) {
					
					if ("currentTime".equals(item.getChild("name").getText())){
						incentivePlan.setCurrentTime(Long.valueOf(item.getChild("contextValue").getText()));
					}else if ("IPId".equals(item.getChild("name").getText())){
						incentivePlan.setIpId(item.getChild("contextValue").getText());						
					}else if ("customerId".equals(item.getChild("name").getText())){
						incentivePlan.setCustomerId(item.getChild("contextValue").getText());						
					}else if ("meterId".equals(item.getChild("name").getText())){
						incentivePlan.setMeterId(item.getChild("contextValue").getText());						
					
					}else if ("Incentive1PlanTarget".equals(item.getChild("name").getText())){
						incentivePlan.setIp1_target(item.getChild("contextValue").getText());						
					}else if ("Incentive1Type".equals(item.getChild("name").getText())){
						incentivePlan.setIp1_type(item.getChild("contextValue").getText());						
					}else if ("Incentive1Value".equals(item.getChild("name").getText())){
						incentivePlan.setIp1_value(item.getChild("contextValue").getText());						
					}else if ("Incentive1StartDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp1_start(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive1EndDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp1_end(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive1Offer".equals(item.getChild("name").getText())){
						incentivePlan.setIp1_offer(item.getChild("contextValue").getText());
					
					}else if ("Incentive2PlanTarget".equals(item.getChild("name").getText())){
						incentivePlan.setIp2_target(item.getChild("contextValue").getText());						
					}else if ("Incentive2Type".equals(item.getChild("name").getText())){
						incentivePlan.setIp2_type(item.getChild("contextValue").getText());						
					}else if ("Incentive2Value".equals(item.getChild("name").getText())){
						incentivePlan.setIp2_value(item.getChild("contextValue").getText());						
					}else if ("Incentive2StartDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp2_start(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive2EndDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp2_end(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive2Offer".equals(item.getChild("name").getText())){
						incentivePlan.setIp2_offer(item.getChild("contextValue").getText());
					
					}else if ("Incentive3PlanTarget".equals(item.getChild("name").getText())){
						incentivePlan.setIp3_target(item.getChild("contextValue").getText());						
					}else if ("Incentive3Type".equals(item.getChild("name").getText())){
						incentivePlan.setIp3_type(item.getChild("contextValue").getText());						
					}else if ("Incentive3Value".equals(item.getChild("name").getText())){
						incentivePlan.setIp3_value(item.getChild("contextValue").getText());						
					}else if ("Incentive3StartDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp3_start(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive3EndDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp3_end(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive3Offer".equals(item.getChild("name").getText())){
						incentivePlan.setIp3_offer(item.getChild("contextValue").getText());
						
					}else if ("Incentive4PlanTarget".equals(item.getChild("name").getText())){
						incentivePlan.setIp4_target(item.getChild("contextValue").getText());						
					}else if ("Incentive4Type".equals(item.getChild("name").getText())){
						incentivePlan.setIp4_type(item.getChild("contextValue").getText());						
					}else if ("Incentive4Value".equals(item.getChild("name").getText())){
						incentivePlan.setIp4_value(item.getChild("contextValue").getText());						
					}else if ("Incentive4StartDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp4_start(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive4EndDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp4_end(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive4Offer".equals(item.getChild("name").getText())){
						incentivePlan.setIp4_offer(item.getChild("contextValue").getText());

					}else if ("Incentive5PlanTarget".equals(item.getChild("name").getText())){
						incentivePlan.setIp5_target(item.getChild("contextValue").getText());						
					}else if ("Incentive5Type".equals(item.getChild("name").getText())){
						incentivePlan.setIp5_type(item.getChild("contextValue").getText());						
					}else if ("Incentive5Value".equals(item.getChild("name").getText())){
						incentivePlan.setIp5_value(item.getChild("contextValue").getText());						
					}else if ("Incentive5StartDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp5_start(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive5EndDateTime".equals(item.getChild("name").getText())){
						incentivePlan.setIp5_end(Long.valueOf(item.getChild("contextValue").getText()));						
					}else if ("Incentive5Offer".equals(item.getChild("name").getText())){
						incentivePlan.setIp5_offer(item.getChild("contextValue").getText());
						
					}else if ("State".equals(item.getChild("name").getText())){
						incentivePlan.setState(item.getChild("contextValue").getText());	
				   }
				}		
		 }
			
		} catch (JDOMException e) {
			logger.error("Error reading xml file :", e);
		} catch (IOException e) {
			logger.error("Error openinig xml (Rest) file :", e);
		}
		
		logger.info("INCENTIVE PLAN =  " + incentivePlan.toString());
       //implementare la logica di lettura della request
		return incentivePlan;
	}
	
	
	public Contract getContractFromIcentivePlan(IncentivePlanList incentivePlan) {
		Contract contract = new Contract();
		
		contract.setContractID(incentivePlan.getIpId()+"_"+incentivePlan.getMeterId()+"_"+incentivePlan.getCurrentTime());
		contract.setCurrentTime(incentivePlan.getCurrentTime());
		contract.setCustomerID(incentivePlan.getCustomerId());
		contract.setIncentivePlanID(incentivePlan.getIpId());
		contract.setMeterID(incentivePlan.getMeterId());
		
		contract.setIp1_planTarget(incentivePlan.getIp1_target());
		contract.setIp1_type(incentivePlan.getIp1_type());
		contract.setIp1_value(Double.valueOf(incentivePlan.getIp1_value()));
		contract.setIp1_start(incentivePlan.getIp1_start());
		contract.setIp1_end(incentivePlan.getIp1_end());
		contract.setIp1_offer(incentivePlan.getIp1_offer());
		
		contract.setIp2_planTarget(incentivePlan.getIp2_target());
		contract.setIp2_type(incentivePlan.getIp2_type());
		contract.setIp2_value(Double.valueOf(incentivePlan.getIp2_value()));
		contract.setIp2_start(incentivePlan.getIp2_start());
		contract.setIp2_end(incentivePlan.getIp2_end());
		contract.setIp2_offer(incentivePlan.getIp2_offer());
		
		contract.setIp3_planTarget(incentivePlan.getIp3_target());
		contract.setIp3_type(incentivePlan.getIp3_type());
		contract.setIp3_value(Double.valueOf(incentivePlan.getIp3_value()));
		contract.setIp3_start(incentivePlan.getIp3_start());
		contract.setIp3_end(incentivePlan.getIp3_end());
		contract.setIp3_offer(incentivePlan.getIp3_offer());

		contract.setIp4_planTarget(incentivePlan.getIp4_target());
		contract.setIp4_type(incentivePlan.getIp4_type());
		contract.setIp4_value(Double.valueOf(incentivePlan.getIp4_value()));
		contract.setIp4_start(incentivePlan.getIp4_start());
		contract.setIp4_end(incentivePlan.getIp4_end());
		contract.setIp4_offer(incentivePlan.getIp4_offer());
		
		contract.setIp5_planTarget(incentivePlan.getIp5_target());
		contract.setIp5_type(incentivePlan.getIp5_type());
		contract.setIp5_value(Double.valueOf(incentivePlan.getIp5_value()));
		contract.setIp5_start(incentivePlan.getIp5_start());
		contract.setIp5_end(incentivePlan.getIp5_end());
		contract.setIp5_offer(incentivePlan.getIp5_offer());
		// 
		contract.setIp1_customerApproval(0);
		contract.setIp2_customerApproval(0);
		contract.setIp3_customerApproval(0);
		contract.setIp4_customerApproval(0);
		contract.setIp5_customerApproval(0);

		contract.setDateOfCreation(new Date().getTime() / 1000);
		contract.setDateOfApproval(0);
		contract.setState("UnderApproval");
		
		return contract;
	}

	
	
	/**
	 * Sets the config properties.
	 */
	protected void setConfigProperties() {
		prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream(propertyPath));
			REGISTER_CONTEXT_PATH = prop.getProperty(REG_CTX_PATH);
			ORION_SERVER_URL = prop.getProperty(ORION_SVR_URL);
			CONTRACT_APP_PATH = prop.getProperty(contractAppPath);
			CONTRACT_REST_URL = prop.getProperty(contractRestUrl);
			
		} catch (IOException e) {
			logger.error("Error during getProperty ", e);
		}
	}
	
	
	public final Response getContractData(String contractId) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(CONTRACT_REST_URL);
		WebTarget resourceWebTarget = webTarget.path(CONTRACT_APP_PATH);
		WebTarget resourceWebTargetParam = resourceWebTarget.queryParam(
				"contractId", contractId);
		Response responseEntity = resourceWebTargetParam.request(
				MediaType.APPLICATION_XML).get();
		return responseEntity;
	}
	
	
	public Contract retrieveContractFromResponse() {
		Response rs = getContractData(contractId);
		String fileResponse = new String(rs.readEntity(String.class));		
		SAXBuilder builder = new SAXBuilder();
		Contract contract = new Contract();
		long time = new Date().getTime() / 1000;
		try {
			Document document = builder.build(new StringReader(fileResponse));
			// Get xml root
			Element rootElement = document.getRootElement();

			contract.setContractID(contractId);
			contract.setCurrentTime(time);
            contract.setCustomerID(rootElement.getChildText("customerID"));
			contract.setDateOfApproval(time);
			contract.setDateOfCreation(Long.valueOf(rootElement.getChildText("dateOfCreation")));
			contract.setIncentivePlanID(rootElement.getChildText("incentivePlanID"));
			
			contract.setIp1_customerApproval(Integer.valueOf(ip1_customerApproval)); //from user choise
			contract.setIp1_end(Long.valueOf(rootElement.getChildText("ip1_end")));
			contract.setIp1_offer(rootElement.getChildText("ip1_offer"));
			contract.setIp1_planTarget(rootElement.getChildText("ip1_planTarget"));
			contract.setIp1_start(Long.valueOf(rootElement.getChildText("ip1_start")));
			contract.setIp1_type(rootElement.getChildText("ip1_type"));
			contract.setIp1_value(Double.valueOf(rootElement.getChildText("ip1_value")));
			
			contract.setIp2_customerApproval(Integer.valueOf(ip2_customerApproval)); //from user choise
			contract.setIp2_end(Long.valueOf(rootElement.getChildText("ip2_end")));
			contract.setIp2_offer(rootElement.getChildText("ip2_offer"));
			contract.setIp2_planTarget(rootElement.getChildText("ip2_planTarget"));
			contract.setIp2_start(Long.valueOf(rootElement.getChildText("ip2_start")));
			contract.setIp2_type(rootElement.getChildText("ip2_type"));
			contract.setIp2_value(Double.valueOf(rootElement.getChildText("ip2_value")));
			
			contract.setIp3_customerApproval(Integer.valueOf(ip3_customerApproval)); //from user choise
			contract.setIp3_end(Long.valueOf(rootElement.getChildText("ip3_end")));
			contract.setIp3_offer(rootElement.getChildText("ip3_offer"));
			contract.setIp3_planTarget(rootElement.getChildText("ip3_planTarget"));
			contract.setIp3_start(Long.valueOf(rootElement.getChildText("ip3_start")));
			contract.setIp3_type(rootElement.getChildText("ip3_type"));
			contract.setIp3_value(Double.valueOf(rootElement.getChildText("ip3_value")));
			
			contract.setIp4_customerApproval(Integer.valueOf(ip4_customerApproval)); //from user choise
			contract.setIp4_end(Long.valueOf(rootElement.getChildText("ip4_end")));
			contract.setIp4_offer(rootElement.getChildText("ip4_offer"));
			contract.setIp4_planTarget(rootElement.getChildText("ip4_planTarget"));
			contract.setIp4_start(Long.valueOf(rootElement.getChildText("ip4_start")));
			contract.setIp4_type(rootElement.getChildText("ip4_type"));
			contract.setIp4_value(Double.valueOf(rootElement.getChildText("ip4_value")));
			
			contract.setIp5_customerApproval(Integer.valueOf(ip5_customerApproval)); //from user choise
			contract.setIp5_end(Long.valueOf(rootElement.getChildText("ip5_end")));
			contract.setIp5_offer(rootElement.getChildText("ip5_offer"));
			contract.setIp5_planTarget(rootElement.getChildText("ip5_planTarget"));
			contract.setIp5_start(Long.valueOf(rootElement.getChildText("ip5_start")));
			contract.setIp5_type(rootElement.getChildText("ip5_type"));
			contract.setIp5_value(Double.valueOf(rootElement.getChildText("ip5_value")));
			
			contract.setMeterID(rootElement.getChildText("meterID"));
			contract.setState(state); //from user choise
			
		
	
		} catch (JDOMException e) {
			logger.error("Error reading xml file :", e);
		} catch (IOException e) {
			logger.error("Error openinig xml file :", e);
		}
		
		logger.info("CONTRACT OBJECT TO SEND TO ORION " + contract.toString());
		return contract;
	}
	
	public void setValueFromRequest(String request){
		JSONParser parser = new JSONParser();

		try {   
	    JSONObject jsonObject =  (JSONObject)new JSONParser().parse(request);  
	    contractId = (String) jsonObject.get("contractID");
	    ip1_customerApproval = (String) jsonObject.get("customer_approval_1");   
	    ip2_customerApproval = (String) jsonObject.get("customer_approval_2");
	    ip3_customerApproval = (String) jsonObject.get("customer_approval_3");
	    ip4_customerApproval = (String) jsonObject.get("customer_approval_4");
	    ip5_customerApproval = (String) jsonObject.get("customer_approval_5");
	    state = (String) jsonObject.get("state");
		
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
}

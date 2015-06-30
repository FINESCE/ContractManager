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
package eu.finesce.emarketplace.context;

import eu.finesce.emarketplace.domain.Contract;
import eu.fiware.ngsi.official.ContextAttribute;
import eu.fiware.ngsi.official.ContextAttributeList;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.EntityId;

// TODO: Auto-generated Javadoc
/**
 * The Class SocialContextElement.
 */
public class ContractManagerContextElement extends ContextElement {

    private static final String CURRENTTIME = "currentTime";
    private static final String DATEOFCREATION = "dateOfCreation";
    private static final String DATEOFAPPROVAL = "dateOfApproval";
    private static final String I1CUSTOMERAPPROVAL = "i1CustomerApproval";
    private static final String I2CUSTOMERAPPROVAL = "i2CustomerApproval";
    private static final String I3CUSTOMERAPPROVAL = "i3CustomerApproval";
    private static final String I4CUSTOMERAPPROVAL = "i4CustomerApproval";
    private static final String I5CUSTOMERAPPROVAL = "i5CustomerApproval";
    private static final String CONTRACTID = "contractId";
    private static final String IPID    = "IPId";
    private static final String CUSTOMERID = "customerId";
    private static final String METERID = "meterId";
    private static final String IRPID      = "IRPId";
    private static final String INCENTIVE1PLANTARGET  = "Incentive1PlanTarget";
    private static final String INCENTIVE1TYPE  = "Incentive1Type";
    private static final String INCENTIVE1VALUE  = "Incentive1Value";
    private static final String INCENTIVE1STARTDATETIME = "Incentive1StartDateTime";
    private static final String INCENTIVE1ENDDATETIME = "Incentive1EndDateTime";
    private static final String INCENTIVE1OFFER = "Incentive1Offer";
    private static final String INCENTIVE2PLANTARGET  = "Incentive2PlanTarget";
    private static final String INCENTIVE2TYPE     = "Incentive2Type";
    private static final String INCENTIVE2VALUE    = "Incentive2Value";
    private static final String INCENTIVE2STARTDATETIME   = "Incentive2StartDateTime";
    private static final String INCENTIVE2ENDDATETIME   = "Incentive2EndDateTime";
    private static final String INCENTIVE2OFFER = "Incentive2Offer";
    private static final String INCENTIVE3PLANTARGET  = "Incentive3PlanTarget";
    private static final String INCENTIVE3TYPE     = "Incentive3Type";
    private static final String INCENTIVE3VALUE   = "Incentive3Value";
    private static final String INCENTIVE3STARTDATETIME  = "Incentive3StartDateTime";
    private static final String INCENTIVE3ENDDATETIME   = "Incentive3EndDateTime";
    private static final String INCENTIVE3OFFER = "Incentive3Offer";
    private static final String INCENTIVE4PLANTARGET  = "Incentive4PlanTarget";
    private static final String INCENTIVE4TYPE    = "Incentive4Type";
    private static final String INCENTIVE4VALUE    = "Incentive4Value";
    private static final String INCENTIVE4STARTDATETIME  = "Incentive4StartDateTime";
    private static final String INCENTIVE4ENDDATETIME  = "Incentive4EndDateTime";
    private static final String INCENTIVE4OFFER = "Incentive4Offer";
    private static final String INCENTIVE5PLANTARGET = "Incentive5PlanTarget";
    private static final String INCENTIVE5TYPE  = "Incentive5Type";
    private static final String INCENTIVE5VALUE   = "Incentive5Value";
    private static final String INCENTIVE5STARTDATETIME  = "Incentive5StartDateTime";
    private static final String INCENTIVE5ENDDATETIME    = "Incentive5EndDateTime";
    private static final String INCENTIVE5OFFER = "Incentive5Offer";
    private static final String STATE    = "State";

	

	/**
	 * Instantiates a new social context element.
	 *
	 * @param socialEvent the social event
	 */
	public ContractManagerContextElement(Contract contract) {
		this.contextAttributeList = new ContextAttributeList();

		EntityId id = new EntityId();
		
		id.setId("contract2orion");
		id.setType("Contract");
		id.setIsPattern(false);
		this.setEntityId(id);
		ContextAttribute attribute = new ContextAttribute();

		// attributes
		attribute = new ContextAttribute();
		attribute.setName(CURRENTTIME);
		attribute.setType("sec");
		attribute.setContextValue(contract.getCurrentTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(CONTRACTID);
		attribute.setType("string");
		attribute.setContextValue(contract.getContractID());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(IPID);
		attribute.setType("key");
		attribute.setContextValue(contract.getIncentivePlanID());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(CUSTOMERID);
		attribute.setType("string");
		attribute.setContextValue(contract.getCustomerID());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(METERID);
		attribute.setType("string");
		attribute.setContextValue(contract.getMeterID());
		this.getContextAttributeList().getContextAttributes().add(attribute);
						
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE1PLANTARGET);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp1_planTarget());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE1TYPE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp1_type());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE1VALUE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp1_value());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE1STARTDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp1_start());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE1ENDDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp1_end());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE1OFFER);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp1_offer());
		
		this.getContextAttributeList().getContextAttributes().add(attribute);
		attribute = new ContextAttribute();
		attribute.setName(I1CUSTOMERAPPROVAL);
		attribute.setType("int");
		attribute.setContextValue(contract.getIp1_customerApproval());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
//
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE2PLANTARGET);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp2_planTarget());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE2TYPE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp2_type());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE2VALUE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp2_value());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE2STARTDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp2_start());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE2ENDDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp2_end());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE2OFFER);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp2_offer());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(I2CUSTOMERAPPROVAL);
		attribute.setType("int");
		attribute.setContextValue(contract.getIp2_customerApproval());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		

//
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE3PLANTARGET);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp3_planTarget());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE3TYPE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp3_type());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE3VALUE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp3_value());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE3STARTDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp3_start());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE3ENDDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp3_end());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE3OFFER);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp3_offer());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(I3CUSTOMERAPPROVAL);
		attribute.setType("int");
		attribute.setContextValue(contract.getIp3_customerApproval());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
//		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE4PLANTARGET);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp4_planTarget());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE4TYPE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp4_type());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE4VALUE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp4_value());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE4STARTDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp4_start());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE4ENDDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp4_end());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE4OFFER);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp4_offer());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(I4CUSTOMERAPPROVAL);
		attribute.setType("int");
		attribute.setContextValue(contract.getIp4_customerApproval());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
//
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE5PLANTARGET);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp5_planTarget());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE5TYPE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp5_type());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE5VALUE);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp5_value());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE5STARTDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp5_start());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE5ENDDATETIME);
		attribute.setType("datetime");
		attribute.setContextValue(contract.getIp5_end());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(INCENTIVE5OFFER);
		attribute.setType("string");
		attribute.setContextValue(contract.getIp5_offer());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(I5CUSTOMERAPPROVAL);
		attribute.setType("int");
		attribute.setContextValue(contract.getIp5_customerApproval());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(DATEOFCREATION);
		attribute.setType("string");
		attribute.setContextValue(contract.getDateOfCreation());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(DATEOFAPPROVAL);
		attribute.setType("string");
		attribute.setContextValue(contract.getDateOfApproval());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(STATE);
		attribute.setType("string");
		attribute.setContextValue(contract.getState());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		
	}

}

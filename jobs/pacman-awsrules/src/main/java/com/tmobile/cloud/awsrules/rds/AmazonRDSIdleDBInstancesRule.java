/*******************************************************************************
 * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.tmobile.cloud.awsrules.rds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.amazonaws.util.StringUtils;
import com.tmobile.cloud.awsrules.utils.PacmanUtils;
import com.tmobile.cloud.constants.PacmanRuleConstants;
import com.tmobile.pacman.commons.PacmanSdkConstants;
import com.tmobile.pacman.commons.exception.InvalidInputException;
import com.tmobile.pacman.commons.exception.RuleExecutionFailedExeption;
import com.tmobile.pacman.commons.rule.Annotation;
import com.tmobile.pacman.commons.rule.BaseRule;
import com.tmobile.pacman.commons.rule.PacmanRule;
import com.tmobile.pacman.commons.rule.RuleResult;

@PacmanRule(key = "check-for-amazon-RDS-idle-DB-instances", desc = "Checks for amazon RDS idle DB instances", severity = PacmanSdkConstants.SEV_HIGH, category = PacmanSdkConstants.GOVERNANCE)
public class AmazonRDSIdleDBInstancesRule extends BaseRule {

	private static final Logger logger = LoggerFactory.getLogger(AmazonRDSIdleDBInstancesRule.class);
	/**
	 * The method will get triggered from Rule Engine with following parameters
	 * @param ruleParam 
	 * 
	 * ************* Following are the Rule Parameters********* <br><br>
	 * 
	 * checkId   : Mention the checkId value <br><br>
	 * 
	 * ruleKey : check-for-amazon-RDS-idle-DB-instances <br><br>
	 * 
	 * esServiceURL : Enter the Es url <br><br>
	 * 
	 * threadsafe : if true , rule will be executed on multiple threads <br><br>
	 *  
	 * severity : Enter the value of severity <br><br>
	 * 
	 * ruleCategory : Enter the value of category <br><br>
	 * 
	 * @param resourceAttributes this is a resource in context which needs to be scanned this is provided by execution engine
	 *
	 */
	@Override
	public RuleResult execute(Map<String, String> ruleParam, Map<String, String> resourceAttributes) {
		
		logger.debug("========AmazonRDSIdleDBInstancesRule started=========");
		Annotation annotation = null;
		String resourceId = null;
		String region = null;
		String accountId = null;
		String checkId = StringUtils.trim(ruleParam.get(PacmanRuleConstants.CHECK_ID));
		
		String severity = ruleParam.get(PacmanRuleConstants.SEVERITY);
		String category = ruleParam.get(PacmanRuleConstants.CATEGORY);
		
		String serviceEsURL = null;
		
		String formattedUrl = PacmanUtils.formatUrl(ruleParam,PacmanRuleConstants.ES_CHECK_SERVICE_SEARCH_URL_PARAM);
        
        if(!StringUtils.isNullOrEmpty(formattedUrl)){
            serviceEsURL =  formattedUrl;
        }
		
		MDC.put("executionId", ruleParam.get("executionId")); // this is the logback Mapped Diagnostic Contex
		MDC.put("ruleId", ruleParam.get(PacmanSdkConstants.RULE_ID)); // this is the logback Mapped Diagnostic Contex
		
		List<LinkedHashMap<String,Object>>issueList = new ArrayList<>();
		LinkedHashMap<String,Object>issue = new LinkedHashMap<>();
		
		if (!PacmanUtils.doesAllHaveValue(checkId,severity,category,serviceEsURL)) {
			logger.info(PacmanRuleConstants.MISSING_CONFIGURATION);
			throw new InvalidInputException(PacmanRuleConstants.MISSING_CONFIGURATION);
		}
		
		if (resourceAttributes != null) {
			region = resourceAttributes.get(PacmanRuleConstants.REGION);
			accountId = resourceAttributes.get(PacmanSdkConstants.ACCOUNT_ID);
			resourceId = StringUtils.trim(resourceAttributes.get(PacmanRuleConstants.DB_INSTANCE_IDENTIFIER));
			Map<String, String> rdsIdleDbMap = new HashMap<>();
			try {
				rdsIdleDbMap = PacmanUtils.getRDSDetailsForCheckId(checkId,resourceId,serviceEsURL,region,accountId);
			} catch (Exception e) {
				logger.error("unable to determine",e);
				throw new RuleExecutionFailedExeption("unable to determine"+e);
			}
			if (!rdsIdleDbMap.isEmpty()) {
				annotation = Annotation.buildAnnotation(ruleParam, Annotation.Type.ISSUE);
				annotation.put(PacmanSdkConstants.DESCRIPTION,"Amazon RDS idle DB instance found !!");
				annotation.put(PacmanRuleConstants.SEVERITY, severity);
				annotation.put(PacmanRuleConstants.CATEGORY, category);
				annotation.put(PacmanRuleConstants.EST_MONTHLY_SAVINGS, rdsIdleDbMap.get(PacmanRuleConstants.EST_MONTHLY_SAVINGS));
				annotation.put(PacmanRuleConstants.INSTANCETYPE, rdsIdleDbMap.get(PacmanRuleConstants.INSTANCETYPE));
				annotation.put("multi-AZ", rdsIdleDbMap.get("multi-AZ"));
				annotation.put("storageProvisioned", rdsIdleDbMap.get("storageProvisioned"));
				annotation.put("daysSinceLastConnection", rdsIdleDbMap.get("daysSinceLastConnection"));
				
				issue.put(PacmanRuleConstants.VIOLATION_REASON, "Amazon RDS idle DB instance found");
				issue.put(PacmanRuleConstants.CHECKID, checkId);
				issue.put(PacmanRuleConstants.SOURCE_VERIFIED, "trusted advisor");
				issueList.add(issue);
				
				annotation.put("issueDetails",issueList.toString());
				
				logger.debug("========AmazonRDSIdleDBInstancesRule ended with an annotation {} :=========",annotation);
				return new RuleResult(PacmanSdkConstants.STATUS_FAILURE,PacmanRuleConstants.FAILURE_MESSAGE, annotation);
			}
			}
		logger.debug("========AmazonRDSIdleDBInstancesRule ended=========");
		return new RuleResult(PacmanSdkConstants.STATUS_SUCCESS,PacmanRuleConstants.SUCCESS_MESSAGE);
	}

	@Override
	public String getHelpText() {
		return "This rule checks for amazon RDS idle DB instances";
	}
}

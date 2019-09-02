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
/**
  Copyright (C) 2017 T Mobile Inc - All Rights Reserve
  Purpose:
  Author :santoshi
  Modified Date: Dec 6, 2017

 **/
package com.tmobile.pacman.api.compliance.domain;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The Class NonCompliancePolicyResponse.
 */
public class NonCompliancePolicyResponse {

    /** The response. */
    List<LinkedHashMap<String, Object>> response;

    /**
     * Instantiates a new non compliance policy response.
     *
     * @param response the response
     */
    public NonCompliancePolicyResponse(
            List<LinkedHashMap<String, Object>> response) {
        super();
        this.response = response;
    }

    /**
     * Gets the response.
     *
     * @return the response
     */
    public List<LinkedHashMap<String, Object>> getResponse() {
        return response;
    }

    /**
     * Sets the response.
     *
     * @param response the response
     */
    public void setResponse(List<LinkedHashMap<String, Object>> response) {
        this.response = response;
    }
}

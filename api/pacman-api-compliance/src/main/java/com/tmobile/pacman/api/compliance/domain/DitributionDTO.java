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
  Modified Date: Oct 23, 2017

 **/
package com.tmobile.pacman.api.compliance.domain;

import java.util.Map;
/**
 * The Class DitributionDTO.
 */
public class DitributionDTO {

    /** The response. */
    Map<String, Object> response;

    /**
     * Instantiates a new ditribution DTO.
     */
    public DitributionDTO() {
        super();
    }

    /**
     * Instantiates a new ditribution DTO.
     *
     * @param distribution the distribution
     */
    public DitributionDTO(Map<String, Object> distribution) {
        super();
        this.response = distribution;
    }

    /**
     * Gets the distribution.
     *
     * @return the distribution
     */
    public Map<String, Object> getDistribution() {
        return response;
    }

    /**
     * Sets the distribution.
     *
     * @param distribution the distribution
     */
    public void setDistribution(Map<String, Object> distribution) {
        this.response = distribution;
    }
}

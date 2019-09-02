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
package com.tmobile.pacman.api.compliance.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
/**
 * The Class TrendRequest.
 */
public class TrendRequest {
    
    /** The from. */
    private Date from;
    
    /** The to. */
    private Date to;
    
    /** The filter. */
    private Map<String, String> filter;
    
    /** The ag. */
    private String ag;
    
    /**
     * Gets the from.
     *
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Sets the from.
     *
     * @param from the new from
     */
    public void setFrom(Date from) {
        this.from = from;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public Date getTo() {
        return to;
    }

    /**
     * Sets the to.
     *
     * @param to the new to
     */
    public void setTo(Date to) {
        this.to = to;
    }

    /**
     * Gets the filter.
     *
     * @return the filter
     */
    public Map<String, String> getFilter() {
        return filter;
    }

    /**
     * Sets the filter.
     *
     * @param filter the filter
     */
    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }

    /**
     * Gets the ag.
     *
     * @return the ag
     */
    public String getAg() {
        return ag;
    }

    /**
     * Sets the ag.
     *
     * @param ag the new ag
     */
    public void setAg(String ag) {
        this.ag = ag;
    }
    
    /**
     * Gets the vuln cache key.
     *
     * @return the vuln cache key
     */
    public String getVulnCacheKey(){
        String severity = "3,4,5";
        if(filter!=null&&
            (filter.get("severity")!=null)){
                severity =filter.get("severity");
            }
        
        return ag+ new SimpleDateFormat("yyyy-MM-dd").format(from)+severity;
    }
}

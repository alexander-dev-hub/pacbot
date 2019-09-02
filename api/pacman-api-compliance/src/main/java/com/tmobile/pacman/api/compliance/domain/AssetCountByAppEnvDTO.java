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
  Modified Date: Nov 5, 2017

 **/
package com.tmobile.pacman.api.compliance.domain;
/**
 * The Class AssetCountByAppEnvDTO.
 */
public class AssetCountByAppEnvDTO {
    private String count;

    private String type;

    private AssetCountEnvCount[] environments;

    private String application;

    public AssetCountEnvCount[] getEnvironments() {
        return environments;
    }

    public void setEnvironments(AssetCountEnvCount[] environments) {
        this.environments = environments;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClassPojo [count = " + count + ", type = " + type
                + ", application = " + application + ",environments = "
                + environments + "]";
    }
}

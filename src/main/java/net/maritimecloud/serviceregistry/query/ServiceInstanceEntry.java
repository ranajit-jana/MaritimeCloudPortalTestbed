/* Copyright 2014 Danish Maritime Authority.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.maritimecloud.serviceregistry.query;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import net.maritimecloud.portal.domain.infrastructure.jackson.CoverageSerializer;
import net.maritimecloud.serviceregistry.command.serviceinstance.Coverage;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Christoffer Børrild
 */
@Entity
public class ServiceInstanceEntry implements Serializable {

    @Id
    @javax.persistence.Id
    private String serviceInstanceId;
    private String providerId;
    private String specificationId;
    private String name;
    @Column(length = 1000)
    private String summary;
    @JsonSerialize(using = CoverageSerializer.class)
    @Embedded
    private Coverage coverage; // FIXME: create complex version of coverage instead of json-serialized one

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(String specificationId) {
        this.specificationId = specificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonSerialize(using = CoverageSerializer.class)
    public Coverage getCoverage() {
        return coverage;
    }

    @JsonSerialize(using = CoverageSerializer.class)
    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    @Override
    public String toString() {
        return "ServiceInstanceEntry{"
                + "serviceInstanceId=" + serviceInstanceId
                + ", providerId=" + providerId
                + ", specificationId=" + specificationId
                + ", name=" + name
                + ", summary=" + summary
                + ", coverage=" + coverage
                + '}';
    }

}
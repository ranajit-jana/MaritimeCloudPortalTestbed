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

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Christoffer Børrild
 */
public interface OrganizationMemberQueryRepository extends PagingAndSortingRepository<OrganizationMemberEntry, String> {

    public OrganizationMemberEntry findByOrganizationIdAndUsername(String anOrganizationId, String aUsername);

    public Iterable<OrganizationMemberEntry> findByUsername(String aUsername);

    public Iterable<OrganizationMemberEntry> findByOrganizationId(String organizationId);
}
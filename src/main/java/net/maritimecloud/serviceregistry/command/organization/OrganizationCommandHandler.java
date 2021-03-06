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
package net.maritimecloud.serviceregistry.command.organization;

import net.maritimecloud.serviceregistry.command.api.PrepareServiceSpecification;
import net.maritimecloud.serviceregistry.command.api.ProvideServiceInstance;
import javax.annotation.Resource;
import net.maritimecloud.serviceregistry.command.api.AuthorizeMembershipToOrganizationCreator;
import net.maritimecloud.serviceregistry.command.api.InviteUserToOrganization;
import net.maritimecloud.serviceregistry.command.api.ApplyForMembershipToOrganization;
import net.maritimecloud.serviceregistry.command.organization.membership.Membership;
import net.maritimecloud.serviceregistry.command.serviceinstance.ServiceInstance;
import net.maritimecloud.serviceregistry.command.servicespecification.ServiceSpecification;
import net.maritimecloud.serviceregistry.query.OrganizationMembershipQueryRepository;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Responsibilities (in the ServiceRegistry context):
 * <p>
 * <p>
 * Creates ServiceSpecifications and provides ServiceInstances (Provides factories for ServiceSpecifications and ServiceInstances, this
 * protects the invariant that )
 * <p>
 * Makes sure that ServiceSpecifications and ServiceInstances has a unique identity within the owning organization
 * <p>
 * Maintains the lists of ServiceSpecifications and ServiceInstances held by an organization
 * <p>
 * <p>
 * @author Christoffer Børrild
 */
@Component
public class OrganizationCommandHandler {

    @Resource
    private Repository<Organization> repository;
    @Resource
    private Repository<ServiceSpecification> serviceSpecificationRepository;
    @Resource
    private Repository<ServiceInstance> serviceInstanceRepository;
    @Resource
    private Repository<Membership> membershipRepository;

    // HACK! TODO: probably should NOT be using a view-model from a command context!
    @Resource
    OrganizationMembershipQueryRepository membershipQueryRepository;

    public void setOrganizationRepository(Repository<Organization> organizationRepository) {
        this.repository = organizationRepository;
    }

    public void setServiceSpecificationRepository(Repository<ServiceSpecification> serviceSpecificationRepository) {
        this.serviceSpecificationRepository = serviceSpecificationRepository;
    }

    public void setServiceInstanceRepository(Repository<ServiceInstance> serviceInstanceRepository) {
        this.serviceInstanceRepository = serviceInstanceRepository;
    }

    public void setMembershipRepository(Repository<Membership> membershipRepository) {
        this.membershipRepository = membershipRepository;
    }
    
    public void setMembershipQueryRepository(OrganizationMembershipQueryRepository membershipQueryRepository) {
        this.membershipQueryRepository = membershipQueryRepository;
    }

    @CommandHandler
    public void handle(AuthorizeMembershipToOrganizationCreator command) {

        Organization organization = repository.load(command.getOrganizationId());

        if (organization.isDeleted()) {
            throw new IllegalArgumentException("Organization exists no more. " + command.getOrganizationId());
        }

        Membership membership = new Membership(
                command.getMembershipId(), 
                command.getOrganizationId(),
                command.getUsername(), 
                "",
                Membership.ApplicationType.CREATOR
        );
        
        membershipRepository.add(membership);
    }

    @CommandHandler
    public void handle(InviteUserToOrganization command) {

        Organization organization = repository.load(command.getOrganizationId());

        if (organization.isDeleted()) {
            throw new IllegalArgumentException("Organization exists no more. " + command.getOrganizationId());
        }

        if (isAlreadyAMember(command.getOrganizationId(), command.getUsername())) {
            // dublicate registration - just ignore
            return;
        }

        Membership membership = new Membership(
                command.getMembershipId(), 
                command.getOrganizationId(),
                command.getUsername(), 
                "",
                Membership.ApplicationType.INVITE
        );
        
        membershipRepository.add(membership);
    }

    @CommandHandler
    public void handle(ApplyForMembershipToOrganization command) {

        Organization organization = repository.load(command.getOrganizationId());

        if (organization.isDeleted()) {
            throw new IllegalArgumentException("Organization exists no more. " + command.getOrganizationId());
        }

        if (isAlreadyAMember(command.getOrganizationId(), command.getUsername())) {
            // dublicate registration - just ignore
            return;
        }

        Membership membership = new Membership(
                command.getMembershipId(), 
                command.getOrganizationId(),
                command.getUsername(), 
                command.getApplicationMessage(),
                Membership.ApplicationType.APPLICATION
        );
        membershipRepository.add(membership);
    }

    private boolean isAlreadyAMember(OrganizationId organizationId, String username) {
        // (HACK: probably should NOT be using a public query-model for this verification)
        return membershipQueryRepository.findByOrganizationIdAndUsername(organizationId.identifier(), username) != null;
    }

    @CommandHandler
    public void handle(PrepareServiceSpecification command) {

        Organization organization = repository.load(command.getOwnerId());

        if (organization.isDeleted()) {
            throw new IllegalArgumentException("Organization exists no more. " + command.getOwnerId());
        }

        ServiceSpecification serviceSpecification
                = organization.prepareServiceSpecification(command.getServiceSpecificationId(), command.getServiceType(), command.getName(), command.getSummary());

        serviceSpecificationRepository.add(serviceSpecification);
    }

    @CommandHandler
    public void handle(ProvideServiceInstance command) {

        Organization organization = repository.load(command.getProviderId());

        if (organization.isDeleted()) {
            throw new IllegalArgumentException("Organization exists no more. " + command.getProviderId());
        }

        ServiceSpecification serviceSpecification
                = serviceSpecificationRepository.load(command.getSpecificationId());

        if (serviceSpecification.isDeleted()) {
            throw new IllegalArgumentException("Service specification exists no more. " + command.getProviderId());
        }

        ServiceInstance serviceInstance
                = organization.provideServiceInstance(
                        serviceSpecification,
                        command.getServiceInstanceId(),
                        command.getName(),
                        command.getSummary(),
                        command.getCoverage()
                );

        serviceInstanceRepository.add(serviceInstance);

    }

}

// This code was generated by net.maritimecloud.common.cqrs.contract.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.axonframework.common.Assert;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.maritimecloud.common.cqrs.Command;
import net.maritimecloud.serviceregistry.command.organization.OrganizationId;
import net.maritimecloud.serviceregistry.command.servicespecification.ServiceSpecificationId;
import net.maritimecloud.serviceregistry.command.servicespecification.ServiceType;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#prepareServiceSpecification
 */
public class PrepareServiceSpecification implements Command {

    private final OrganizationId ownerId;
    @TargetAggregateIdentifier
    private final ServiceSpecificationId serviceSpecificationId;
    private final ServiceType serviceType;
    private final String name;
    private final String summary;

    @JsonCreator
    public PrepareServiceSpecification(
            @JsonProperty("ownerId") OrganizationId ownerId,
            @JsonProperty("serviceSpecificationId") ServiceSpecificationId serviceSpecificationId,
            @JsonProperty("serviceType") ServiceType serviceType,
            @JsonProperty("name") String name,
            @JsonProperty("summary") String summary
    ) {
        Assert.notNull(ownerId, "The ownerId must be provided");
        Assert.notNull(serviceSpecificationId, "The serviceSpecificationId must be provided");
        Assert.notNull(serviceType, "The serviceType must be provided");
        Assert.notNull(name, "The name must be provided");
        Assert.notNull(summary, "The summary must be provided");
        this.ownerId = ownerId;
        this.serviceSpecificationId = serviceSpecificationId;
        this.serviceType = serviceType;
        this.name = name;
        this.summary = summary;
    }

    public OrganizationId getOwnerId() {
        return ownerId;
    }

    public ServiceSpecificationId getServiceSpecificationId() {
        return serviceSpecificationId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

}


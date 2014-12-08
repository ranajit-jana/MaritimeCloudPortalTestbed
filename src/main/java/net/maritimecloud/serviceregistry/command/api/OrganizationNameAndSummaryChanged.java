// This code was generated by net.maritimecloud.cqrs.tool.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import net.maritimecloud.cqrs.tool.Event;
import net.maritimecloud.serviceregistry.command.organization.OrganizationId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#organizationNameAndSummaryChanged
 */
@Event
public class OrganizationNameAndSummaryChanged {

    @TargetAggregateIdentifier
    private final OrganizationId organizationId;
    private final String name;
    private final String summary;

    public OrganizationNameAndSummaryChanged(
            OrganizationId organizationId,
            String name,
            String summary
    ) {
        this.organizationId = organizationId;
        this.name = name;
        this.summary = summary;
    }

    public OrganizationId getOrganizationId() {
        return organizationId;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

}


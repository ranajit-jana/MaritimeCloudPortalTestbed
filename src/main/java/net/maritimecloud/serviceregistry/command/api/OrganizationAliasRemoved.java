// This code was generated by net.maritimecloud.cqrs.tool.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import net.maritimecloud.cqrs.tool.Event;
import net.maritimecloud.serviceregistry.command.organization.OrganizationId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#organizationAliasRemoved
 */
@Event
public class OrganizationAliasRemoved {

    @TargetAggregateIdentifier
    private final OrganizationId organizationId;
    private final String alias;

    public OrganizationAliasRemoved(
            OrganizationId organizationId,
            String alias
    ) {
        this.organizationId = organizationId;
        this.alias = alias;
    }

    public OrganizationId getOrganizationId() {
        return organizationId;
    }

    public String getAlias() {
        return alias;
    }

}


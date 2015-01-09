// This code was generated by net.maritimecloud.cqrs.tool.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import net.maritimecloud.cqrs.tool.Event;
import net.maritimecloud.serviceregistry.command.organization.OrganizationId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#UserInvitedToOrganization
 */
@Event
public class UserInvitedToOrganization {

    @TargetAggregateIdentifier
    private final OrganizationId organizationId;
    private final String username;

    public UserInvitedToOrganization(
            OrganizationId organizationId,
            String username
    ) {
        this.organizationId = organizationId;
        this.username = username;
    }

    public OrganizationId getOrganizationId() {
        return organizationId;
    }

    public String getUsername() {
        return username;
    }

}

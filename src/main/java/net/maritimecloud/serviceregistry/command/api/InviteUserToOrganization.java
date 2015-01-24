// This code was generated by net.maritimecloud.cqrs.tool.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.axonframework.common.Assert;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.maritimecloud.common.cqrs.Command;
import net.maritimecloud.serviceregistry.command.organization.OrganizationId;
import net.maritimecloud.serviceregistry.command.organization.membership.MembershipId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#inviteUserToOrganization
 */
public class InviteUserToOrganization implements Command {

    @TargetAggregateIdentifier
    private final OrganizationId organizationId;
    private final MembershipId membershipId;
    private final String username;

    @JsonCreator
    public InviteUserToOrganization(
            @JsonProperty("organizationId") OrganizationId organizationId,
            @JsonProperty("membershipId") MembershipId membershipId,
            @JsonProperty("username") String username
    ) {
        Assert.notNull(organizationId, "The organizationId must be provided");
        Assert.notNull(membershipId, "The membershipId must be provided");
        Assert.notNull(username, "The username must be provided");
        this.organizationId = organizationId;
        this.membershipId = membershipId;
        this.username = username;
    }

    public OrganizationId getOrganizationId() {
        return organizationId;
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }

    public String getUsername() {
        return username;
    }

}


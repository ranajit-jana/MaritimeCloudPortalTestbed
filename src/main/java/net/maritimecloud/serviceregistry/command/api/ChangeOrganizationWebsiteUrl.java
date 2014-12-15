// This code was generated by net.maritimecloud.cqrs.tool.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.axonframework.common.Assert;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.maritimecloud.serviceregistry.command.Command;
import net.maritimecloud.serviceregistry.command.organization.OrganizationId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#changeOrganizationWebsiteUrl
 */
public class ChangeOrganizationWebsiteUrl implements Command {

    @TargetAggregateIdentifier
    private final OrganizationId organizationId;
    private final String url;

    @JsonCreator
    public ChangeOrganizationWebsiteUrl(
            @JsonProperty("organizationId") OrganizationId organizationId,
            @JsonProperty("url") String url
    ) {
        Assert.notNull(organizationId, "The organizationId must be provided");
        Assert.notNull(url, "The url must be provided");
        this.organizationId = organizationId;
        this.url = url;
    }

    public OrganizationId getOrganizationId() {
        return organizationId;
    }

    public String getUrl() {
        return url;
    }

}


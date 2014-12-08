// This code was generated by net.maritimecloud.cqrs.tool.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import net.maritimecloud.cqrs.tool.Event;
import net.maritimecloud.serviceregistry.command.serviceinstance.ServiceInstanceId;
import net.maritimecloud.serviceregistry.command.serviceinstance.ServiceEndpoint;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#serviceInstanceEndpointAdded
 */
@Event
public class ServiceInstanceEndpointAdded {

    @TargetAggregateIdentifier
    private final ServiceInstanceId serviceInstanceId;
    private final ServiceEndpoint serviceEndpoint;

    public ServiceInstanceEndpointAdded(
            ServiceInstanceId serviceInstanceId,
            ServiceEndpoint serviceEndpoint
    ) {
        this.serviceInstanceId = serviceInstanceId;
        this.serviceEndpoint = serviceEndpoint;
    }

    public ServiceInstanceId getServiceInstanceId() {
        return serviceInstanceId;
    }

    public ServiceEndpoint getServiceEndpoint() {
        return serviceEndpoint;
    }

}


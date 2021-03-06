// This code was generated by net.maritimecloud.common.cqrs.contract.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.serviceregistry.command.ServiceRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.serviceregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import net.maritimecloud.common.cqrs.contract.Event;
import net.maritimecloud.serviceregistry.command.serviceinstance.ServiceInstanceId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.serviceregistry.command.ServiceRegistryContract#serviceInstanceNameAndSummaryChanged
 */
@Event
public class ServiceInstanceNameAndSummaryChanged {

    @TargetAggregateIdentifier
    private final ServiceInstanceId serviceInstanceId;
    private final String name;
    private final String summary;

    public ServiceInstanceNameAndSummaryChanged(
            ServiceInstanceId serviceInstanceId,
            String name,
            String summary
    ) {
        this.serviceInstanceId = serviceInstanceId;
        this.name = name;
        this.summary = summary;
    }

    public ServiceInstanceId getServiceInstanceId() {
        return serviceInstanceId;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

}


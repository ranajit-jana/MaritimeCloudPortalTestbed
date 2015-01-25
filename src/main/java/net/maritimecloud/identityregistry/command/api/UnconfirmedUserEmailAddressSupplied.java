// This code was generated by net.maritimecloud.common.cqrs.contract.SourceGenerator
// Generated Code is based on the contract defined in net.maritimecloud.identityregistry.command.IdentityRegistryContract
// Please modify the contract instead of this file!
package net.maritimecloud.identityregistry.command.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import net.maritimecloud.common.cqrs.contract.Event;
import net.maritimecloud.identityregistry.command.user.UserId;

/**
 * GENERATED CLASS!
 * @see net.maritimecloud.identityregistry.command.IdentityRegistryContract#UnconfirmedUserEmailAddressSupplied
 */
@Event
public class UnconfirmedUserEmailAddressSupplied {

    @TargetAggregateIdentifier
    private final UserId userId;
    private final String username;
    private final String unconfirmedEmailAddress;
    private final String emailVerificationCode;

    public UnconfirmedUserEmailAddressSupplied(
            UserId userId,
            String username,
            String unconfirmedEmailAddress,
            String emailVerificationCode
    ) {
        this.userId = userId;
        this.username = username;
        this.unconfirmedEmailAddress = unconfirmedEmailAddress;
        this.emailVerificationCode = emailVerificationCode;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUnconfirmedEmailAddress() {
        return unconfirmedEmailAddress;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

}


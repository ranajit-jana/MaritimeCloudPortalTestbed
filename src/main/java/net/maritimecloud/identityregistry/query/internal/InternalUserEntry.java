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
package net.maritimecloud.identityregistry.query.internal;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import net.maritimecloud.identityregistry.command.user.UserId;
import net.maritimecloud.identityregistry.domain.Identity;

/**
 * An internal
 * <p>
 * @author Christoffer Børrild
 */
@Entity
public class InternalUserEntry implements Identity, Serializable {

    @Id
    private String userId;
    // TODO: add unique index
    private String username;
    // TODO: add unique index
    private String emailAddress;

    private String encryptedPassword;
    private boolean activated;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public UserId userId() {
        return new UserId(getUserId());
    }

    @Override
    public String encryptedPassword() {
        return getEncryptedPassword();
    }

    @Override
    public String username() {
        return getUsername();
    }

}

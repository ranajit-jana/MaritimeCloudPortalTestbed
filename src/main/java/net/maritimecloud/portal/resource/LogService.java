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
package net.maritimecloud.portal.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application specific logging Service for reporting significant application events to a general logging mechanism.
 * <p>
 * @author Christoffer Børrild
 */
public class LogService {

    private static final Logger LOG = LoggerFactory.getLogger(LogService.class);

    void reportUserLoggedIn(String aUsername) {
        LOG.info("User {} logged in", aUsername);
    }

    void reportWrongUsernamePassword(String username) {
        LOG.info("User {} not logged in (wrong username / password)", username);
    }

    void reportDebugError(String current_user_is_not_authenticated_, Exception e) {
        LOG.info("Current user is not authenticated: ", e);
    }

    void reportUserLoggingOut() {
        LOG.info("User logged out");
    }

    public void activateAccountFailedUserNotFound(String aUsername) {
        LOG.info("User account activation failed. Unknown user: {}", aUsername);
    }

    public void activateAccountFailed(String aUsername, String triedActivationId, String usersActivationId) {
        LOG.info("User account activation failed. User was {}: \nusers activationId {} \ntried activationId {} ", aUsername, usersActivationId, triedActivationId);
    }

    public void activateAccountSucceded(String aUsername) {
        LOG.info("User account activated for user {}", aUsername);
    }

    public void sendResetPasswordMessageFailedUserOfEmailNotFound(String emailAddress) {
        LOG.info("Reset password mail was requested, but found no user with email {}", emailAddress);
    }

}

/* Copyright (c) 2011 Danish Maritime Authority.
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

import javax.ws.rs.core.Application;
import net.maritimecloud.portal.JerseyConfig;
import net.maritimecloud.portal.config.ApplicationTestConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import net.maritimecloud.portal.application.ApplicationServiceTest;
import net.maritimecloud.portal.domain.model.identity.User;
import org.junit.After;
import org.junit.Before;

/**
 * This class extends the JerseyTest class to expose the convenient features of the Jersey test framework. It however, also exposes the
 * features of the ApplicationServiceTest as it wraps this class an proxies (most of) its features.
 * <p>
 * @author Christoffer Børrild
 * @see ApplicationServiceTest
 */
public abstract class ResourceTest extends JerseyTest {

    private ApplicationContext sharedApplicationContext;

    ApplicationServiceTestImpl applicationServiceTest = new ApplicationServiceTestImpl();

    @Override
    protected Application configure() {
        sharedApplicationContext = createApplicationContext();
        return new JerseyConfig().property("contextConfig", sharedApplicationContext);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        applicationServiceTest.setUp();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        applicationServiceTest.tearDown();
    }
    
    
    /**
     * Sugar syntax helper method to create a json array. 
     * @param jsonObjects json formatted objects
     * @return a json formatted array of the supplied json objects
     */
    protected String array(String... jsonObjects){
        String jsons = "[\n  ";
        for (String json : jsonObjects) {
            jsons += json + ",\n  ";
        }
        return jsons.substring(0, jsons.length()-4) + "\n]";
    }
    
    /**
     * Sugar syntax helper method to create a json object given a list of name and value pairs. 
     * 
     * @param namesAndValues pairs of names and values
     * @return a json formatted object of the supplied names and values
     */
    protected String asJson(String... namesAndValues){
        String body = "{\n  ";
        for (int i = 0; i < namesAndValues.length; i+=2) {
            String name = namesAndValues[i];
            String value = namesAndValues[i+1];
            body += "    '"+name+"':'"+value+"',\n";            
        }
        return body.substring(0, body.length()-2) + "\n}";
    }            
    
    

    /**
     * Exposed ApplicationServiceTest method
     * <p>
     * @see ApplicationServiceTest#createApplicationContext()
     */
    protected ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(ApplicationTestConfig.class);
    }

    /**
     * Exposed ApplicationServiceTest method
     * <p>
     * @see ApplicationServiceTest#aUser()
     */
    protected User aUser() {
        return applicationServiceTest.aUser();
    }

    private class ApplicationServiceTestImpl extends ApplicationServiceTest {

        public ApplicationServiceTestImpl() {
        }

        @Override
        protected ApplicationContext createApplicationContext() {
            return sharedApplicationContext;
        }

        @Override
        public User aUser() {
            return super.aUser();
        }
    }

}
/*
 * (C) Copyright 2014 FINESCE-WP4.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package eu.finesce.emarketplace.test;

import java.io.IOException;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.finesce.emarketplace.ContractManagerProxy;
import eu.finesce.emarketplace.domain.Meter;

/**
 * The Class ConnectivityTest.
 */
public class ConnectivityTest {
	
	/**
	 * Utilizzato per accetta re il certificato self-signed registrato nel cacerts indipendentemente dall'host chiamato
	 */
	static {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			
			@Override
			public boolean verify(String hostname, SSLSession sslSession) {
				return true;
			}
		});
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
//		SslConfigurator sslConfig = SslConfigurator.newInstance()
//				.securityProtocol("SSL");
//		SSLContext sslContext = sslConfig.createSSLContext();
//		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
		Log logger = LogFactory
				.getLog(ContractManagerProxy.class);
//		Properties prop = new Properties();
//		try {
//			prop.load(this.getClass().getClassLoader()
//					.getResourceAsStream("config.properties"));
//			
//			logger.info("APPLICATION PATH: " + prop.getProperty("application_path"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		WebTarget webTarget = client.target(prop.getProperty("application_path"));
//		WebTarget resourceWebTarget = webTarget.path("meterRegistration");
//		resourceWebTarget.register(HttpAuthenticationFeature.basic(
//				prop.getProperty("username"), prop.getProperty("password")));
//		Response responseEntity = resourceWebTarget.request(
//				MediaType.APPLICATION_XML).get();
//		
//	logger.info(responseEntity.readEntity(Meter.class));
//		
		logger.info("test ok");
	}

}

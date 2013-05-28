/*
 * Copyright 2013 JUGChennai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.jugchennai.javamoney.jpa.tools.rateloader;

import in.jugchennai.javamoney.jpa.service.ExchangeRateService;
import org.glassfish.jms.admin.cli.CreateJMSResource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Balaji T
 */
public class RateLoaderTest {
    
    public RateLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class RateLoader.
     */
    @Ignore
    @Test
    public void testRun() {
        System.out.println("run");
//        Thread instance = new Thread(new RateLoader(new RateStatusProvider(new ExchangeRateService()),new RateReaderImpl(),new JsonRateFormatter(),new MQPublisherImpl()));
//        instance.start();        
        RateLoader rateLoader = new RateLoader(new RateStatusProvider(new ExchangeRateService()),new RateReaderImpl(),new JsonRateFormatter(),new MQPublisherImpl(new GlassFishMQPublishStrategy()));
        rateLoader.run();
        
    }
}
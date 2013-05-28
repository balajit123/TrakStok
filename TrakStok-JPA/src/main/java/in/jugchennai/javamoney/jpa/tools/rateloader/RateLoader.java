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

import in.jugchennai.javamoney.jpa.service.entity.TsExchangeRate;
import java.util.Collection;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * RateLoader is a demon thread that polls the URL and loads the exchange rates 
 * to the JMS queue
 * 
 * @author Balaji T
 */
public class RateLoader implements Runnable{
    
    private RateReader rateReader;
    private RateFormatter rateFormatter;
    private MQPublisher mqPublisher;
    private RateStatusProvider rateStatusProvider;
    private static final Logger LOGGER = Logger.getLogger(RateLoader.class.toString());    
    
    @Inject
    public RateLoader(RateStatusProvider rateStatusProvider,RateReader rateReader, RateFormatter rateFormatter, MQPublisher mqPublisher) {
        if(rateStatusProvider == null){
            throw new IllegalArgumentException("Rate status provider should not be null in RateLoader");
        }
        if(rateReader == null){
            throw new IllegalArgumentException("Rate Reader should not be null in RateLoader");
        }
        if(rateFormatter == null){
            throw new IllegalArgumentException("Rate Formatter should not be null in RateLoader");
        }
        if(mqPublisher == null){
            throw new IllegalArgumentException("MQ publisher should not be null in RateLoader");
        }
        this.rateStatusProvider = rateStatusProvider;
        this.rateReader = rateReader;
        this.rateFormatter = rateFormatter;
        this.mqPublisher = mqPublisher;
    }

    @Override
    public void run() {
        loadExchangeRates();
    }

    private void loadExchangeRates() {
        if(!rateStatusProvider.isRateUptoDate()){
            Collection<TsExchangeRate> exchangeRates = rateReader.readExchangeRates();
            if(exchangeRates != null && !exchangeRates.isEmpty()){
                LOGGER.info(exchangeRates.toString());
                Collection<RateFormat> formats = rateFormatter.formatExchangeRates(exchangeRates);
                LOGGER.info(formats.toString());
                mqPublisher.pushExchangeRatesToMQ(formats);
                LOGGER.info("Exchange rates are loaded to JMS queue successfully");
            }else{
                LOGGER.warning("Exchange rates are not available from the Rate Reader");
            }            
        }else{
            LOGGER.info("Exchange rates are already up to date");
        }
        
    }
}

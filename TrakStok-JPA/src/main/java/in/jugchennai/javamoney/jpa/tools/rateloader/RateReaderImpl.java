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
import in.jugchennai.javamoney.jpa.tools.Cube;
import in.jugchennai.javamoney.jpa.tools.StaXParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Balaji T
 */
class RateReaderImpl implements RateReader{
    
    private static final String SOURCE_CURRENCY_CODE = "EUR";
    private static final String FEED_URL = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

    public RateReaderImpl() {
    }

    @Override
    public TsExchangeRate readExchangeRate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<TsExchangeRate> readExchangeRates() {
        Collection<TsExchangeRate> result = null;
        
        StaXParser read = new StaXParser();
        try {
            List<Cube> stokList = read.readConfig(FEED_URL);
            if (stokList != null && !stokList.isEmpty()) {
                result = new ArrayList<>();
                for(Cube cube : stokList){
                    TsExchangeRate rate = new TsExchangeRate();
                    rate.setCurrencyCode(cube.getCurrency());
                    rate.setExchangeDate(Date.valueOf(cube.getDate()));
                    rate.setRate(Double.valueOf(cube.getRate()));
                    rate.setSourceCurrencyCode(SOURCE_CURRENCY_CODE);
                    result.add(rate);
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(RateReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RateReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}

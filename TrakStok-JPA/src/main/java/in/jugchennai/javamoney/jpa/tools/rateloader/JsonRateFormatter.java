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

import in.jugchennai.javamoney.jpa.tools.TrakStokUtils;
import in.jugchennai.javamoney.jpa.service.entity.TsExchangeRate;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Balaji T
 */
class JsonRateFormatter implements RateFormatter{

    public JsonRateFormatter() {
    }

    @Override
    public RateFormat formatExchangeRate(TsExchangeRate rate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<RateFormat> formatExchangeRates(Collection<TsExchangeRate> rates) {
        TrakStokUtils.validateParameter(rates,"Exchange rates should not be null or empty");
        Collection<RateFormat> formats = new ArrayList<>();
        for(TsExchangeRate rate : rates){
            RateFormat format =  new JsonRateFomat(rate);
            formats.add(format);
        }        
        return formats;
    }
    
}

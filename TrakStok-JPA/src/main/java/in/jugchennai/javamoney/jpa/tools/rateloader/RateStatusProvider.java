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
import in.jugchennai.javamoney.jpa.tools.TrakStokDateUtils;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Balaji T
 */
public class RateStatusProvider {
    
    @Inject
    private ExchangeRateService exchangeRateService;

    public RateStatusProvider() {
    }

    public RateStatusProvider(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }
    
    public boolean isRateUptoDate(){        
        Date lastLoadedDateTime = checkLastLoadedDateTime();
        return TrakStokDateUtils.isToday(lastLoadedDateTime);         
    }
    
    public Date checkLastLoadedDateTime(){
        return exchangeRateService.getLatestLoadedDate();
    }
}

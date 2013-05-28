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
import in.jugchennai.javamoney.jpa.tools.TrakStokDateUtils;
import java.util.logging.Logger;

/**
 *
 * @author Balaji T
 */
public class JsonRateFomat implements RateFormat {
    
    private static final Logger LOGGER = Logger.getGlobal();
    private static final String OBJECT_BEGIN = "{";
    private static final String OBJECT_END = "}";
    private static final String COLON = ":";
    private static final String COMMA = ",";
    private static final String ARRAY_BEGIN = "[";
    private static final String ARRAY_END = "]";
    
    private TsExchangeRate rate;
    
    public JsonRateFomat(TsExchangeRate rate) {
        this.rate = rate;
    }

    @Override
    public String format() {
        StringBuilder formattedString = new StringBuilder();
        formattedString.append(OBJECT_BEGIN);
        appendNameValuePair(formattedString,"exchangeRateId",rate.getExchangeRateId(),true);
        appendNameValuePair(formattedString,"currencyCode",rate.getCurrencyCode(),true);
        appendNameValuePair(formattedString,"exchangeDate", TrakStokDateUtils.format(rate.getExchangeDate(),"yyyy-mm-dd"),true);
        appendNameValuePair(formattedString,"rate",rate.getRate(),true);
        appendNameValuePair(formattedString,"sourceCurrencyCode",rate.getSourceCurrencyCode(),false);
        formattedString.append(OBJECT_END);
        LOGGER.info(formattedString.toString());
        return formattedString.toString();
    }

    private void appendNameValuePair(StringBuilder builder,String name,String value,boolean insertComma) {
        builder.append(name);
        builder.append(COLON);
        builder.append(value);
        if(insertComma){
            builder.append(COMMA);
        }
    }
    
    private void appendNameValuePair(StringBuilder builder,String name,Number value,boolean insertComma) {
        builder.append(name);
        builder.append(COLON);
        builder.append(value);
        if(insertComma){
            builder.append(COMMA);
        }
    }    

    @Override
    public TsExchangeRate getRate() {
        return this.rate;
    }

    @Override
    public void setRate(TsExchangeRate rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return format();
    }
}

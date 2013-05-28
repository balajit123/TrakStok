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
package in.jugchennai.javamoney.jpa.service.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Balaji T
 */
@Entity
@Table(name = "TS_EXCHANGE_RATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsExchangeRate.findAll", query = "SELECT t FROM TsExchangeRate t"),
    @NamedQuery(name = "TsExchangeRate.findLatestLoadedDate", query = "SELECT max(t.exchangeDate) FROM TsExchangeRate t")
})
public class TsExchangeRate implements Serializable{

    @Id
    @Basic(optional = false)
    @Column(name = "EXCHANGE_RATE_ID")
    private int exchangeRateId;
    @Basic(optional = false)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Basic(optional = false)
    @Column(name = "EXCHANGE_DATE")
    private Date exchangeDate;
    @Basic(optional = false)
    @Column(name = "RATE")
    private Number rate;
    @Basic(optional = false)
    @Column(name = "SOURCE_CURRENCY_CODE")
    private String sourceCurrencyCode;

    public int getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(int exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Number getRate() {
        return rate;
    }

    public void setRate(Number rate) {
        this.rate = rate;
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public void setSourceCurrencyCode(String sourceCurrencyCode) {
        this.sourceCurrencyCode = sourceCurrencyCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.exchangeRateId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TsExchangeRate other = (TsExchangeRate) obj;
        if (this.exchangeRateId != other.exchangeRateId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TsExchangeRate{" + "currencyCode=" + currencyCode + ", exchangeDate=" + exchangeDate + ", rate=" + rate + ", sourceCurrencyCode=" + sourceCurrencyCode + '}';
    }
    
}

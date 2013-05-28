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
package in.jugchennai.javamoney.jpa.service;

import in.jugchennai.javamoney.jpa.service.entity.TsExchangeRate;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Balaji T
 */
public class ExchangeRateService {
    
    private static final Logger LOGGER = Logger.getGlobal();
    private static EntityManagerFactory emFactory;
    private static EntityManager eManager;
    
    static{
        emFactory = Persistence.createEntityManagerFactory("trakStokPU");
        eManager = emFactory.createEntityManager();
    }

    public ExchangeRateService() {
    }
        
    public Date getLatestLoadedDate(){
        Query qry = eManager.createNamedQuery("TsExchangeRate.findLatestLoadedDate");
        Date result = (Date) qry.getSingleResult();
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); 
        eManager.close();
        emFactory.close();
    }
    
    
    
}

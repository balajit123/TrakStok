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
package in.jugchennai.javamoney.jpa.tools;

import in.jugchennai.javamoney.jpa.service.entity.TsExchangeRate;
import java.util.Collection;

/**
 *
 * @author Balaji T
 */
public class TrakStokUtils {

    public static void validateParameter(Object object, String validationMsg) {
        if(object == null){
            throw new IllegalArgumentException(validationMsg);            
        }
        if(object instanceof String && ((String)object).trim().equals("")){
            throw new IllegalArgumentException(validationMsg);
        }
        if(object instanceof Collection && ((Collection)object).isEmpty()){
            throw new IllegalArgumentException(validationMsg);
        }
    }
    
}

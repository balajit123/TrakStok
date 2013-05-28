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

import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Balaji T
 */
class MQPublisherImpl implements MQPublisher {
    
    @Inject
    private MQPublishStrategy strategy;

    public MQPublisherImpl() {
    }

    public MQPublisherImpl(MQPublishStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean pushExchangeRatesToMQ(Collection<RateFormat> formats) {
        boolean success = false;        
        success = strategy.publishRatesToMQ(formats);
        return success;
    }
    
}

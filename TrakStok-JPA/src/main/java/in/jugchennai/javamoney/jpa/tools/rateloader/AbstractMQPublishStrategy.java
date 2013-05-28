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
import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author Balaji T
 */
public abstract class AbstractMQPublishStrategy implements MQPublishStrategy{
    
    private Connection connection;
    private Session session;
    private Topic topic;
    private MessageProducer msgProducer;

    @Override
    public final boolean publishRatesToMQ(Collection<RateFormat> formats) {
        boolean success;        
        connection = openMQConnection();
        session = createMQSession(connection);
        topic = createMqTopic(session);
        msgProducer = createMsgProducer(session,topic);
        Collection<Message> messages = createMessages(session,formats);
        success = sendMessages(msgProducer,messages);        
        return success;
    }    

    public abstract Connection openMQConnection();

    public abstract Session createMQSession(Connection connection);

    public abstract Topic createMqTopic(Session session);

    public abstract MessageProducer createMsgProducer(Session session, Topic topic);

    public abstract Collection<Message> createMessages(Session session, Collection<RateFormat> formats);

    public abstract boolean sendMessages(MessageProducer msgProducer, Collection<Message> messages);
    
}

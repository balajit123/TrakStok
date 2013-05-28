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
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author Balaji T
 */
public class GlassFishMQPublishStrategy extends AbstractMQPublishStrategy{
    
    @Resource(mappedName = "jms/TrakStokConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/TrakStokTopic")
    private Topic topic;
    
    private static final Logger LOGGER = Logger.getGlobal();

    public GlassFishMQPublishStrategy() {
    }

    public GlassFishMQPublishStrategy(ConnectionFactory connectionFactory, Topic topic) {
        this.connectionFactory = connectionFactory;
        this.topic = topic;
    }    

    @Override
    public Connection openMQConnection() {        
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, "JMS Connection could not be created at Glassfish server", ex);
        }
        return connection;
    }

    @Override
    public Session createMQSession(Connection connection) {
        TrakStokUtils.validateParameter(connection, "Connection should not be null");
        Session session = null;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, "JMS Session could not be created at Glassfish server", ex);
        }
        return session;
    }

    @Override
    public Topic createMqTopic(Session session) {        
        TrakStokUtils.validateParameter(session, "Session should not be null");
        return topic;
    }

    @Override
    public MessageProducer createMsgProducer(Session session, Topic topic) {
        TrakStokUtils.validateParameter(session, "Session should not be null");
        TrakStokUtils.validateParameter(topic, "Topic should not be null");
        MessageProducer producer = null;
        try {
            session.createProducer(topic);
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, "Message producer could not be created at Glassfish server", ex);
        }
        return producer;
    }

    @Override
    public Collection<Message> createMessages(Session session, Collection<RateFormat> formats) {
        TrakStokUtils.validateParameter(session, "Session should not be null");
        TrakStokUtils.validateParameter(formats, "Rate Format collection should not be null or empty");
        Collection<Message> messages = new ArrayList<>();
        int i = 0;
        for(RateFormat format : formats){
            i++;
            try {
                Message message = session.createTextMessage(format.format());
                message.setStringProperty("id", String.valueOf(i));
                messages.add(message);
            } catch (JMSException ex) {
                LOGGER.log(Level.SEVERE, "Message could not be created at Glassfish server", ex);
            }
        }
        return messages;
    }

    @Override
    public boolean sendMessages(MessageProducer msgProducer, Collection<Message> messages) {
        TrakStokUtils.validateParameter(msgProducer, "Message producer should not be null");
        TrakStokUtils.validateParameter(messages, "Message Collection should not be null or empty");
        boolean success = false;
        for(Message message : messages){
            try {
                msgProducer.send(message);
                success = true;
            } catch (JMSException ex) {
                LOGGER.log(Level.SEVERE, "Message could not be sent at Glassfish server", ex);
            }
        }
        return success;
    }
    
}

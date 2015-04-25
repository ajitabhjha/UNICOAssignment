package au.com.unico.assignment.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSHelper {

    private static ConnectionFactory connectionFactory;
    private static Queue unicoQueue;


    /**
     * this method pushes an integer into the pre-configured JMS queue.
     *
     * @param i
     * @return
     */
    public boolean pushToQueue(int i) {
        boolean retVal = false;
        Connection connection = null;
        try {
            Context jndiContext = new InitialContext();

            if (connectionFactory == null) {
                connectionFactory = (ConnectionFactory) jndiContext.lookup(Constants.JNDI_JMS_CONNECTION_FACTORY);
            }
            if (unicoQueue == null) {
                unicoQueue = (Queue) jndiContext.lookup(Constants.JNDI_JMS_QUEUE);
            }
            connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(unicoQueue);
            StreamMessage message = session.createStreamMessage();
            message.writeInt(i);
            publisher.send(message);
            retVal = true;
        } catch (Exception ex) {
            Logger.getLogger(JMSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                   Logger.getLogger(JMSHelper.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return retVal;
    }

    /**
     * this method pops the element from head of the JMS queue and removes the
     * element from the queue.
     *
     * @return
     */
    public int popFromQueue() {
        int i = -1;
        Connection connection = null;
        try {
            Context jndiContext = new InitialContext();
            if (connectionFactory == null) {
                connectionFactory = (ConnectionFactory) jndiContext.lookup(Constants.JNDI_JMS_CONNECTION_FACTORY);
            }
            if (unicoQueue == null) {
                unicoQueue = (Queue) jndiContext.lookup(Constants.JNDI_JMS_QUEUE);
            }
            connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(unicoQueue);
            StreamMessage message = (StreamMessage) consumer.receiveNoWait();
            i = message.readInt();
        } catch (Exception ex) {
            Logger.getLogger(JMSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    Logger.getLogger(JMSHelper.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return i;
    }
}

package com.fntsoftware.hackathon.devobst.catalog.control.esi;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import com.fntsoftware.hackathon.devobst.catalog.entity.Fruit;

/**
 * External Interface implementation
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class FruitPublisherImpl implements FruitPublisher {
	
	@Resource(name="devobstConnectionFactory")
	TopicConnectionFactory connectionFactory;
	
	@Resource(name="devobstTopic")
	Topic topic;
	
	@Inject
	FruitEventWriter eventWriter;
	
	private static final String MESSAGE_TYPE_FRUIT_CREATED = "FRUIT_CREATED";
	
	private static final String MESSAGE_TYPE_FRUIT_UPDATED = "FRUIT_UPDATED";
	private static final String MESSAGE_TYPE_FRUIT_DELETD = "FRUIT_DELETED";

	@Override
	public void publishCreate(Fruit fruit) {			
		try (final Session session = connectionFactory.createConnection().createSession();
				final MessageProducer messageProducer = session.createProducer(topic)) {
			final TextMessage message = session.createTextMessage();
			message.setJMSType(MESSAGE_TYPE_FRUIT_CREATED);
			message.setText(eventWriter.toJSON(fruit));
			messageProducer.send(message);
		} catch (JMSException e) {
			throw new EJBException(e);
		} 				
	}

	@Override
	public void publishUpdate(Fruit fruit) {
		try (final Session session = connectionFactory.createConnection().createSession();
				final MessageProducer messageProducer = session.createProducer(topic)) {
			final TextMessage message = session.createTextMessage();
			message.setJMSType(MESSAGE_TYPE_FRUIT_UPDATED);
			message.setText(eventWriter.toJSON(fruit));
			messageProducer.send(message);
		} catch (JMSException e) {
			throw new EJBException(e);
		} 			
	}

	@Override
	public void publishDelete(String fruitUuid) {
		try (final Session session = connectionFactory.createConnection().createSession();
				final MessageProducer messageProducer = session.createProducer(topic)) {
			final TextMessage message = session.createTextMessage();
			message.setJMSType(MESSAGE_TYPE_FRUIT_DELETD);
			message.setText(fruitUuid);
			messageProducer.send(message);
		} catch (JMSException e) {
			throw new EJBException(e);
		} 			
	}

}

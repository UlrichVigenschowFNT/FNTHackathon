package com.fntsoftware.hackathon.devobst.billing.boundary;

import java.io.StringReader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.fntsoftware.hackathon.devobst.billing.control.ProductNewTransactionCtrl;

/**
 * Message consumer for fruit events
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "devobstTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = FruitSubscriber.MESSAGE_SELECTOR),
		@ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "FruitSubscriber"),
		@ActivationConfigProperty(propertyName = "endpointExceptionRedeliveryAttempts", propertyValue = "2"),
		@ActivationConfigProperty(propertyName = "clientId", propertyValue = "FruitSubscriber") })
public class FruitSubscriber implements MessageListener {

	static final String JMS_TYPE_FRUIT_CREATED = "FRUIT_CREATED";

	static final String JMS_TYPE_FRUIT_UPDATED = "FRUIT_UPDATED";

	static final String JMS_TYPE_FRUIT_DELETED = "FRUIT_DELETED";

	static final String MESSAGE_SELECTOR = "JMSType='" + JMS_TYPE_FRUIT_CREATED + "' OR JMSType='"
			+ JMS_TYPE_FRUIT_UPDATED + "' OR JMSType='" + JMS_TYPE_FRUIT_DELETED + "'";

	@Inject
	ProductNewTransactionCtrl productCtrl;

	@Override
	public void onMessage(final Message message) {

		try {
			switch (message.getJMSType()) {
			case JMS_TYPE_FRUIT_CREATED:
			case JMS_TYPE_FRUIT_UPDATED:
				String jsonString = message.getBody(String.class);

				try (final JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
					final JsonObject jsonObject = jsonReader.readObject();
					String name = jsonObject.getString("name");
					String uuid = jsonObject.getString("uuid");

					if (JMS_TYPE_FRUIT_CREATED.equals(message.getJMSType())) {
						productCtrl.registerNewProduct(uuid, name);
					} else {
						productCtrl.updateProductName(uuid, name);
					}
				}

				break;

			case JMS_TYPE_FRUIT_DELETED:
				String uuid = message.getBody(String.class);

				productCtrl.deprecateProduct(uuid);

				break;
			}
		} catch (JMSException e) {
			// TODO logger or exception handling needed
		}
	}
}

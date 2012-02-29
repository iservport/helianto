package org.helianto.message.sender;

import org.helianto.message.MessageAdapter;
import org.helianto.message.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testing mail sender.
 * 
 * @author mauriciofernandesdecastro
 */
public class TestingMailSender implements MessageSender {

	public void prepareMessage(MessageAdapter<?> message) {

	}

	public String sendMessage(MessageAdapter<?> message) {
		if (message!=null) {
			logger.info("Testing message: {}", message.getMessage());
			return "Testing message printed to the log";
		}
		logger.info("Null testing message.");
		return "Null testing message.";
	}

	private static final Logger logger = LoggerFactory.getLogger(TestingMailSender.class);

}

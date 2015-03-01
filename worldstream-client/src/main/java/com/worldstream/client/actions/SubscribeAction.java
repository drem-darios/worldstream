package com.worldstream.client.actions;

import com.worldstream.bus.Consumer;
import com.worldstream.bus.KafkaProperties;
import com.worldstream.client.messages.Message;
import com.worldstream.client.messages.ServerMessage;
import com.worldstream.client.messages.strategy.DefaultReply;
import com.worldstream.client.messages.strategy.MessageStrategy;

/**
 * @author drem
 */
public class SubscribeAction implements Action {
//	private static final Logger Log = Logger.getLogger(SubscribeAction.class.getSimpleName());
	/**
     * The name of this action
     */
    private static final String ACTION = "subscribe";
    /**
     * Strategy used to process the reply message
     */
    private MessageStrategy strategy = new DefaultReply();
    
	@Override
	public Message createMessage() {
		return new ServerMessage(ACTION + ";stream:blah;;");
	}

	@Override
	public String getActionName() {
		return ACTION;
	}

	@Override
	public String processReply(Message message) {
        String topic = strategy.processMessage(message);
    	Consumer consumerThread = new Consumer(KafkaProperties.topic);
        consumerThread.start();
        return topic;
	}
}

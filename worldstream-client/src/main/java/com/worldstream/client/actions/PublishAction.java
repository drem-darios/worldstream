package com.worldstream.client.actions;

import com.worldstream.client.messages.Message;
import com.worldstream.client.messages.ServerMessage;
import com.worldstream.client.messages.strategy.DefaultReply;
import com.worldstream.client.messages.strategy.MessageStrategy;

/**
 * @author drem
 */
public class PublishAction implements Action {
//	private static final Logger Log = Logger.getLogger(PublishAction.class.getSimpleName());
    /**
     * The name of this action
     */
    private static final String ACTION = "publish";
    /**
     * Strategy used to process the reply message
     */
    private MessageStrategy strategy = new DefaultReply();
    
    
	@Override
	public Message createMessage() {
		return new ServerMessage(ACTION+";upload:hello;;");
	}

	@Override
	public String getActionName() {
		return ACTION;
	}

	@Override
	public String processReply(Message message) {
        return strategy.processMessage(message);
	}

}

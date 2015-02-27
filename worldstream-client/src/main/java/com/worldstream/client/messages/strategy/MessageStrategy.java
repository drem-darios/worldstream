package com.worldstream.client.messages.strategy;

import com.worldstream.client.messages.Message;

/**
 * Strategy for handling messages and producing a result.
 * 
 * @author Drem Darios
 *
 */
public interface MessageStrategy
{
    /**
     * Processes the message in a specific way.
     * 
     * @param message The message to be processed.
     * @return A result String
     */
    String processMessage(Message message);
}

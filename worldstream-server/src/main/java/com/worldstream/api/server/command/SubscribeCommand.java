package com.worldstream.api.server.command;

import com.worldstream.bus.Consumer;
import com.worldstream.bus.KafkaProperties;

/**
 * @author Drem Darios
 *
 */
public class SubscribeCommand implements Command
{
    public static final String COMMAND = "subscribe";
    
    @Override
    public String getCommandName()
    {
        return COMMAND;
    }

    @Override
    public String processCommand()
    {
    	Consumer consumerThread = new Consumer(KafkaProperties.topic);
        consumerThread.start();
        // check username password against the database
        return "ok:"+ KafkaProperties.topic +";;";
    }

}

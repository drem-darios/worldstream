package com.worldstream.api.server.command;

import com.worldstream.bus.KafkaProperties;
import com.worldstream.bus.Producer;


/**
 * Clears all committed data from the database
 * @author Drem Darios
 *
 */
public class PublishCommand implements Command
{
//    private static final Logger logger =  Logger.getLogger(PublishCommand.class.getName());
    public static final String COMMAND = "publish";
    
    @Override
    public String getCommandName()
    {
        return COMMAND;
    }

    
    @Override
    public String processCommand()
    {
    	Producer producerThread = new Producer(KafkaProperties.topic);
        producerThread.start();
    	// Save some random photo or something
        return "ok:success;;";
    }

}

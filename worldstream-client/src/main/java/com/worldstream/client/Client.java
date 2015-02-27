package com.worldstream.client;


import java.util.logging.Logger;

import com.worldstream.client.actions.Action;
import com.worldstream.client.conn.Connection;
import com.worldstream.client.conn.ConnectionManager;
import com.worldstream.client.messages.Message;



/**
 * <code>MarsClient</code> can be used to send actions to the server
 * 
 * @author Drem Darios
 *
 */
public class Client
{
    private static final Logger LOG = Logger.getLogger("client");
    /**
     * The manager of the connection to the server.
     */
    private ConnectionManager manager;
    
    /**
     * Creates a new <code>MarsClient</code> with the connection provided. If
     * the connection is not open yet, this will attempt to open it
     */
    public Client(Connection connection)
    {
        manager = new ConnectionManager(connection);
    }
    
    /**
     * Handles the action that was performed by the client and give the action
     * the message the server has replied with.
     * 
     * @param action - The action who holds a message to perform on the server
     */
    public String handleAction(Action action)
    {
        Message actionMessage = action.createMessage();
        manager.sendMessage(actionMessage);

        return action.processReply(manager.getMessage());
    }
    
    /**
     * Ends the client session
     * 
     */
    public void endClient()
    {
        LOG.info("Ending client...");
        manager.closeConnection();
    }
}

package com.worldstream.client.conn;

import java.io.IOException;
import java.util.logging.Logger;

import com.worldstream.client.messages.Message;
import com.worldstream.client.messages.ServerMessage;



/**
 * Manages the connection between the client and the sever
 * @author Drem Darios
 *
 */
public class ConnectionManager
{
    private static final Logger LOG = Logger.getLogger("manager");
    /**
     * The connection between the server and client
     */
    private Connection connection;
    
    /**
     * Creates a new <code>ConnectionManager</code> to manage the connection 
     * between the server and client. If the connection is not open already, this
     * will attempt to open the connection.
     * 
     */
    public ConnectionManager(Connection connection)
    {
        this.connection = connection;
        if(!connection.isConnected())
        {
            try
            {
                connection.open();
            }
            catch (IOException e)
            {
                LOG.severe("Could not open connection.");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Sends a message to the server. To get the reply see <code>getMessage()</code>
     * 
     * @param message - The message object holding the message to send
     */
    public void sendMessage(Message message)
    {
        ClientStream outputStream = connection.getStream();
        
        try
        {
            outputStream.write(message.getMessage());
        }
        catch (IOException e)
        {
            LOG.warning("Could not send message.");
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the message from the server. If no request to the server has been
     * made, a blank message will be returned.
     * 
     * @return The latest <code>Message</code> returned from the server
     */
    public Message getMessage()
    {
        ClientStream stream = connection.getStream();
        return new ServerMessage(stream.getReply());
    }

    /**
     * Closes the connection to the server.
     */
    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch (IOException e)
        {
            LOG.severe("Could not close connection.");
            e.printStackTrace();
        }
    }
}

package org.worldstream.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.worldstream.client.conn.ClientStream;
import com.worldstream.client.conn.Connection;
import com.worldstream.client.messages.Message;
import com.worldstream.client.messages.MessageHelper;
import com.worldstream.client.messages.ServerMessage;
import com.worldstream.client.messages.strategy.DefaultReply;
import com.worldstream.client.messages.strategy.MessageStrategy;

/**
 * @author drem
 */
public class ClientIT {

	private static Connection conn = new Connection("localhost", 8080);
    private static ClientStream stream;
    
    /**
     * Creates a new connection and new client stream
     * @throws IOException
     */
    @BeforeClass
    public static void createConnection() throws IOException
    {
        conn.open();
        stream = conn.getStream();
    }
    
    /**
     * Closes the connection
     * @throws IOException
     */
    @AfterClass
    public static void closeConnection() throws IOException
    {
        conn.close();
    }
    
    /**
     * Tests a message being sent over to the server by sending a message and
     * comparing the results with the expected result
     */
    @Test
    public void testPublishMessage() throws UnknownHostException, IOException
    {
        String message = "publish;upload:hello;;";
        stream.write(message);
        String reply = stream.getReply();
        Assert.assertEquals("ok:success;;", reply);
    }
    
    @Test
    public void testSubscribeMessage() throws UnknownHostException, IOException
    {
        String message = "subscribe;stream:blah;;";
        stream.write(message);
        String reply = stream.getReply();
        Assert.assertEquals("ok:success;;", reply);
    }
    
    /**
     * Tests an error message being sent over to the server by sending a message and
     * comparing the results with the expected result
     */
    @Test
    public void testSendMessageError() throws IOException
    {
        String message = "trip;destination:mars;people:1;" +
        		"mpg:1;milesperyear:10;;";
        stream.write(message);
        String reply = stream.getReply();
        Assert.assertEquals("error:Invalid command;;", reply);
    }

    /**
     * Tests the correctness of parsing a message sent from the server
     */
    @Test
    public void testParseServerMessage()
    {
        String output = "food:143.4;weight:28.5;;";
        Message message = new ServerMessage(output);
        
        Map<String, String> resultMap = MessageHelper.getMessageElements(message);
        
        Assert.assertEquals("143.4", resultMap.get("food"));
        Assert.assertEquals("28.5", resultMap.get("weight"));
        
        Assert.assertTrue(resultMap.containsKey("food"));
        Assert.assertTrue(resultMap.containsKey("weight"));
        Assert.assertTrue(resultMap.containsValue("143.4"));
        Assert.assertTrue(resultMap.containsValue("28.5"));
        
        Assert.assertEquals(2, resultMap.size());
    }
    
    @Test
    public void testDefaultMessageStrategy()
    {
        MessageStrategy strategy = new DefaultReply();
        String output = "food:143.4;weight:28.5;;";
        Message message = new ServerMessage(output);
        
        String result = strategy.processMessage(message);
        
        Assert.assertEquals(result, output);
    }
}

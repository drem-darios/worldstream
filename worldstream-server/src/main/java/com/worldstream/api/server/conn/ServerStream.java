package com.worldstream.api.server.conn;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Drem Darios
 *
 */
public class ServerStream
{
    private static final Logger logger = Logger.getLogger(ServerStream.class.getName());
    protected OutputStreamWriter output;
    protected InputStreamReader input;
    private Socket sock;
    
    public ServerStream(Socket socket) throws IOException
    {
        this.sock = socket;
        openStream();
    }
   
    public void write(String message) throws IOException
    {
        logger.info("Writing message...");
        openStream();
        output.write(message);
        output.flush();
    }
    
    public String read() throws IOException
    {
        logger.info("Reading message...");
        int data = -1;
        StringBuffer reply = new StringBuffer();
        
        while(true)
        {
            data = input.read();
            //connection was closed
            if(data == -1)
            {
               break; 
            }
            reply.append((char)data);
            
            //reply is at least 2 chars long
            if (reply.length() > 1)
            {
                //check if this and previous char are 59 (";;")
                if(reply.codePointAt(reply.length() - 2) == ';' 
                        && data == ';')
                {
                    break;
                }
            }
        }
        
        return reply.toString();
    }
    
    /**
     * Closes Input/Output buffer streams
     * @throws IOException
     */
    public void closeStream() throws IOException
    {
        logger.info("Closing stream...");
        output.close();
        input.close();
    }
    
    /**
     * Opens Input/Output buffer streams using utf-8 encoding
     * @throws IOException
     */
    public void openStream() throws IOException
    {
        logger.info("Opening stream...");
        
        OutputStream rawOut = new BufferedOutputStream(sock.getOutputStream());
        InputStream rawIn = new BufferedInputStream(sock.getInputStream());
        
        output = new OutputStreamWriter(rawOut, "UTF-8");
        input = new InputStreamReader(rawIn, "UTF-8");
        
        output.flush();
    }
}

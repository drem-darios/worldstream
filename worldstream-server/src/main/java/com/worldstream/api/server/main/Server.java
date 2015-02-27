package com.worldstream.api.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Logger;

import com.worldstream.api.server.thread.WorkerThread;

/**
 * Server responsible for handling commands from the client. Every connection from
 * the client forks a new thread.
 * 
 * @author Drem Darios
 */
public class Server implements Runnable{
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static boolean isRunning = false;
    private static ServerSocket serverSocket;
    private int socketPort;
    public static void main(String... args) throws Exception
    {
        initProperties(args);
        Server server = new Server();
        server.run();
    }
    
    private static void initProperties(String... args) throws Exception
    {
        Properties prop = new Properties();
        
        // load properties from resources
//        prop.load(Server.class.getResourceAsStream("/resources/server.properties"));
        
//        if(args.length > 0)
//        {
//            logger.info("Loading properties from arguments.");
//            // load arguments from input
//            prop = setProperties(args, prop);
//        }
        
    }

    /**
     * 
     * @param args
     * @return
     */
    private static Properties setProperties(String[] args, Properties props)
    {
        for(String arg : args)
        {
            String[] argSplit = arg.split("=");
            props.setProperty(argSplit[0].trim(), argSplit[1].trim());
        }
        
        return props;
    }

    /**
     * Starts a server on the default port 8080
     */
    public Server()
    {
        this(8080);
    }
    
    /**
     * Starts a server on the port number provided.
     * @param port Server port number to run on
     */
    public Server(int port)
    {
        this.socketPort = port;
    }
    
    public void run()
    {           

        try {
            // Open a server socket to communicate with the client.
            serverSocket = new ServerSocket(socketPort);
            logger.info("Now accepting connections...");
            isRunning = true; // server is running
            while(isRunning())
            {
                Socket sock = serverSocket.accept(); // Accept a new socket from the server to communicate with a client.
                logger.info("New connection accepted!!");
                new Thread(new WorkerThread(sock)).start(); //run the worker thread process the commands
            }
            } catch (IOException e) {
                if(!isRunning())
                {
                    logger.severe("Error Communicating with client: " + e.getMessage());
                }
            }
            finally
            {
                if(isRunning())
                {
                    shutdown();
                }
            }
    }
    
    public boolean isRunning() 
    {
        return isRunning;
    }
            
    public void shutdown() 
    {
        isRunning = false; // set running to false
        try 
        {
            logger.info("Closing connection...");
            serverSocket.close(); //close the server if it is no longer running
            
        } catch (IOException e) {
            logger.severe("Error closing connection: " + e.getMessage());
        }
    }
    
}

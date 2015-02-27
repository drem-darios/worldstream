package com.worldstream.api.server.command;

import java.util.logging.Logger;

/**
 * Handles validating a login attempt
 * 
 * @author Drem Darios
 *
 */
public class LoginCommand implements Command
{
    private static final Logger logger = Logger.getLogger(LoginCommand.class.getName());
    public static final String COMMAND = "login";
    private String username;
//    private Password password;
    private boolean success = false;
    
    /**
     * @param username
     * @param password
     */
    public LoginCommand(String username, String password)
    {
        this.username = username;
//        this.password = new Password(password);
    }

    @Override
    public String getCommandName()
    {
        return COMMAND;
    }

    @Override
    public String processCommand()
    {
    	return "ok:success;;"; 
    }
    
    public boolean loginSuccess()
    {
        return true;
    }

}

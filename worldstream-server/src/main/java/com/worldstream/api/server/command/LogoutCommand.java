package com.worldstream.api.server.command;

/**
 * Gandles logging out the user
 * @author Drem Darios
 *
 */
public class LogoutCommand implements Command
{
    public static final String COMMAND = "logout";
    
    @Override
    public String getCommandName()
    {
        return COMMAND;
    }

    @Override
    public String processCommand()
    {
        // check username password against the database
        return "ok:success;;";
    }

}

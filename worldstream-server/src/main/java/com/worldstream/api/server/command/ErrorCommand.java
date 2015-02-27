package com.worldstream.api.server.command;

/**
 * Handles creating an error message
 * 
 * @author Drem Darios
 *
 */
public class ErrorCommand implements Command
{

    public static final String COMMAND = "error";
    private String errorMessage = "An error occurred";
    /**
     * Sets the error message
     */
    public ErrorCommand(String message)
    {
        this.errorMessage = message;
    }
    /**
     * Create a generic error message
     */
    public ErrorCommand()
    {
        
    }
    
    @Override
    public String getCommandName()
    {
        return COMMAND;
    }

    @Override
    public String processCommand()
    {
        return "error:"+errorMessage+";;";
    }

}

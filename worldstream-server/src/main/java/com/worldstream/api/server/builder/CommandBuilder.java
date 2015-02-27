package com.worldstream.api.server.builder;

import com.worldstream.api.server.command.Command;
import com.worldstream.api.server.command.Command.Commands;
import com.worldstream.api.server.command.ErrorCommand;

/**
 * 
 * @author Drem Darios
 *
 */
public class CommandBuilder extends Builder<Command>
{

    @Override
    public Command build(String objects)
    {
        String[] elements = getObjectElements(objects); // gets elements
        String[] params = new String[(elements.length - 1)]; // -1 for elements without command
        String command = getElement(0, elements); // command is first element
        for(int i = 1; i < elements.length; i++) // start at 1 to skip command
        {
            String element = getElement(i, elements);
            // error if element is null
            if(element == null)
            {
                command = ErrorCommand.COMMAND;
                params[0] = "Text cannot be blank.";
                break;
            }
            else
            {
                params[i-1] = element; // i-1 to start at zero since i starts at 1
            }
        }
        // get the command found from the enumeration
        return Commands.getCommand(command, params);
    }

}

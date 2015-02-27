package com.worldstream.api.server.command;

/**
 * Enumeration of commands available to for the server. Some commands are for 
 * debugging purposes others are for error handling. The parameters to building
 * a command are order specific!
 * 
 * @author Drem Darios
 *
 */
public interface Command
{

    public enum Commands
    {
      UPLOAD()
      {

        @Override
        public String getName()
        {
            return PublishCommand.COMMAND;
        }

        /**
         * params 0:username 1:password
         */
        @Override
        public Command createCommand(String... params)
        {
            return new PublishCommand();
        }

        @Override
        public boolean isCommand(String command)
        {
            return getName().equals(command);
        }
          
      },

      /**
       * params 
       */
      STREAM()
      {

        @Override
        public String getName()
        {
            return SubscribeCommand.COMMAND;
        }

        @Override
        public Command createCommand(String... params)
        {
            return new SubscribeCommand();
        }

        @Override
        public boolean isCommand(String command)
        {
            return getName().equals(command);
        }
          
      },

      /**
       * params 0:message
       */
      ERROR()
      {

        @Override
        public String getName()
        {
            return ErrorCommand.COMMAND;
        }

        @Override
        public Command createCommand(String... params)
        {
            return new ErrorCommand(params[0]);
        }

        @Override
        public boolean isCommand(String command)
        {
            return getName().equals(command);
        }
          
      };
      
      /**
       * Returns the command given the command name and passes the parameters
       */
      public static Command getCommand(String commandName, String... params)
      {
          for (Commands command : Commands.values())
          {
              if(command.isCommand(commandName))
              {
                  return command.createCommand(params);
              }
          }
          
          return new ErrorCommand("Invalid command");
      }
      
      public abstract String getName();
      public abstract Command createCommand(String... params);
      public abstract boolean isCommand(String command);
    };
    
    /**
     * Returns the name of the command.
     */
    public String getCommandName();
    /**
     * Performs the actions necessary for this command.
     */
    public String processCommand();
    
}

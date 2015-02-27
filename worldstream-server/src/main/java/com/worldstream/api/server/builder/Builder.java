package com.worldstream.api.server.builder;


/**
 * Generic abstract class to build an object.
 * 
 * @author Drem Darios
 * @param <T>
 *
 */
public abstract class Builder<T>
{

    /**
     * Builds an object given a string representing that object.
     * 
     */
    public abstract T build(String objects);
    

    /**
     * Returns a string array of the string passed in delimited by ";"
     * 
     */
    protected String[] getObjectElements(String object)
    {
        //replace escaped semi-colon with garbage to preserve any semi in input
        String delimObject = object.replace("\\;", "!@##@!");
        String[] objectArray = delimObject.split(";");
        String[] returnArray = new String[objectArray.length];
        
        for(int i = 0; i < objectArray.length; i++)
        {
            String obj = objectArray[i];
            returnArray[i] = obj.replace("!@##@!", ";");
        }
        
        return returnArray;
    }
    
    /**
     * Gets a string value of an element at the indicated index. Key value element
     * pairs are separated by ":".
     * @return
     */
    protected String getElement(int index, String[] elements)
    {
        /**
         * THERE IS A BUG IN HERE THAT IF A QUESTION OR ANSWER IS ADDED 
         * WITH NO TEXT IT WILL USE THE FIELD NAME TEXT AS THE VALUE.
         * THIS GOES INTO THE ELSE OF THESE TWO CLAUSES! FIX THIS
         */
        String[] field = elements[index].split(":");
        String element;
        //handle case that string had semi already
        if(field.length > 2)
        {
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i < field.length; i++)
            {
                sb.append(field[i]);
                if(i+1 != field.length)
                {
                    sb.append(":");
                }
            }
            
            element = sb.toString();
        }
        //handle case there is only key and value
        else if(field.length == 2)
        {
            element = field[1];
        }
        
        else
        {
            element = field[0];
            //No value for key
            if(elements[index].endsWith(":"))
            {
                element = null;
            }
        }
        
        
        return element;
    }
}

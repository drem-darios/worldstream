package com.worldstream.api.server.command;

import java.util.logging.Logger;

/**
 * Handles creating a video list for the client
 * 
 * @author Drem Darios
 *
 */
public class QuestionListCommand implements Command
{
    private static final Logger logger = Logger.getLogger(QuestionListCommand.class.getName());
    public static final String COMMAND = "questionList";
    private static final String CHAR_RETURN = "\r"; // sets the character return
    private String videoId;
    
    public QuestionListCommand(String videoId)
    {
        this.videoId = videoId;
    }

    @Override
    public String getCommandName()
    {
        return COMMAND;
    }

    @Override
    public String processCommand()
    {
////        StringBuilder questionList = new StringBuilder();
////        try
////        {
////            logger.info("Creating question list...");
////            String questionListQuery = DatabaseHelper.createFilteredStatement("questions", 
////                    "*", "video_id", videoId);
////            List<Map<String, String>> results = DatabaseHelper.executeQuery(
////                    questionListQuery, "question_id", "question_text", "time_asked", "date_created");
////            
////            questionList.append("ok:");
////            questionList.append(results.size());
////            questionList.append(";");
////            
////            for(int i = 0; i < results.size(); i++)
////            {
////                Map<String, String> result = results.get(i);
////                String questionId = result.get("question_id");
////                String text = result.get("question_text");
////                String timeAsked = result.get("time_asked");
////                String dateCreated = result.get("date_created");
////                
////                int answerCount = DatabaseHelper.getFilteredCount("answers", "question_id", questionId);
////                
////                String question =  createQuestion(questionId, text, timeAsked, dateCreated, answerCount);
////                questionList.append(question);
////                if(i+1 != results.size())
////                {
////                    questionList.append(CHAR_RETURN);
////                }
////            }
////            logger.info("question list created...");
////            questionList.append(";");
//        }
//        catch (SQLException e)
//        {
//            logger.severe("Question not found: " + e.getMessage());
//            return "error:No such question;;";
//        }
//        
//        return questionList.toString();
    	return "error:No such question;;";
    }

    /**
     * Creates a string representing a video based on the input.
     */
    private String createQuestion(String questionId, String text,
            String timeAsked, String dateCreated, int answerCount)
    {

        StringBuilder questionList = new StringBuilder();
        
        questionList.append("id:");
        questionList.append(questionId);
        questionList.append(";");
        
        questionList.append("text:");
        questionList.append(escapeText(text));
        questionList.append(";");
        
        questionList.append("time:");
        questionList.append(timeAsked);
        questionList.append(";");
        
        questionList.append("timestamp:");
        questionList.append(dateCreated);
        questionList.append(";");
        
        questionList.append("answers:");
        questionList.append(answerCount);
        questionList.append(";");

        return questionList.toString();
    }
    
    /**
     * Escapes any special characters
     */
    private Object escapeText(String text)
    {
        String questionString = text;
        
        questionString = questionString.replace("\\", "\\\\");
        questionString = questionString.replace(":", "\\:");
        questionString = questionString.replace(";", "\\;");
        
        return questionString;
    }

}

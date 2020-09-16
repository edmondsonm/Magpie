/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>

 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
	//Instance Variable
	String lastTopic = "";
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, what's crackalackin?.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		statement = statement.toLowerCase().trim();
		String response = "";

		//check for blank response
		if (statement.length() == 0){
			response = "Say something please!";
		}
		//no negative response
		else if (findKeyword(statement, "no") != -1)
		{
			response = "Why so negative?";
		}

		//family response
		else if (findKeyword(statement, "mother") != -1
				|| findKeyword(statement, "father") != -1
				|| findKeyword(statement, "sister") != -1
				|| findKeyword(statement, "brother") != -1)
		{
			response = "Tell me more about your family.";
		}

		//pets response
		else if (findKeyword(statement, "cat") != -1
				|| findKeyword(statement, "dog") != -1
				|| findKeyword(statement, "hamster") != -1
				|| findKeyword(statement, "fish") != -1){
			if (lastTopic.equals("pets")){
				response = "I never get tired of talking about pets.";
			}
			response = "Tell me more about your pets";
			lastTopic = "pets";
		}

		//teacher response
		else if (findKeyword(statement, "mr. a") != -1
				||findKeyword(statement, "teacher") != -1
				|| findKeyword(statement, "mr. adiletta") != -1){
			response = "He sounds like a good teacher.";
		}

		//school response
		else if (findKeyword(statement, "school") != -1){
			response = "Oh, that's nice. I don't go to school because I am a robot.";
		}

		//food response
		else if (findKeyword(statement, "food") != -1){
			response = "How interesting. I am very hungry.";
		}

		//outer space response
		else if (findKeyword(statement, "outer space") != -1){
			response = "I have always wanted to be an atronaut!";
		}
		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}

		else if (findKeyword(statement, "I want", 0) >= 0){
			response = transformIWantStatements(statement);
		}

		else
		{
			// Look for a two word (you <something> me) pattern
			if (findKeyword(statement, "you", 0) >= 0
					&& findKeyword(statement, "me", findKeyword(statement, "you", 0)) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			
			//Look for a two word (i <something> you) pattern
			else if (findKeyword(statement, "I", 0) >= 0
			&& findKeyword(statement, "you", findKeyword(statement, "I", 0)) >= 0)
				{
					response = transformIYouStatement(statement);
				}
			
			//Gets random response
			else
			{
				response = getRandomResponse();
			}
		}
		return response;
	}
	
	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()

	{
		final int NUMBER_OF_RESPONSES = 6;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}

		else if (whichResponse == 4){
			response = "You're boring!";
		}

		else if (whichResponse == 5){
			response = "You are fun to talk to!";
		}
		
		return response;
	}

	/**
 * Search for one word in phrase. The search is not case
 * sensitive. This method will check that the given goal
 * is not a substring of a longer string (so, for
 * example, "I know" does not contain "no").
 *
 * @param statement the string to search
 * @param goal the string to search for
 * @param startPos the character of the string to begin the search at
 * @return the index of the first occurrence of goal in
 *         statement or -1 if it's not found
 */
private int findKeyword(String statement, String goal,
		int startPos)
{
	String phrase = statement.trim().toLowerCase();
	goal = goal.toLowerCase();

	// The only change to incorporate the startPos is in
	// the line below
	int psn = phrase.indexOf(goal, startPos);

	// Refinement--make sure the goal isn't part of a
	// word
	while (psn >= 0)
	{
		// Find the string of length 1 before and after
		// the word
		String before = " ", after = " ";
		if (psn > 0)
		{
			before = phrase.substring(psn - 1, psn);
		}
		if (psn + goal.length() < phrase.length())
		{
			after = phrase.substring(
					psn + goal.length(),
					psn + goal.length() + 1);
		}

		// If before and after aren't letters, we've
		// found the word
		if (((before.compareTo("a") < 0) || (before
				.compareTo("z") > 0)) // before is not a
										// letter
				&& ((after.compareTo("a") < 0) || (after
						.compareTo("z") > 0)))
		{
			return psn;
		}

		// The last position didn't work, so let's find
		// the next, if there is one.
		psn = phrase.indexOf(goal, psn + 1);

	}

	return -1;
}

/**
 * Search for one word in phrase. The search is not case
 * sensitive. This method will check that the given goal
 * is not a substring of a longer string (so, for
 * example, "I know" does not contain "no"). The search
 * begins at the beginning of the string.
 * 
 * @param statement
 *            the string to search
 * @param goal
 *            the string to search for
 * @return the index of the first occurrence of goal in
 *         statement or -1 if it's not found
 */
private int findKeyword(String statement, String goal)
{
	return findKeyword(statement, goal, 0);
}

	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
		
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you be really happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatements(String statement)
	{
		//  Remove the final period, if there is one
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you be really happy if you had " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou= findKeyword (statement, "you", psnOfI + 1);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}


}

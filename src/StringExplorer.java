/**
 * A program to allow students to try out different 
 * String methods. 
 * @author Laurie White
 * @version April 2012
 */
public class StringExplorer
{
	public static void main(String[] args)
	{
		String sample = "The quick brown fox jumped over the lazy dog.";
	
		//  Demonstrate the indexOf method.
		int position = sample.indexOf("quick");
		System.out.println ("sample.indexOf(\"quick\") = " + position);
        
        int notFoundPsn = sample.indexOf("slow"); 
        System.out.println("sample.indexOf(\"slow\") = " + notFoundPsn);

		//  Demonstrate the toLowerCase method.
		String lowerCase = sample.toLowerCase();
		System.out.println ("sample.toLowerCase() = " + lowerCase);
		System.out.println ("After toLowerCase(), sample = " + sample);
				

		//  Try other methods here:

		//demonstrate the substring method
		String subguy = sample.substring(4,9);
		System.out.println(subguy);

		//demonstrate sneaky substring method
		String target = "quick";
		int startingLoc = sample.indexOf(target);
		int len = target.length();
		System.out.println(sample.substring(startingLoc, startingLoc + len));

		//substring overload method
		//same name; different parameters
		System.out.println(sample.substring(startingLoc + len));

		//break character
		System.out.println("Lalalalala\nlalalalala");

		//equals
		String one = "one";
		String two = "one";
		System.out.println(one.equals(two));

		int[] x = {1,2,3,4};
		int[] y = {1,2,3,4};
		System.out.println(x == y);

		//demonstrate indexOf method
		System.out.println(sample.indexOf("dog", 0));
		System.out.println(sample.indexOf("The", 4));
		
		//demonstrate compareTo method
		String comparedString = "The quick brown dog jumped over the lazy fox.";
		System.out.println(sample.compareTo(comparedString));

	
	}
}

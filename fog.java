import java.util.*;

import java.io.*;

//import java.io.IOException;

public class fog

{
	
	public static void main(String a[]) throws Exception 
	{
		File f = null;
		Scanner sc = null;
		String newFile = "";
		try
		{
			f = new File("temp.txt");
			sc = new Scanner(f);
		}
catch(Exception e)
		{}
String s= "";
try
{
			while(sc.hasNextLine()){
			String str = sc.next();
			s+=str+" ";
		}
	}
	catch(Exception e)
	{}
	//System.out.println(s);
	calculate(s);
}

   	
public static void calculate(String str)
{
		int length,syllable,s=0;
		double sentences=0,words=1.0,fogIndex;
		char ch;
		CountSyllables k=new CountSyllables();
		length=str.length();
		for(int i=0;i<length;i++)
		{
			 ch= str.charAt(i);
			 if (ch=='.')
			     sentences++;
			 else if (ch==' ')
			     words++;
		}
		String[] arr = str.split(" "); 
		for(int i=0;i<arr.length;i++)
		{
			    syllable=k.count(arr[i]);
			    if (syllable>=3)
				s++;
		}
		double average_words_per_sentence=words/sentences;
		fogIndex=(average_words_per_sentence+s)*0.4;
		System.out.println("Fog Index = "+fogIndex);
    } 
}    
                  
class CountSyllables 
{

    	static String[] addSyllableArray = { "ia", "riet", "dien", "iu", "io", "ii", "[aeiouym]bl$", "[aeiou]{3}", "^mc", "ism$",
            "[^aeiouy][^aeiouy]l$", "[^l]lien", "^coa[dglx].", "[^gq]ua[^auieo]", "dnt$" };
    	static String[] subtractSyllableArray = { "cial", "tia", "cius", "cious", "giu", "ion", "iou", "sia$", ".ely$" };

   	public static int count(String string) 
	{
		string = string.toLowerCase();
		string = string.replaceAll("'", " ");

		if (string.equals("i"))
		    return 1;
		if (string.equals("a"))
		    return 1;

		if (string.endsWith("e")) 
		{
		    string = string.substring(0, string.length() - 1);
        	}

		String[] phonemes = string.split("[^aeiouy]+");

		int syllableCount = 0;
		for (int i = 0; i < subtractSyllableArray.length; i++) 
		{
		    	String syllable = subtractSyllableArray[i];
		    	if (string.matches(syllable)) 
			{
		        	syllableCount--;
		    	}
		}
		for (int i = 0; i < addSyllableArray.length; i++) 
		{
		    	String syllable = addSyllableArray[i];
		   	if (string.matches(syllable)) 
			{
		        	syllableCount++;
		    	}
		}
		if (string.length() == 1) 
		{
		    	syllableCount++;
		}

		for (int i = 0; i < phonemes.length; i++) 
		{
		    	if (phonemes[i].length() > 0)
		        syllableCount++;
		}

		if (syllableCount == 0) 
		{
		    	syllableCount = 1;
		}

        	return syllableCount;
    	}

}
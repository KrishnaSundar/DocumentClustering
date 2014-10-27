package preProcessingDocumentsPipeline;

import java.io.*;
import java.util.*;

public class StopWordsRemoval 
{
	
	private static HashSet<String> m_STOP_WORDS = new HashSet<String>();
	private static File STOP_WORDS_LIST;
	private static Integer num = new Integer(0);
	
	static 
	{
		STOP_WORDS_LIST = new File( "stopwordsCollection/StopWordsList.txt" );
		try 
		{
			collectAllStopWords();
		} 
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
	}
	
	private static void collectAllStopWords() throws IOException,FileNotFoundException
	{	
		
		BufferedReader stopWordsList = new BufferedReader( new FileReader( STOP_WORDS_LIST ) );
		
		while( stopWordsList.ready() )
		{
			m_STOP_WORDS.add( stopWordsList.readLine() );
		}
		
	}
	
	private static String getProper( String word )
	{
		
		String check = "";
		
		for( int i=0;i<word.length();i++ )
		{
			if( !( word.charAt( i ) == '!' 
				|| word.charAt( i ) == '@'
				|| word.charAt( i ) == ','
				|| word.charAt( i ) == '.'
				|| word.charAt( i ) == '<'
				|| word.charAt( i ) == '>'
				|| word.charAt( i ) == '_'
				|| word.charAt( i ) == '-'
				|| word.charAt( i ) == '+'
				|| word.charAt( i ) == '='
				|| word.charAt( i ) == ')'
				|| word.charAt( i ) == '('
				|| word.charAt( i ) == '*'
				|| word.charAt( i ) == '&'
				|| word.charAt( i ) == '^'
				|| word.charAt( i ) == '%'
				|| word.charAt( i ) == '$'
				|| word.charAt( i ) == '#'
				|| word.charAt( i ) == '`'
				|| word.charAt( i ) == '~'
				|| word.charAt( i ) == '{'
				|| word.charAt( i ) == '}'
				|| word.charAt( i ) == '['
				|| word.charAt( i ) == ']'
				|| word.charAt( i ) == '|'
				|| word.charAt( i ) == '\\'
				|| word.charAt( i ) == '?'
				|| word.charAt( i ) == ':'
				|| word.charAt( i ) == ';'
				|| word.charAt( i ) == '\"'
				|| word.charAt( i ) == '\''
				|| (word.charAt( i ) >= '0'
				&& word.charAt( i ) <= '9') ))
			{
				check+=word.charAt( i );
			}
			
		}
		
		return check;
	
	}
	
	public static void removeAllStopWords( File input ) throws IOException
	{
		num++;
		BufferedReader parseIt = new BufferedReader( new FileReader( input ) );
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputs/output"+(new Integer(num).toString())+".txt")));
		
		while( parseIt.ready() )
		{
			String line = parseIt.readLine();
			
			line = line.trim().replaceAll("\\s+", " ");
			String[] checkWords = line.split(" "); 
			
			for( String word : checkWords )
			{
				word = word.toLowerCase();
				
				if( !m_STOP_WORDS.contains( word ) )
				{
					String temp = getProper( word );
					if( !temp.equals("") && !m_STOP_WORDS.contains( temp ) )
					{	
						out.println( temp );
					}
				}	
			}
		}
		
		out.close();
		
	}
	
}
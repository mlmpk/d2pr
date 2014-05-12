package de.lempikbubar.src.config;

/**
 * Statics Klasse zur Verwaltung von statischen Variablen
 * @author mlempik & dbubar
 *
 */
public class Statics {
	
	public static final int DEFAULT_PARAMETER_EXCEPTION = 1;
	public static final int FIRST_PARAMETER_NOT_VALID   = 1;
	public static final int SECOND_PARAMETER_NOT_VALID  = 2;	
	public static final int THIRD_PARAMETER_NOT_VALID   = 3;
	public static final int BLATT06_PARAMETER_EXCEPTION = 4;

	
	public static final String PARAMETER_EXCEPTION_DEFAULT_MESSAGE = "Die Syntax zum korrekten Aufruf des Programms lautet: java Sortierung Zahl [insert|merge [auf|ab|rand]]";
	public static final String FIRST_PARAMETER_EXCEPTION_MESSAGE   = "Die Syntax zum korrekten Aufruf des Programms lautet: java Sortierung Zahl [insert|merge [auf|ab|rand]]";
	public static final String SECOND_PARAMETER_EXCEPTION_MESSAGE  = "Die Syntax zum korrekten Aufruf des Programms lautet: java Sortierung Zahl [insert|merge [auf|ab|rand]]";
	public static final String THIRD_PARAMETER_EXCEPTION_MESSAGE  = "Die Syntax zum korrekten Aufruf des Programms lautet: java Sortierung Zahl [insert|merge [auf|ab|rand]]";
	
	public static final String PARAMETER_TYPE_ASC    = "auf";
	public static final String PARAMETER_TYPE_DESC   = "ab";
	public static final String PARAMETER_TYPE_RANDOM = "rand";
	
	public static final String MERGE_SORT     = "merge";
	public static final String INSERTION_SORT = "insert";
	public static final String QUICK_SORT = "quick";
	
	public static final String BLATT06_PARAMETER_EXCEPTION_MESSAGE = "Aufruf ohne Parameter! Die korrekte Syntax lautet: Anwendung [Interval/Lateness][Pfad+Dateiname]";
	
}

package archunit;

import org.junit.runner.RunWith;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;






import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture03PredifinedRules {
	

	@ArchIgnore
	@ArchTest
	public static final ArchRule RULES1_NO_ACCESS_TO_STANDARD_STREAMS = 
			NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
	
	@ArchTest
	public static final ArchRule RULES2_NO_THROW_GENERIC_EXCEPTIONS = 
			NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
	
	@ArchTest
	public static final ArchRule RULES3_NO_USE_JAVA_UTIL_LOGGING = 
			NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;


}

package archunit.hascode;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.USE_JAVA_UTIL_LOGGING;
 
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
 
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = {"com.hascode.tutorial.comp1", "com.hascode.tutorial.comp2"})
public class PredefinedRulesExampleTest {
 
  @ArchIgnore
  @ArchTest
  public static final ArchRule MUST_NOT_USE_JAVA_UTIL_LOGGING = noClasses()
      .should(USE_JAVA_UTIL_LOGGING)
      .because("slf4j and logback/log4j2 should be used instead of java.util logger");
 
}

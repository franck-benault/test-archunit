package archunit.hascode;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
 
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.hascode.tutorial", 
importOptions = ImportOption.DontIncludeTests.class
)
public class IgnoringTestClassesTest {
 
  @ArchTest
  public static ArchRule NO_TEST_CLASSES = classes().that()
      .resideInAPackage("com.hascode.tutorial..").should()
      .notHaveSimpleName("NoFooMethodCondition");
  
  	
}
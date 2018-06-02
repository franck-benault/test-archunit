package archunit.hascode;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
 
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = {"com.hascode.tutorial.comp1", "com.hascode.tutorial.comp2"})
public class CyclicDependenciesExampleTest {
 
  @ArchIgnore
  @ArchTest
  public static final ArchRule NO_CYCLIC_DEPENDENCIES =
      slices().matching("com.hascode.(tutorial).(*)").namingSlices("$2 of $1").should()
          .beFreeOfCycles();
 
}

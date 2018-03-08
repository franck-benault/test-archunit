package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;

import org.junit.Test;

public class MyArchitectureTest {
	
	@Test
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.myapp");
    
        ArchRule rule =
        		classes().should(ACCESS_STANDARD_STREAMS);
        		
       // importedClasses.that().arePublic();
    
        rule.check(importedClasses);
    }
}

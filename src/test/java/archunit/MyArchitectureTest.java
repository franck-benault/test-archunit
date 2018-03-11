package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;

import org.junit.Test;


public class MyArchitectureTest {
	
	//@Test
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.myapp");
    
        ArchRule rule =
        		classes().should(ACCESS_STANDARD_STREAMS);
        		
        rule.check(importedClasses);
    }
	
	@Test
    public void services_should_only_be_accessed_by_Controllers() {
		
		JavaClasses classes = new ClassFileImporter().importPackages("com.myapp");
		
		ArchRule myRule = classes()
		    .that().resideInAPackage("com.myapp.service")
		    .should().onlyBeAccessed().byAnyPackage("com.myapp.service","com.myapp.controller");
		 
		 myRule.check(classes);
    }
}

package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.Test;


public class MyArchitectureTest {
	

	
	@Test
    public void services_should_only_be_accessed_by_services() {
		
		JavaClasses classes = new ClassFileImporter().importPackages("com.myapp");
		
		ArchRule myRule = classes()
		    .that().resideInAPackage("..service..")
		    .should().onlyBeAccessed().byAnyPackage("..service..");
		 
		 myRule.check(classes);
    }
	
	@Test
    public void controllers_should_only_be_accessed_by_services_controllers() {
		
		JavaClasses classes = new ClassFileImporter().importPackages("com.myapp");
		
		ArchRule myRule = classes()
		    .that().resideInAPackage("..controller..")
		    .should().onlyBeAccessed().byAnyPackage("..service..","..controller..");
		 
		 myRule.check(classes);
    }
	
	@Test
    public void daos_should_only_be_accessed_by_daos_services_controllers_queries() {
		
		JavaClasses classes = new ClassFileImporter().importPackages("com.myapp");
		
		ArchRule myRule = classes()
		    .that().resideInAPackage("..dao..")
		    .should().onlyBeAccessed().byAnyPackage("..dao..","..service..","..controller..","..query..");
		 
		 myRule.check(classes);
    }
	
	@Test
    public void queries_should_only_be_accessed_by_queries_controllers() {
		
		JavaClasses classes = new ClassFileImporter().importPackages("com.myapp");
		
		ArchRule myRule = classes()
		    .that().resideInAPackage("..query..")
		    .should().onlyBeAccessed().byAnyPackage("..query..","..controller..");
		 
		 myRule.check(classes);
    }
}

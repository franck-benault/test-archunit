package archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitectureTest2WithRunner {
	
	
	@ArchTest
	public static final ArchRule services_should_only_be_accessed_by_services_rule =
		classes().that().resideInAPackage("..service..")
		.should().onlyBeAccessed().byAnyPackage("..service..");
		 
	
	@ArchTest
	public static final ArchRule controllers_should_only_be_accessed_by_services_controllers_rule =
		classes().that().resideInAPackage("..controller..")
		.should().onlyBeAccessed().byAnyPackage("..service..","..controller..");

	
	@ArchTest
	public static final ArchRule daos_should_only_be_accessed_by_daos_services_controllers_queries_rule =
		classes().that().resideInAPackage("..dao..")
		.should().onlyBeAccessed().byAnyPackage("..dao..","..service..","..controller..","..query..");

	
	@ArchTest
	public static final ArchRule queries_should_only_be_accessed_by_queries_controllers_rule =
		classes().that().resideInAPackage("..query..")
		.should().onlyBeAccessed().byAnyPackage("..query..","..controller..");

}
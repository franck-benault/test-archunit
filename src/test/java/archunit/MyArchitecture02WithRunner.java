package archunit;

import com.myapp.annotation.DataAccessObject;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture02WithRunner {
	
	
	@ArchTest
	public static final ArchRule services_should_only_be_accessed_by_services_rule =
		classes().that().resideInAPackage("..service..")
		.should().onlyBeAccessed().byAnyPackage("..service..")
		.as("services rule");
		 
	
	@ArchTest
	public static final ArchRule controllers_should_only_be_accessed_by_services_controllers_rule =
		classes().that().resideInAPackage("..controller..")
		.should().onlyBeAccessed().byAnyPackage("..service..","..controller..")
		.as("controllers rule");
	
	@ArchTest
	public static final ArchRule daos_should_only_be_accessed_by_daos_services_controllers_queries_rule =
		classes().that().resideInAPackage("..dao..")
		.should().onlyBeAccessed().byAnyPackage("..dao..","..service..","..controller..","..query..")
		.as("dao rules");


	
	@ArchTest
	public static final ArchRule queries_should_only_be_accessed_by_queries_controllers_rule =
		classes().that().resideInAPackage("..query..")
		.should().onlyBeAccessed().byAnyPackage("..query..","..controller..")
		.as("queries rules");
	
	@ArchTest
	public static final ArchRule services_should_not_access_to_queries =
		noClasses().that().resideInAPackage("..service..")
		.should().accessClassesThat().resideInAPackage("..query..")
		.because("The service layer should not access to the query layer")
		.as("services rule");
	
	@ArchTest
	public static final ArchRule classes_dao_should_only_reside_in_dao_package = 
		classes().that().haveNameMatching(".*Dao")
            .should().resideInAPackage("..dao..")
            .andShould().beAnnotatedWith(DataAccessObject.class)
            .because("The dto stereotype can only be defined in layer dao.")
            .as("dao classes rule");
	
	
	@ArchTest
	public static final ArchRule layers_are_respected = layeredArchitecture()
	            .layer("Services").definedBy("com.myapp.service")
	            .layer("Daos").definedBy("com.myapp.dao")
	            .whereLayer("Services").mayNotBeAccessedByAnyLayer();


}

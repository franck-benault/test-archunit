package archunit;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture06ClassNameConvention {
	

	
    @ArchTest
    public static ArchRule classes_named_dao_should_be_in_a_dao_package =
            classes().that().haveSimpleNameContaining("Dao")
            .should().resideInAPackage("..dao..");
    
    @ArchTest
    public static ArchRule classes_named_ended_with_Dao_should_be_in_a_dao_package =
            classes().that().haveSimpleNameEndingWith("Dao")
            .should().resideInAPackage("..dao..");
    
    @ArchTest
    public static ArchRule classes_named_start_by_Country_should_be_in_a_dao_package =
            classes().that().haveSimpleNameStartingWith("Country")
            .should().resideInAPackage("..dao..");

    @ArchTest
    public static ArchRule classes_in_dao_package_should_be_ending_with_Dao =
    classes().that().resideInAPackage("..dao..").should().haveNameMatching(".*Dao$");

}

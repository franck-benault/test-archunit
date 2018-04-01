package archunit.hascode;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = { "com.hascode.tutorial.comp1", "com.hascode.tutorial.comp2" })
public class PackageAccessExampleTest {

	@ArchTest
	public static final ArchRule COMP2_PACKAGE_MUST_NOT_ACCESS_COMP1 = noClasses().that()
			.resideInAPackage("com.hascode.tutorial.comp2").should().accessClassesThat()
			.resideInAPackage("com.hascode.tutorial.comp1").because(
					"classes in package com.hascode.tutorial.comp2 should not be accessed from classes in package com.hascode.tutorial.comp1");
}
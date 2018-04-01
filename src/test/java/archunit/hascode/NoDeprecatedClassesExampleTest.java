package archunit.hascode;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = {"com.hascode.tutorial.comp1", "com.hascode.tutorial.comp2"})
public class NoDeprecatedClassesExampleTest {

 @ArchTest
 public static final ArchRule NO_DEPRECATED_CLASSES_IN_COMP1_PACKAGE = noClasses().that()
     .areAnnotatedWith(Deprecated.class).should()
     .resideInAnyPackage("com.hascode.tutorial.comp1")
     .because("deprecated classes should not be allowed in package com.hascode.tutorial.comp1");
}
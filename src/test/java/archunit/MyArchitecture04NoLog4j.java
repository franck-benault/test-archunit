package archunit;

import org.junit.runner.RunWith;

import com.tngtech.archunit.core.domain.AccessTarget.FieldAccessTarget;
import com.tngtech.archunit.core.domain.JavaAccess.Functions.Get;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;


import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.properties.HasType.Functions.GET_TYPE;
import static com.tngtech.archunit.lang.conditions.ArchConditions.setFieldWhere;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture04NoLog4j {
	
	public static final ArchCondition<JavaClass> USE_JAVA_LOG4J_LOGGING =
			setFieldWhere(resideInAPackage("org.apache.log4j..")
					.onResultOf(Get.<JavaFieldAccess, FieldAccessTarget>target().then(GET_TYPE)))
                    .as("use org.apache.log4j");

	@ArchTest
	public static final ArchRule MUST_NOT_USE_LOG4J_LOGGING = noClasses()
		.should(USE_JAVA_LOG4J_LOGGING)
		.because("slf4j should be used instead of log4j");

}

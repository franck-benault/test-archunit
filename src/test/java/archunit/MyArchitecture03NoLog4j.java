package archunit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.core.domain.AccessTarget.FieldAccessTarget;
import com.tngtech.archunit.core.domain.JavaAccess.Functions.Get;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;
import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.AccessTarget.Predicates.constructor;
import static com.tngtech.archunit.core.domain.AccessTarget.Predicates.declaredIn;
import static com.tngtech.archunit.core.domain.JavaAccess.Predicates.originOwner;
import static com.tngtech.archunit.core.domain.JavaCall.Predicates.target;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.assignableTo;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;
import static com.tngtech.archunit.core.domain.properties.HasOwner.Predicates.With.owner;
import static com.tngtech.archunit.core.domain.properties.HasParameterTypes.Predicates.parameterTypes;
import static com.tngtech.archunit.core.domain.properties.HasType.Functions.GET_TYPE;
import static com.tngtech.archunit.lang.conditions.ArchConditions.setFieldWhere;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture03NoLog4j {
	
	public static final ArchCondition<JavaClass> USE_JAVA_UTIL_LOGGING =
            setFieldWhere(resideInAPackage("org.apache.log4j..")
                    .onResultOf(Get.<JavaFieldAccess, FieldAccessTarget>target().then(GET_TYPE)))
                    .as("use org.apache.log4j");


	@ArchTest
	public static final ArchRule MUST_NOT_USE_LOG4J_LOGGING = noClasses()
		      .should(USE_JAVA_UTIL_LOGGING)
		      .because("slf4j should be used instead of log4j");

}

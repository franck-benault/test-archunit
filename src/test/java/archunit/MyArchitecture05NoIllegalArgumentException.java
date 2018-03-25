package archunit;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.AccessTarget.Predicates.constructor;
import static com.tngtech.archunit.core.domain.AccessTarget.Predicates.declaredIn;
import static com.tngtech.archunit.core.domain.JavaCall.Predicates.target;
import static com.tngtech.archunit.lang.conditions.ArchConditions.callCodeUnitWhere;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.is;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture05NoIllegalArgumentException {
	

	
    private static final ArchCondition<JavaClass> throwIllegalArgumentExceptions =
        callCodeUnitWhere(target(is(constructor()).and(is(declaredIn(IllegalArgumentException.class)))))
        .as("throw illegalArgument exceptions");
    
	
	@ArchTest
	public static final ArchRule MUST_NOT_USE_ILLEGAL_ARGUMENT_EXCEPTION = noClasses()
		.should(throwIllegalArgumentExceptions)
		.because("slf4j should be used instead of log4j");

}

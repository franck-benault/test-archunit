package archunit;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import com.myapp.service2.a.AmountService2;
import com.myapp.service2.b.AmountService3;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.properties.HasModifiers;
import com.tngtech.archunit.core.domain.properties.HasOwner.Functions.Get;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ClassesTransformer;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static com.tngtech.archunit.core.domain.Formatters.formatLocation;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaModifier.PUBLIC;
import static com.tngtech.archunit.core.domain.properties.HasModifiers.Predicates.modifier;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp.service2")
public class MyArchitecture08MethodCheck {

	@ArchTest
	public static ArchRule all_public_methods_in_the_controller_layer_should_return_API_response_wrappers = all(
			methods()).that(areDefinedInAPackage("com.myapp.service2..")).and(arePublic())
					.should(returnType(AmountService2.class).or(returnType(AmountService3.class))).because(
							"we don't want to couple the client code directly to the return types of the encapsulated module");

	private static ClassesTransformer<JavaMethod> methods() {
		return new AbstractClassesTransformer<JavaMethod>("methods") {
			@Override
			public Iterable<JavaMethod> doTransform(JavaClasses javaClasses) {
				List<JavaMethod> methods = new ArrayList<>();
				for (JavaClass javaClass : javaClasses) {
					methods.addAll(javaClass.getMethods());
				}
				return methods;
			}
		};
	}

	private static DescribedPredicate<? super JavaMember> areDefinedInAPackage(final String packageIdentifier) {
		return Get.<JavaClass>owner().is(resideInAPackage(packageIdentifier));
	}

	private static DescribedPredicate<HasModifiers> arePublic() {
		return modifier(PUBLIC).as("are public");
	}

	private static ArchCondition<JavaMethod> returnType(final Class<?> type) {
		return new ArchCondition<JavaMethod>("return type " + type.getName()) {
			@Override
			public void check(JavaMethod method, ConditionEvents events) {
				boolean typeMatches = method.getReturnType().isAssignableTo(type);
				String message = String.format("%s returns %s in %s", method.getFullName(),
						method.getReturnType().getName(), formatLocation(method.getOwner(), 0));
				events.add(new SimpleConditionEvent(method, typeMatches, message));
			}
		};
	}
}

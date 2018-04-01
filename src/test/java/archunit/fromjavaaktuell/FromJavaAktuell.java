package archunit.fromjavaaktuell;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.runner.RunWith;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ClassesTransformer;
import com.tngtech.archunit.lang.ConditionEvents;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.hascode.tutorial")
public class FromJavaAktuell {

	@ArchTest
	public static final ArchRule customConcepts = all(businessModules()).that(dealWithOrders())
			.should(beIndependentOfPayment());

	static ClassesTransformer<BusinessModule> businessModules() {
		return new AbstractClassesTransformer<BusinessModule>("business modules") {
			@Override
			public Iterable<BusinessModule> doTransform(JavaClasses classes) {
				return Arrays.asList(new BusinessModule());
			}
		};
	}

	static DescribedPredicate<BusinessModule> dealWithOrders() {
		return new DescribedPredicate<BusinessModule>("deal with orders") {
			@Override
			public boolean apply(BusinessModule module) {
				return true;
				// return true, if a business module deals with orders
			}
		};
	}

	static ArchCondition<BusinessModule> beIndependentOfPayment() {
		return new ArchCondition<BusinessModule>("be independent of payment") {
			@Override
			public void check(BusinessModule module, ConditionEvents events) {
				// check if the actual business module is independent of payment
			}
		};
	}
}
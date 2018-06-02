package archunit;

import org.junit.runner.RunWith;

import com.myapp.service2.a.AmountService2;
import com.myapp.service2.b.AmountService3;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture07LayersAndException {
	

	@ArchIgnore
    @ArchTest
    public static final ArchRule no_cycles_by_method_calls_between_slices =
    	slices().matching("..(service2).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();
	
    @ArchTest
    public static final ArchRule no_cycles_by_method_calls_between_slices_with_exception =
    	slices().matching("..(service2).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles()
    	.ignoreDependency(AmountService2.class, AmountService3.class);
}

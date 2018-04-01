package archunit.hascode.extension;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

public class Component1PackagePredicate extends DescribedPredicate<JavaClass> {

	private static final String PACKAGE = "com.hascode.tutorial.comp1";

	public Component1PackagePredicate() {
		super("resides in package " + PACKAGE);
	}

	@Override
	public boolean apply(JavaClass input) {
		JavaClass javaClass = input;
		return javaClass.getPackage().startsWith(PACKAGE);
	}


}

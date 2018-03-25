Archunit : "If you don’t check your architecture it doesn’t really exist."

# Presentation
Archunit est an architecture test library witten in Java to specify and assert architecture rules

With Archunit, the architecture rules and the checks are implemented in Java. 

You can specify rules
* on dependencies between packages
* on dependencies between classes
* on annotations 
* on inheritances 

# Documentation
Archunit web site https://www.archunit.org/

# Source code
Github repository : https://github.com/TNG/ArchUnit

# Articles over Archunit
A lot of resources are still in German. But here is a few resources in English :

* https://blog.novatec-gmbh.de/architecture-validation-archunit-vs-stereotype-check/
* http://www.hascode.com/2017/07/assuring-architectural-rules-with-archunit/

# Alternatives
* JDepend 
  * Link: https://github.com/clarkware/jdepend 
* Stereotype-check (checkstyle plugin extension)
  * Link: https://github.com/NovaTecConsulting/stereotype-check
* QAssistant
  * Link: https://jqassistant.org/	


# Github examples
* Franck Benault : https://github.com/franck-benault/test-archunit
* Hascode https://bitbucket.org/hascode/archunit-tutorial.git

# Examples
## Write normal JUnit tests
You can write architectural rules with "normal" JUnit tests.

```java
	@Test
    public void services_should_only_be_accessed_by_services() {
		
		JavaClasses classes = new ClassFileImporter().importPackages("com.myapp");
		
		ArchRule myRule = classes()
		    .that().resideInAPackage("..service..")
		    .should().onlyBeAccessed().byAnyPackage("..service..");
		 
		 myRule.check(classes);
    }
```

## More readable tests with ArchUnitRunner
You can write more readable tests by using the ArchUnitRunner

Use the annotations on the test class 
* @RunWith(ArchUnitRunner.class)
* @AnalyzeClasses(packages = "your.packages.xxx")
* @ArchIgnore (can be use at class level or at rule level)

The rules are then defined as public attribute annotated with @ArchTest and with the type ArchRule

```java
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.myapp")
public class MyArchitecture02WithRunner {
	
	
	@ArchTest
	public static final ArchRule 		services_should_only_be_accessed_by_services_rule =
			classes().that().resideInAPackage("..service..")
			.should().onlyBeAccessed().byAnyPackage("..service..")
			.as("services rule");
}
```

The tests are easy to read and auto-documented with the usage of the following methods
* classes() / noClasses()
* shoud() / andShould() / orShould()
* because() / as()

## Define your own rules
Example create a rule to avoid the usage of log4j library (if you want for example to use slf4j as interface).

```java
	public static final ArchCondition<JavaClass> USE_JAVA_LOG4J_LOGGING =
			setFieldWhere(resideInAPackage("org.apache.log4j..")
					.onResultOf(Get.<JavaFieldAccess, FieldAccessTarget>target().then(GET_TYPE)))
                    .as("use org.apache.log4j");

	@ArchTest
	public static final ArchRule MUST_NOT_USE_LOG4J_LOGGING = noClasses()
		.should(USE_JAVA_LOG4J_LOGGING)
		.because("slf4j should be used instead of log4j");
```


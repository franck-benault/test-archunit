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

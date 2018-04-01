package archunit.hascode.extension;

import java.util.ArrayList;
import java.util.List;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
 
public class NoMethodCondition extends ArchCondition<JavaClass> {
 
  private List<String> methods;
  

  public NoMethodCondition(List<String> methodsInput) {
	  super("not contain a method named "+methodsInput);
	  methods = new ArrayList<String>();
	  for(String method : methodsInput) {
		  methods.add(method);
	  }
	  
	    
  }
 
  @Override
  public void check(JavaClass item, ConditionEvents conditionEvents) {
	  JavaClass javaClass = item;  
    javaClass.getCodeUnits().stream().filter(c -> methods.contains(c.getName()))
        .forEach(c -> conditionEvents
            .add(SimpleConditionEvent
                .violated(c, "class " + javaClass.getName() + " contains a method named foo")));
  }


}
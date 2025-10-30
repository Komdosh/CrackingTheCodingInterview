package pro.atabakov.reflection;

import java.util.Arrays;

public class ReflectionExample {
    static void main() {
        Object someProcessor = ExampleProcessorFactory.getExampleProcessor(false);

        // We don't know the class type at compile time
        Class<?> clazz = someProcessor.getClass();
        System.out.println("Class Name: " + clazz.getName());

        Arrays.stream(clazz.getMethods()).forEach(m -> {

            try {
                Class<?>[] parameterTypes = m.getParameterTypes();

                if (m.getDeclaringClass().getSimpleName().equals(clazz.getSimpleName())) {
                    System.out.println("I have a method: " + m.getName());

                    if (parameterTypes.length == 1 && parameterTypes[0].getName().equals("java.lang.String")) {
                        Object result = m.invoke(someProcessor, "Hello Reflection!");
                        // Print result (if the method returns something)
                        System.out.println("Result: " + result);
                    } else {
                        Object result = m.invoke(someProcessor);
                        System.out.println(result);
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        });
    }
}


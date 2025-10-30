package pro.atabakov.reflection;

public class ExampleProcessorFactory {
    public static ExampleProcessor getExampleProcessor(boolean isFirst) {
        if(isFirst) {
            return new ExampleProcessorFirst();
        } else {
            return new ExampleProcessorSecond();
        }
    }
}

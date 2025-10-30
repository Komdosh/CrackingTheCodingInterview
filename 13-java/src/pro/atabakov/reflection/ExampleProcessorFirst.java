package pro.atabakov.reflection;

class ExampleProcessorFirst implements ExampleProcessor {
    public String processData(String input) {
        return "Processed: " + input.toUpperCase();
    }

    @SuppressWarnings("unused")
    public String firstName(){
        return "I'm first";
    }
}
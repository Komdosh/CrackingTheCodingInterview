package pro.atabakov.reflection;

class ExampleProcessorSecond implements ExampleProcessor {
    public String processData(String input) {
        return "Processed: " + input.toUpperCase();
    }

    @SuppressWarnings("unused")
    public String secondName() {
        return "I'm second";
    }
}
package pro.atabakov.finallyblock;

public class FinallyBlock {
    @SuppressWarnings("ConstantValue")
    int someExceptionMethod() {
        if (true) {
            throw new RuntimeException("Some exception");
        }
        return 42;
    }

    int someMethod() {
        return 42;
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    int withFinallyBlock() {
        try {
            System.out.println("Executing someMethod");
            return someExceptionMethod();
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
            return someMethod();
        }
        finally {
            System.out.println("Finally executed");
            return 0;
        }
    }

    static void main() {
        FinallyBlock finallyBlock = new FinallyBlock();
        System.out.println(finallyBlock.withFinallyBlock());
    }
}

package pro.atabakov.finaldiff;

public class FinalDifference {
    @SuppressWarnings({"removal", "FinalizeCalledExplicitly"})
    final void tryToFinalize() {
        System.out.println("Call to finalize");
        try {
            this.finalize();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finalize finished");
        }
    }

    static void main() {
        FinalDifference finalDifference = new FinalDifference();
        finalDifference.tryToFinalize();
    }
}

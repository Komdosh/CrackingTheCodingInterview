package pro.atabakov.privateconstructor;

public class MyClass {
    public int num = 1;

    private MyClass() {
    }

    private MyClass(int num) {
        this.num = num;
    }

    static class Builder extends MyClass {
        public Builder(int num) {
            super(num);
        }
    }
}
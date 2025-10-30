package pro.atabakov.privateconstructor;

public class Main {
    public static void main(String[] args) {
        System.out.println(new MyClass.Builder(14).num); // 14, but MyClass by default is 1
    }
}
package three.in.one.naive;

public interface ThreeInOneStack<T> {

    T pushFirst(T item);

    T popFirst();

    T peekFirst();

    T pushSecond(T item);

    T popSecond();

    T peekSecond();

    T pushThird(T item);

    T popThird();

    T peekThird();

}

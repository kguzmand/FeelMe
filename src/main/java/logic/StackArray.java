package logic;
public class StackArray<T> {
    private static final int N = 100;
    private T[] sarray;
    private int top;

    public StackArray(int n) {
        top = 0;
        sarray = (T[]) new Object[n];
    }

    public boolean empty() {
        return top <= 0;
    }

    public boolean full() {
        return top >= sarray.length;
    }

    public T pop() {
        if (empty()) {
            throw new RuntimeException("Stack is empty");
        }
        top--;
        return sarray[top];
    }

    // void method
    public void push(T item) {
        if (full()) {
            throw new RuntimeException("Stack is full");
        }
        sarray[top] = item;
        top++;
    }
}

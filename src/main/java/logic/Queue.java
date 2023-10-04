package logic;

public class Queue<T> {
    private class Node<T> {
        private T data;
        private Node<T> next;
        public Node() {
            this(null);
        }
        public Node(T data) {
            this.data = data;
            next = null;
        }
    }
    private Node<T> front, rear;
    public Queue(){
        front = null;
        rear = null;
    }
    public void enqueue(T item) {
        Node<T> newp = new Node<>(item);
        if(rear != null){
            rear.next = newp;
        }
        else{
            front = newp;
        }
        rear = newp;
    }

    public T dequeue() {
        if (empty()) {
            throw new RuntimeException("StackRef is empty");
        }
        T item = front.data;
        front = front.next;
        if(front == null){
            rear = null;
        }
        return item;
    }
    public boolean empty() {return front == null && rear == null;}

}

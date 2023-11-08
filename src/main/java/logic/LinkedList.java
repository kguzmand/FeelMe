package logic;

public class LinkedList {
    //Esta cambiaria tentativamente por un hash
    private class Node {
        private User data;  // Campo para almacenar los datos
        private Node next; // Campo para el siguiente nodo en la lista

        // Constructor
        public Node() {
            this.data = null;
            this.next = null;
        }

        public User getData() {
            return this.data;
        }

        public Node getNext(){
            return this.next;
        }

        public void setData(User data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private Node head, ptr;

    public LinkedList() {
        head = null;
    }

    public void insert(User user) {
        Node newNode = new Node();
        newNode.setData(user);
        newNode.setNext(null);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public boolean registeredUser(String user, String password){
        ptr = head;
        while(ptr != null){
            if(user.equals(ptr.data.getNombreUsuario())){
                if(password.equals(ptr.data.getPassword())){
                    return true;
                }
            }
            ptr = ptr.getNext();
        }
        return false;
    }

    public boolean delete(User user) {
        if (head == null) {
            return false; // La lista está vacía
        }
        if (head.getData().equals(user)) {
            head = head.getNext();
            return true;
        }
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.getData().equals(user)) {
                prev.setNext(current.getNext());
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false; // No se encontró el usuario
    }

    public User searchUser(String user){
        ptr = head;
        while(true){
            if(ptr.data.getNombreUsuario().equals(user)){
                return ptr.data;
            }
            ptr = ptr.next;
        }
    }
}
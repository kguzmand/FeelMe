package logic;

public class LinkedList {
    private class Node {
        private Usuario data;  // Campo para almacenar los datos
        private Node next; // Campo para el siguiente nodo en la lista

        // Constructor
        public Node() {
            this.data = null;
            this.next = null;
        }

        public Usuario getData() {
            return this.data;
        }

        public Node getNext(){
            return this.next;
        }

        public void setData(Usuario data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private Node head;

    public LinkedList() {
        head = null;
    }

    public void insert(Usuario usuario) {
        Node newNode = new Node();
        newNode.setData(usuario);
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

    public boolean delete(Usuario usuario) {
        if (head == null) {
            return false; // La lista está vacía
        }
        if (head.getData().equals(usuario)) {
            head = head.getNext();
            return true;
        }
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.getData().equals(usuario)) {
                prev.setNext(current.getNext());
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false; // No se encontró el usuario
    }

    public void print() {
        Node current = head;
        while (current != null) {
            Usuario usuario = current.getData();
            System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
            System.out.println("Edad: " + usuario.getEdad());
            System.out.println("Correo Electrónico: " + usuario.getCorreoElectronico());
            System.out.println("Contraseña: " + usuario.getPassword());
            System.out.println("-----------------------------");
            current = current.getNext();
        }
    }
}
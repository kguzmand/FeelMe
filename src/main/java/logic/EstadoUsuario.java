package logic;

public class EstadoUsuario {
    private static class Node{
        private String data;  // Campo para almacenar los datos
        private Node next; // Campo para el siguiente nodo en la lista

        // Constructor
        public Node() {
            this.data = null;
            this.next = null;
        }

        public String getData() {
            return this.data;
        }

        public Node getNext(){
            return this.next;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private Node head, tail, ptr;
    public EstadoUsuario() {this.head = null;}
    // Función para agregar un nuevo estado al principio de la lista
    public void agregarEstado(String estado) {
        Node node = new Node();
        node.setData(estado);
        node.next = head;
        head = node;
        if(tail == null){tail = head;}
    }

    // Función para obtener el estado que está en la parte superior de la lista
    public String obtenerUltimoEstado() {
        if(head == null){
            throw new RuntimeException("Empty list");
        }
        ptr = head;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return ptr.data;
    }

    // Función para eliminar un estado de la lista
    /*public void eliminarEstado(String estado) {
        // Si el estado no está en la lista, no hacemos nada
        if (!estado.contains(estado)) {
            return;
        }

        // Obtenemos el índice del estado en la lista
        int indice = estados.indexOf(estado);

        // Eliminamos el nodo en el índice especificado
        estados.remove(indice);
    }

    // Función para obtener el estado del usuario en un índice específico
    public String obtenerEstado(int indice) {
        // Si el índice es inválido, devolvemos null
        if (indice < 0 || indice >= estados.size()) {
            return null;
        }

        // Obtenemos el nodo en el índice especificado
        Node<String> nodo = new Node<>(estados.get(indice));

        // Devolvemos el estado que está almacenado en el nodo
        return nodo.getElement();
    }

    // Función para comprobar si un estado dado se encuentra en la lista
    public boolean contieneEstado(String estado) {
        return estados.contains(estado);
    }
    //Funcion para hacer pop()
    public String eliminarPrimero() {
        // Si la lista está vacía, devolvemos null
        if (estados.isEmpty()) {
            return null;
        }

        // Obtenemos el nodo que está en la parte superior de la lista
        Node<String> primerNodo = new Node<>(estados.getFirst());

        // Eliminamos el primer nodo de la lista
        estados.removeFirst();

        // Devolvemos el estado que estaba almacenado en el nodo
        return primerNodo.getElement();
    }*/
}
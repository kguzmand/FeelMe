package logic;
import java.util.LinkedList;
import java.util.Random;

public class LinkedListEstadoUsuario {

    private LinkedList<String> estados;

    public LinkedListEstadoUsuario() {
        this.estados = new LinkedList<>();
    }

    // Función para agregar un nuevo estado al principio de la lista
    public void agregarEstado(String estado) {
        // Creamos un nuevo nodo para almacenar el estado
        Node<String> nuevoNodo = new Node<>(estado);

        // Insertamos el nuevo nodo al principio de la lista
        estados.addFirst(nuevoNodo.getElement());
    }

    // Función para obtener el estado que está en la parte superior de la lista
    public String obtenerUltimoEstado() {
        // Si la lista está vacía, devolvemos null
        if (estados.isEmpty()) {
            return null;
        }

        // Obtenemos el nodo que está en la parte superior de la lista
        Node<String> ultimoNodo = new Node<>(estados.getFirst());

        // Devolvemos el estado que está almacenado en el nodo
        return ultimoNodo.getElement();
    }


    // Función para eliminar un estado de la lista
    public void eliminarEstado(String estado) {
        // Si el estado no está en la lista, no hacemos nada
        if (!estados.contains(estado)) {
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
    }

    // Clase para representar un nodo de la lista
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
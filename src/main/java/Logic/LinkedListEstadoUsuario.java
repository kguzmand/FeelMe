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

    public static void main(String[] args) {
        /*LinkedListEstadoUsuario estadoUsuario = new LinkedListEstadoUsuario();

        estadoUsuario.agregarEstado("Feliz");
        estadoUsuario.agregarEstado("Triste");
        estadoUsuario.agregarEstado("Enojado");

        System.out.println("El último estado del usuario es: " + estadoUsuario.obtenerUltimoEstado());

        estadoUsuario.eliminarEstado("Enojado");

        System.out.println("El último estado del usuario es: " + estadoUsuario.obtenerUltimoEstado());

        System.out.println("El estado del usuario en el índice 1 es: " + estadoUsuario.obtenerEstado(1));

        System.out.println("El estado 'Feliz' está en la lista? " + estadoUsuario.contieneEstado("Feliz"));*/
        // Creamos una lista para almacenar los datos de prueba
        LinkedList<String> estados = new LinkedList<>();

        // Generamos una muestra de 1 millón de datos de prueba
        Random random = new Random();
        String[] datos = new String[10000000];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = Integer.toString(random.nextInt(10000000));
        }

        // Medimos el tiempo que tarda en agregar todos los datos de prueba
        long tiempoInicio = System.nanoTime();
        for (String estado : datos) {
            estados.addFirst(estado);
        }
        long tiempoFinal = System.nanoTime();

        // Imprimimos el tiempo de ejecución
        System.out.println("Tiempo de ejecución addFirst: " + (tiempoFinal - tiempoInicio) / 1000000000.0 + " segundos");

      // Medimos el tiempo que tarda en obtener el último estado
        long tiempoInicio2 = System.nanoTime();
        String ultimoEstado = estados.getLast();
        long tiempoFinal2 = System.nanoTime();
        // Imprimimos el tiempo de ejecución
        System.out.println("Tiempo de ejecución obtenerUltimoestado: " + (tiempoFinal2 - tiempoInicio2) / 1000000000.0 + " segundos");

        // Medimos el tiempo que tarda en obtener el estado en el índice 1
        long tiempoInicio3 = System.nanoTime();
        String estado = estados.get(3);
        long tiempoFinal3 = System.nanoTime();

        // Imprimimos el tiempo de ejecución
        System.out.println("Tiempo de ejecución obtenerestado: " + (tiempoFinal3 - tiempoInicio3) / 1000000000.0 + " segundos");

        // Create an instance of the LinkedListEstadoUsuario class
        LinkedListEstadoUsuario list = new LinkedListEstadoUsuario();

        // Add some elements to the list
        list.agregarEstado("Estado 1");
        list.agregarEstado("Estado 2");
        list.agregarEstado("Estado 3");

        // Measure the execution time of the eliminarPrimero() method
        long startTime = System.currentTimeMillis();

        list.eliminarPrimero();

        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in seconds
        double elapsedTime = (endTime - startTime);

        System.out.println("Elapsed time/ eliminar primero tarda: " + elapsedTime + " nanoseconds");
/*
        // Medimos el tiempo que tarda en eliminar un estado aleatorio
        long tiempoInicio4 = System.nanoTime();
        estados.remove(datos[random.nextInt(datos.length)]);
        long tiempoFinal4 = System.nanoTime();

        // Imprimimos el tiempo de ejecución
        System.out.println("Tiempo de ejecución eliminar: " + (tiempoFinal4 - tiempoInicio4) / 1000000000.0 + " segundos");
*/
    }
}

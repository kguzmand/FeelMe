import java.util.EmptyStackException;

public class StackEstadoUsuario {

    String arrayStack[];
    int top;

    public StackEstadoUsuario(int size) {
        arrayStack = new String[size];
        top = -1;
    }
    public void push(String estado) {
        try {
            // Verificar que el dato sea una instancia de la clase Usuario
            if (estado == null || !(estado instanceof String)) {
                throw new IllegalArgumentException("El dato debe ser una instancia de la clase Usuario");
            }

            // Insertar el dato en la pila
            arrayStack[top + 1] = estado;
            top++;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String pop() {
        String delete = null;
        if (top == -1) {
            System.out.println("STACK IS EMPTY");
        } else {
            delete = arrayStack[top];
            top--;
        }
        return delete;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arrayStack[top];
    }

    public void display() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.println(arrayStack[i]);
            }
        }
    }
    public int buscarEstado(String estado) {
        for (int i = top; i >= 0; i--) {
            if (arrayStack[i].equals(estado)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /*StackEstadoUsuario testStack = new StackEstadoUsuario(3);

        // Crear una nueva instancia de la clase Usuario
        Usuario estado1 = new Usuario("Juan", 25, "Masculino", "Triste");

        // Insertar el estado de ánimo en la pila
        testStack.push(estado1.getEstado());

        int indice = testStack.buscarEstado("Triste");

        if (indice != -1) {
            System.out.println("El estado 'triste' se encuentra en la pila en el índice " + indice);
        } else {
            System.out.println("El estado 'triste' no se encuentra en la pila");
        }
        // Mostrar el contenido de la pila
        testStack.display();

        // Eliminar el estado de ánimo de la pila
        String estadoEliminado = testStack.pop();

        // Mostrar el estado de ánimo eliminado
        System.out.println(estadoEliminado);*/


        StackEstadoUsuario stack = new StackEstadoUsuario(10000000); // Crear una pila con capacidad para 10,000 elementos
        // Medir el tiempo de ejecución de push()
        long startTime = System.nanoTime();

        for (int i = 0; i < 10000000; i++) {
            stack.push("dato" + i); // Insertar un elemento en la pila
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  // Duración en nanosegundos
        double seconds = (double)duration / 1_000_000_000.0;  // Duración en segundos
        System.out.println("Tiempo de ejecución de push: " + seconds + " segundos");//Tiempo de ejecución de push: 0.0113939 segundos
        System.out.println("Tiempo de ejecución de push: " + duration + " nanosegundos");//Tiempo de ejecución de push: 11393900 nanosegundos
        System.out.println("IS EMPTY: " + stack.isEmpty());

        /*// Medir el tiempo de ejecución de display()
        long startTime3 = System.nanoTime();

        // Llamar a la función display()
        stack.display();

        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);

        // Imprimir el tiempo de ejecución
        System.out.println("Tiempo de ejecución de display: " + duration3 + " nanosegundos");*/

        long startTime2 = System.nanoTime();

        for (int i = 0; i < 10000000; i++) {
            stack.pop(); // eliminar un elemento en la pila
        }

        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);  // Duración en nanosegundos
        double seconds2 = (double)duration2 / 1_000_000_000.0;  // Duración en segundos
        System.out.println("Tiempo de ejecución de pop: " + seconds2 + " segundos");//Tiempo de ejecución de pop: 5.077E-4 segundos
        System.out.println("Tiempo de ejecución de pop: " + duration2 + " nanosegundos");//Tiempo de ejecución de pop: 507700 nanosegundos

        System.out.println("IS EMPTY: " + stack.isEmpty());

    }
}
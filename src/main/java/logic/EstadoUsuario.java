package logic;
import java.util.EmptyStackException;

public class EstadoUsuario {

    private StackEstadoUsuario stack;

    public EstadoUsuario(int size) {
        stack = new StackEstadoUsuario(size);
    }

    public void insertarEstado(String estado) {
        // Llamar al método push() de la clase StackEstadoUsuario
        stack.push(estado);
    }

    public String eliminarUltimoEstado() {
        // Llamar al método pop() de la clase StackEstadoUsuario
        String estado = stack.pop();

        // Devolver el estado eliminado
        return estado;
    }

    public int numeroDeEstados() {
        return stack.size();
    }

    public void mostrarEstados() {
        stack.display();
    }

    public int buscarEstado(String estado) {
        return stack.buscarEstado(estado);
    }

    public String obtenerUltimoEstado() {
        if (stack.isEmpty()) {
            return null;
        } else {
            return stack.peek();
        }
    }
}

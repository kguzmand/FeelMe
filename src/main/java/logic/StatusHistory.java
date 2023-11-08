package logic;
import java.util.EmptyStackException;

public class StatusHistory {
    String arrayStack[];
    int top;

    public StatusHistory(int size) {
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
    public String obtenerUltimoEstado() {
        if (isEmpty()) {
            return null;
        } else {
            String estadoAnterior = arrayStack[top];
            return estadoAnterior;
        }
    }
}
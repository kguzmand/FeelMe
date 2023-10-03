package Logic;

import java.util.Random;

class ListaCanciones {
  private NodoCancion primero;

  // Constructor
  public ListaCanciones() {
    primero = null;
  }
  
  public NodoCancion getPrimero() {
    return primero;
}

  // Método para agregar una canción a la lista
  public void agregarCancion(Cancion cancion) {
    NodoCancion nuevoNodo = new NodoCancion(cancion);
    if (primero == null) {
      primero = nuevoNodo;
    } else {
      nuevoNodo.setSiguiente(primero);
      primero = nuevoNodo;
    }
  }

  // Método para eliminar una canción de la lista
  public void eliminarCancion(String nombre) {
    if (primero != null) {
      if (primero.getCancion().getNombre().equals(nombre)) {
        primero = primero.getSiguiente();
      } else {
        NodoCancion actual = primero;
        while (actual.getSiguiente() != null) {
          if (actual.getSiguiente().getCancion().getNombre().equals(nombre)) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            return;
          }
          actual = actual.getSiguiente();
        }
      }
    }
  }

  public Cancion buscarCancion(String nombre) {
    NodoCancion actual = primero;
    while (actual != null) {
      if (actual.getCancion().getNombre().equals(nombre)) {
        return actual.getCancion(); // Canción encontrada
      }
      actual = actual.getSiguiente();
    }
    return null; // Canción no encontrada
  }

  public Cancion seleccionarCancionAleatoria() {
    if (primero == null) {
      return null; // Retorna null si la lista está vacía
    }

    // Genera un número aleatorio entre 0 y el número de canciones en la lista
    Random rand = new Random();
    int numCanciones = contarCanciones();
    int indiceAleatorio = rand.nextInt(numCanciones);

    NodoCancion actual = primero;
    for (int i = 0; i < indiceAleatorio; i++) {
      actual = actual.getSiguiente();
    }

    return actual.getCancion();
  }

  // Método auxiliar para contar el número de canciones en la lista
  private int contarCanciones() {
    int contador = 0;
    NodoCancion actual = primero;
    while (actual != null) {
      contador++;
      actual = actual.getSiguiente();
    }
    return contador;
  }

  // Método para imprimir la lista de canciones
  public void imprimirLista() {
    NodoCancion actual = primero;
    while (actual != null) {
      System.out.println("Canción: " + actual.getCancion().getNombre());
      System.out.println("Artista: " + actual.getCancion().getArtista());
      System.out.println("Emoción: " + actual.getCancion().getEmocion());
      System.out.println("//////////////////////////");
      actual = actual.getSiguiente();
    }
  }
}

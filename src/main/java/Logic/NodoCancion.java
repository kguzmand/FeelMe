package Logic;

class NodoCancion {
  private final Cancion cancion;
  private NodoCancion siguiente;

  public NodoCancion(Cancion cancion) {
    this.cancion = cancion;
    this.siguiente = null;
  }

  // Getters y setters

  public NodoCancion getSiguiente() {
    return siguiente;
  }

  public void setSiguiente(NodoCancion siguiente) {
    this.siguiente = siguiente;
  }

  public Cancion getCancion() {
    return cancion;
  }
}

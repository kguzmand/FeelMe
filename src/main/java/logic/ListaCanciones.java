package logic;

import java.util.Random;

public class ListaCanciones {
  private NodoCancion primero, ptr;
  private String songChoice, ArtistChoice;

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

  /*public Cancion buscarCancion(String nombre) {
    NodoCancion actual = primero;
    while (actual != null) {
      if (actual.getCancion().getNombre().equals(nombre)) {
        return actual.getCancion(); // Canción encontrada
      }
      actual = actual.getSiguiente();
    }
    return null; // Canción no encontrada
  }*/

  public Cancion seleccionarCancionAleatoria(int feel) {
    String emocion = null;
    if (primero == null) {
      return null; // Retorna null si la lista está vacía
    }else{
      switch (feel){
        case 1:
          emocion = "Felicidad";
          break;
        case 2:
          emocion = "Tristeza";
          break;
        case 3:
          emocion = "Melancolia";
          break;
        case 4:
          emocion = "Enojo";
          break;
        case 5:
          emocion = "Euforia";
          break;
        case 6:
          emocion = "Amor";
          break;
        default:
          System.out.println("Opción no válida.");
          break;
      }
    }

    ListaCanciones listaEmocion = filtrarEmocion(emocion);

    // Genera un número aleatorio entre 0 y el número de canciones en la lista
    Random rand = new Random();
    int numCanciones = listaEmocion.contarCanciones();
    int indiceAleatorio = rand.nextInt(numCanciones);

    NodoCancion actual = listaEmocion.primero;
    for (int i = 0; i < indiceAleatorio; i++) {
      actual = actual.getSiguiente();
    }

    setSongChoice(actual.getCancion().getNombre());
    setArtistChoice(actual.getCancion().getArtista());

    return actual.getCancion();
  }

  public ListaCanciones filtrarEmocion(String emocion){
    ListaCanciones listaEmocion = new ListaCanciones();
    ptr = primero;
    while(ptr != null){
      if(ptr.getCancion().getEmocion().equals(emocion)){
        listaEmocion.agregarCancion(ptr.getCancion());
      }
      ptr = ptr.getSiguiente();
    }
    return listaEmocion;
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

  //Metodo para ordenar las canciones por popularidad
  public void ordenarPorPopularidad(){
    PriorityQueueClass heapPopularidad = new PriorityQueueClass();
    // Agregar todas las canciones al montículo
    NodoCancion actual = primero;
    while (actual != null) {
      heapPopularidad.insertItem(actual.getCancion());
      actual = actual.getSiguiente();
    }
    // Limpiar la lista actual
    primero = null;
    // Agregar las canciones ordenadas de nuevo a la lista
    while (heapPopularidad.size > 0) {
      agregarCancion(heapPopularidad.removeMin());
    }
  }

  public String getSongChoice() {
    return songChoice;
  }

  public void setSongChoice(String songChoice) {
    this.songChoice = songChoice;
  }

  public String getArtistChoice() {
    return ArtistChoice;
  }

  public void setArtistChoice(String artistChoice) {
    ArtistChoice = artistChoice;
  }
}

class PriorityQueueClass {
  private final int n = 1000010;
  private Cancion[] array;
  public int size;
  public PriorityQueueClass() {
    array=new Cancion[n];
    size = 0;
  }

  public void insertItem(Cancion cancion) {
    array[size] = cancion;
    moveUp();
    size++;
  }

  private void moveUp() {
    int child = size;
    int parent = (child-1)/2;
    Cancion temp = array[child];
    while(child > 0 && temp.getPopularidad() < array[parent].getPopularidad()) {
      array[child] = array[parent];
      child = parent;
      parent = (child-1)/2;
    }
    array[child]=temp;
  }

  public Cancion removeMin() {
    Cancion min=array[0];
    array[0]=array[--size];
    moveDown();
    return min;
  }

  public Cancion minimo(){
    return array[0];
  }

  private void moveDown() {
    boolean flag = false;
    Cancion smallest = null;
    int parent = 0;
    int child = (2*parent)+1;
    Cancion temp = array[parent];
    while(child < size && !flag) {
      smallest = array[child];
      if(child+1 < size && array[child+1].getPopularidad() < array[child].getPopularidad())
        smallest = array[++child];
      if(smallest.getPopularidad() < temp.getPopularidad()) {
        array[parent] = smallest;
        parent = child;
      }
      else
        flag = true;
      child = 2*parent+1;
    }
    array[parent] = temp;
  }
}

/*class AVLTree {
  static class Node {
    int key;
    Node left, right;
    int altura;

    public Node(int key) {
      this.key = key;
      left = right = null;
      altura = 1;
    }
  }

  private Node root;

  public Node getRoot() {
    return root;
  }

  public int altura(Node node) {
    if (node == null) {
      return 0;
    }
    return node.altura;
  }

  private int balanceFactor(Node node) {
    if (node == null) {
      return 0;
    }
    return altura(node.left) - altura(node.right);
  }

  private void updateAltura(Node node) {
    if (node != null) {
      node.altura = 1 + Math.max(altura(node.left), altura(node.right));
    }
  }

  private Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    updateAltura(y);
    updateAltura(x);

    return x;
  }

  private Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    updateAltura(x);
    updateAltura(y);

    return y;
  }

  public Node find(int k) {
    return find(k, root);
  }

  private Node find(int k, Node node) {
    if (node == null || node.key == k) {
      return node;
    }
    if (node.key > k) {
      return find(k, node.left);
    }
    return find(k, node.right);
  }

  private Node insert(int k) {
    root = insert(root, k);
    return root;
  }

  public static Node insert(Node node, int key) {
    if (node == null) {
      return new Node(key);
    }

    if (key < node.key) {
      node.left = insert(node.left, key);
    } else if (key > node.key) {
      node.right = insert(node.right, key);
    } else {
      return node; // No se permiten duplicados
    }

    updateAltura(node);

    int balance = balanceFactor(node);

    if (balance > 1) {
      if (key < node.left.key) {
        return rightRotate(node);
      } else {
        node.left = leftRotate(node.left);
        return rightRotate(node);
      }
    }

    if (balance < -1) {
      if (key > node.right.key) {
        return leftRotate(node);
      } else {
        node.right = rightRotate(node.right);
        return leftRotate(node);
      }
    }

    return node;
  }

  public void delete(int key) {
    root = delete(root, key);
  }

  private Node delete(Node node, int key) {
    if (node == null) {
      return node;
    }

    if (key < node.key) {
      node.left = delete(node.left, key);
    } else if (key > node.key) {
      node.right = delete(node.right, key);
    } else {
      if (node.left == null || node.right == null) {
        Node temp = (node.left != null) ? node.left : node.right;
        if (temp == null) {
          temp = node;
          node = null;
        } else {
          node = temp;
        }
      } else {
        Node temp = minValueNode(node.right);
        node.key = temp.key;
        node.right = delete(node.right, temp.key);
      }
    }

    if (node == null) {
      return node;
    }

    updateAltura(node);

    int balance = balanceFactor(node);

    if (balance > 1) {
      if (balanceFactor(node.left) >= 0) {
        return rightRotate(node);
      } else {
        node.left = leftRotate(node.left);
        return rightRotate(node);
      }
    }

    if (balance < -1) {
      if (balanceFactor(node.right) <= 0) {
        return leftRotate(node);
      } else {
        node.right = rightRotate(node.right);
        return leftRotate(node);
      }
    }

    return node;
  }

  private Node minValueNode(Node node) {
    Node current = node;
    while (current.left != null) {
      current = current.left;
    }
    return current;
  }

  public void printTree() {
    printTree(root);
  }

  private void printTree(Node node) {
    if (node != null) {
      printTree(node.left);
      System.out.print(node.key + " ");
      printTree(node.right);
    }
  }
}*/


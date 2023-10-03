/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.otroprojecttest;

/**
 *
 * @author esteb
 */
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.otroprojecttest;

/**
 *
 * @author esteb
 */
class Cancion {
  private final String nombre;
  private final String artista;
  private final String emocion;

  public Cancion(String nombre, String artista, String emocion) {
    this.nombre = nombre;
    this.artista = artista;
    this.emocion = emocion;
  }

  //Getters y Setters

  public String getNombre() {
    return nombre;
  }

  public String getArtista() {
    return artista;
  }

  public String getEmocion() {
    return emocion;
  }
}

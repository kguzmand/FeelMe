package logic;

import GUI.HelloApplication;
import GUI.PrincipalController;
import javafx.application.Application;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LinkedList userList = new LinkedList();

        System.out.println("FEEL ME");
        System.out.println("¡Bienvenido¡");
        System.out.println("1. Ingresar");
        System.out.println("2. Registrarse");
        int choice = scanner.nextInt();
        String user;

        while (true) {
            if (choice == 2) {
                System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.next();
                System.out.print("Edad: ");
                int edad = scanner.nextInt();
                System.out.print("Correo Electrónico: ");
                String correoElectronico = scanner.next();
                System.out.print("Contraseña: ");
                String newPassword = scanner.next();

                Usuario newUser = new Usuario(nombreUsuario, edad, correoElectronico, newPassword);
                userList.insert(newUser);
                System.out.println("Usuario registrado con éxito.");
                choice = 1;
            } else {
                System.out.print("Ingresa tu usuario: ");
                user = scanner.next();
                System.out.print("Ingresa tu contraseña: ");
                String password = scanner.next();
                if (userList.registeredUser(user, password)) {
                    break;
                } else {
                    System.out.println("Usuario o contraseña incorrecta. Intenta nuevamente");
                }
            }
        }

        System.out.println("Bienvenido, " + user);
        Usuario thisUser = userList.searchUser(user);

    }
}
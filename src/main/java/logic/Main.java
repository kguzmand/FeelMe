package logic;

import GUI.HelloApplication;
import GUI.PrincipalController;
import javafx.application.Application;

import java.io.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "DayanaSenpai", "1003689316")) {
                    String insertQuery = "INSERT INTO Usuarios (username, edad, email, contraseña) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, nombreUsuario);
                        preparedStatement.setInt(2, edad);
                        preparedStatement.setString(3, correoElectronico);
                        preparedStatement.setString(4, newPassword);

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Usuario registrado con éxito.");
                            choice = 1;
                        } else {
                            System.err.println("No se pudo registrar el usuario.");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
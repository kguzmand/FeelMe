package logic;
public class Usuario {
    private String nombreUsuario;
    private int edad;
    private String correoElectronico;
    private String password;
    private EstadoUsuario estados;

    public Usuario(String nombreUsuario, int edad, String correoElectronico, String password) {
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.estados = new EstadoUsuario();
    }

    // Getters y setters para los campos
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setEstado(String estado) {estados.agregarEstado(estado);}
}

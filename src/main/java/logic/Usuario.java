package logic;
public class Usuario {
    private String nombreUsuario;
    private int edad;
    private String correoElectronico;
    private String password;
    private String estado;

    public Usuario(String nombreUsuario, int edad, String correoElectronico, String password, String estado) {
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.estado = estado;
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
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
}

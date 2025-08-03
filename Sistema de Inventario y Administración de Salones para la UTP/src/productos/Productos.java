package productos;

public class Productos {

    public static Productos[] obtenerProductosIniciales() {
        return new Productos[]{
            new Productos("Proyector", 100),
            new Productos("Computadora", 100),
            new Productos("Bocinas", 100),
            new Productos("Pantalla", 100),
            new Productos("Sillas", 100),
            new Productos("Escritorio", 100),
            new Productos("Tableros", 100),};
    }
    private String nombre;
    private int cantidad;

    public Productos(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

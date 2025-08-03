package salones;

public class Salon {

    protected String nombre;
    protected int capacidad;

    // Arreglo fijo para productos asignados al salón
    protected static final int MAX_PRODUCTOS_SALON = 100;
    protected productos.Productos[] productosSalon = new productos.Productos[MAX_PRODUCTOS_SALON];
    protected int totalProductosSalon = 0;

    public Salon(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getDescripcion() {
        return nombre + " | Capacidad: " + capacidad;
    }

    // para agrega un producto al salón
    public boolean agregarProductoSalon(productos.Productos producto, int cantidad) {
        if (totalProductosSalon >= MAX_PRODUCTOS_SALON) {
            return false;
        }
        // Si ya existe el producto, solo suma la cantidad
        for (int i = 0; i < totalProductosSalon; i++) {
            if (productosSalon[i].getNombre().equals(producto.getNombre())) {
                productosSalon[i].setCantidad(productosSalon[i].getCantidad() + cantidad);
                return true;
            }
        }
        // Si es nuevo producto
        productosSalon[totalProductosSalon] = new productos.Productos(producto.getNombre(), cantidad);
        totalProductosSalon++;
        return true;
    }

    // productos asignados al salón
    public String getProductosAsignadosDescripcion() {
        if (totalProductosSalon == 0) {
            return "Sin productos asignados.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalProductosSalon; i++) {
            sb.append("- ").append(productosSalon[i].getNombre())
                    .append(": ").append(productosSalon[i].getCantidad()).append(" unidades\n");
        }
        return sb.toString();
    }

    public void mostrarInfo() {
        System.out.println("Información general del salón");
        System.out.println("Productos asignados:\n" + getProductosAsignadosDescripcion());
    }
}

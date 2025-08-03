package salones;

public class SalonDeClases extends Salon {

    public SalonDeClases(String nombre, int capacidad) {
        super("Salon de clases: " + nombre, capacidad);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Este es un salón de clases");
    }
}

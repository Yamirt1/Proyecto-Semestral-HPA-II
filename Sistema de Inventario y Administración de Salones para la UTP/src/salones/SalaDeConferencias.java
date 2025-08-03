package salones;

public class SalaDeConferencias extends Salon {
    public SalaDeConferencias(String nombre, int capacidad) {
        super("Sala de conferencias: " + nombre, capacidad);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Esta es una sala de conferencias");
    }
}

package salones;

import javax.swing.*;

public class PrincipalSalones {

    private static final int MAX_SALONES = 50;
    private static final Salon[] salones = new Salon[MAX_SALONES];
    private static int totalSalones = 0;

    public static void crearSalon() {
        String[] tipo = {"Laboratorio", "Auditorio", "Sala de conferencias", "Salon de clases"};

        String seleccion1 = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de salon", "Creacion de salon", JOptionPane.QUESTION_MESSAGE, null, tipo, tipo[0]);

        if (seleccion1 == null) {
            return;
        }
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del " + seleccion1.toLowerCase(), "Creacion de salon", JOptionPane.QUESTION_MESSAGE);
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad del " + seleccion1.toLowerCase(), "Creacion de salon", JOptionPane.QUESTION_MESSAGE));

        if (totalSalones < MAX_SALONES) {
            Salon nuevoSalon;
            switch (seleccion1) {
                case "Laboratorio":
                    nuevoSalon = new Laboratorio(nombre, capacidad);
                    break;
                case "Auditorio":
                    nuevoSalon = new Auditorio(nombre, capacidad);
                    break;
                case "Sala de conferencias":
                    nuevoSalon = new SalaDeConferencias(nombre, capacidad);
                    break;
                case "Salon de clases":
                    nuevoSalon = new SalonDeClases(nombre, capacidad);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo de salón no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            salones[totalSalones] = nuevoSalon;
            totalSalones++;
            JOptionPane.showMessageDialog(null, seleccion1 + " creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más salones.", "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void mostrarListaSalones() {
        if (totalSalones == 0) {
            JOptionPane.showMessageDialog(null, "No hay salones registrados.", "Lista de salones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Crear lista de nombres para selección
        String[] nombresSalones = new String[totalSalones];
        for (int i = 0; i < totalSalones; i++) {
            nombresSalones[i] = salones[i].getDescripcion();
        }
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un salón para ver o modificar:", "Lista de salones", JOptionPane.QUESTION_MESSAGE, null, nombresSalones, nombresSalones[0]);
        if (seleccion != null) {
            // Buscar el índice del salón seleccionado
            int indiceSeleccionado = -1;
            for (int i = 0; i < totalSalones; i++) {
                if (nombresSalones[i].equals(seleccion)) {
                    indiceSeleccionado = i;
                    break;
                }
            }
            if (indiceSeleccionado != -1) {
                // Mostrar información del salón y sus productos
                Salon salonSeleccionado = salones[indiceSeleccionado];
                StringBuilder info = new StringBuilder();
                info.append(salonSeleccionado.getDescripcion()).append("\n\n");
                info.append("Productos asignados:\n");
                info.append(salonSeleccionado.getProductosAsignadosDescripcion());
                JOptionPane.showMessageDialog(null, info.toString(), "Información del salón", JOptionPane.INFORMATION_MESSAGE);
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea modificar este salón?", "Modificar salón", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    modificarSalones(); // Aquí puedes pasar el índice o el objeto salón si lo deseas
                }
            }
        }
    }

    public static void modificarSalones() {
        Salon salon = seleccionarSalon();
        if (salon == null) return;
        modificarSalones(salon);
    }

    // para modificar un salón específico
    public static void modificarSalones(Salon salon) {
        String[] opciones = {"Modificar información del salón", "Agregar productos", "Cancelar"};
        String seleccion = (String) JOptionPane.showInputDialog(null, "¿Qué deseas modificar?", "Modificar salón", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion == null || seleccion.equals("Cancelar")) {
            return;
        }
        if (seleccion.equals("Modificar información del salón")) {
            modificarInformacionSalon(salon);
        } else if (seleccion.equals("Agregar productos")) {
            EquipamientoManager.agregarProductosASalon(salon);
            modificarSalones(salon);
            return;
        }
    }

    private static Salon seleccionarSalon() {
        if (totalSalones == 0) {
            JOptionPane.showMessageDialog(null, "No hay salones registrados.", "Modificar salón", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        String[] nombresSalones = new String[totalSalones];
        for (int i = 0; i < totalSalones; i++) {
            nombresSalones[i] = salones[i].getDescripcion();
        }
        String seleccionSalon = (String) JOptionPane.showInputDialog(null, "Seleccione el salón a modificar:", "Modificar salón", JOptionPane.QUESTION_MESSAGE, null, nombresSalones, nombresSalones[0]);
        if (seleccionSalon == null) return null;
        int indiceSeleccionado = -1;
        for (int i = 0; i < totalSalones; i++) {
            if (nombresSalones[i].equals(seleccionSalon)) {
                indiceSeleccionado = i;
                break;
            }
        }
        if (indiceSeleccionado == -1) return null;
        return salones[indiceSeleccionado];
    }

    private static void modificarInformacionSalon(Salon salon) {
        // Modificar nombre
        String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo nombre del salón:", salon.getNombre());
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            salon.nombre = nuevoNombre;
        }
        // Modificar capacidad
        String nuevaCapacidadStr = JOptionPane.showInputDialog(null, "Nueva capacidad:", salon.getCapacidad());
        if (nuevaCapacidadStr != null) {
            try {
                int nuevaCapacidad = Integer.parseInt(nuevaCapacidadStr);
                salon.capacidad = nuevaCapacidad;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Capacidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Información del salón actualizada.");
    }
}

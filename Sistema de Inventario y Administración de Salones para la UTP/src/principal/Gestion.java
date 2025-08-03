// Sistema de Inventario y Administración de Salones para la UTP 
// Desarrollado por Edwin Tuñón y Amilkar Niño
package principal;

import javax.swing.*;
import productos.*;
import salones.*;

public class Gestion {

    public void iniciarS() {
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestion de salones y asignacion de equipamiento academico");

        int respuesta = JOptionPane.showConfirmDialog(null, "Desea continuar", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            menuPrincipal();
        }
    }

    public void menuPrincipal() {
        boolean salir = false;

        while (!salir) {
            String[] opciones = {"Creación de salones", "Lista de salones", "Productos Disponibles", "Salir"};
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opcion", "Menu Principal", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case "Creación de salones":
                    creacionSalones();
                    break;
                case "Lista de salones":
                    listaSalones();
                    break;
                case "Productos Disponibles":
                    productosDisponibles();
                    break;
                case "Salir":
                    salir = true;
                    break;
            }
        }
    }

    public void creacionSalones() {
        PrincipalSalones.crearSalon();
    }

    public void listaSalones() {
        PrincipalSalones.mostrarListaSalones();
    }

    public void productosDisponibles() {
        StringBuilder mensaje = new StringBuilder("Productos disponibles en la universidad:\n\n");
        // Mostrar Aparatos tecnológicos
        mensaje.append("Aparatos tecnológicos:\n");
        for (Productos prod : CategoriasP.APARATOS) {
            mensaje.append(" - ")
                    .append(prod.getNombre())
                    .append(": ")
                    .append(prod.getCantidad())
                    .append(" unidades\n");
        }
        mensaje.append("\nMobiliario:\n");
        for (Productos prod : CategoriasP.MUEBLES) {
            mensaje.append(" - ")
                    .append(prod.getNombre())
                    .append(": ")
                    .append(prod.getCantidad())
                    .append(" unidades\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Productos Disponibles", JOptionPane.INFORMATION_MESSAGE);
    }
}

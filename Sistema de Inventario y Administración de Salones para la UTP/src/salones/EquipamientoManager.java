package salones;

import javax.swing.*;
import productos.CategoriasP;
import productos.Productos;

public class EquipamientoManager {

    public static void agregarProductosASalon(Salon salon) {
        String[] categorias = {"Aparatos tecnológicos", "Mobiliario"};
        String seleccionCategoria = (String) JOptionPane.showInputDialog(null, "Seleccione una categoría:", "Agregar productos", JOptionPane.QUESTION_MESSAGE, null, categorias, categorias[0]);
        if (seleccionCategoria == null) {
            return;
        }
        Productos[] productosCategoria;
        if (seleccionCategoria.equals("Aparatos tecnológicos")) {
            productosCategoria = CategoriasP.APARATOS;
        } else {
            productosCategoria = CategoriasP.MUEBLES;
        }
        String[] nombresProductos = new String[productosCategoria.length];
        for (int i = 0; i < productosCategoria.length; i++) {
            nombresProductos[i] = productosCategoria[i].getNombre() + " (Disponibles: " + productosCategoria[i].getCantidad() + ")";
        }
        String seleccionProducto = (String) JOptionPane.showInputDialog(null, "Seleccione el producto a agregar:", "Agregar productos", JOptionPane.QUESTION_MESSAGE, null, nombresProductos, nombresProductos[0]);
        if (seleccionProducto == null) {
            return;
        }
        int indiceProducto = -1;
        for (int i = 0; i < productosCategoria.length; i++) {
            if (seleccionProducto.startsWith(productosCategoria[i].getNombre())) {
                indiceProducto = i;
                break;
            }
        }
        if (indiceProducto == -1) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Productos productoSeleccionado = productosCategoria[indiceProducto];
        String cantidadStr = JOptionPane.showInputDialog(null, "¿Cuántas unidades desea agregar al salón? (Disponibles: " + productoSeleccionado.getCantidad() + ")");
        if (cantidadStr == null) {
            return;
        }
        int cantidadAgregar = 0;
        try {
            cantidadAgregar = Integer.parseInt(cantidadStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cantidadAgregar <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cantidadAgregar > productoSeleccionado.getCantidad()) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Asociar el producto al salón (arreglo propio)
        boolean exito = salon.agregarProductoSalon(productoSeleccionado, cantidadAgregar);
        if (!exito) {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto al salón (límite alcanzado)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Descontar del stock general
        productoSeleccionado.setCantidad(productoSeleccionado.getCantidad() - cantidadAgregar);
        JOptionPane.showMessageDialog(null, cantidadAgregar + " " + productoSeleccionado.getNombre() + "(s) agregados al salón " + salon.getNombre());
    }

    public static void descontarStockProducto() {
        String[] categorias = {"Aparatos tecnológicos", "Mobiliario"};
        String seleccionCategoria = (String) JOptionPane.showInputDialog(null, "Seleccione una categoría:", "Descontar stock", JOptionPane.QUESTION_MESSAGE, null, categorias, categorias[0]);
        if (seleccionCategoria == null) {
            return;
        }

        Productos[] productosCategoria;
        if (seleccionCategoria.equals("Aparatos tecnológicos")) {
            productosCategoria = CategoriasP.APARATOS;
        } else {
            productosCategoria = CategoriasP.MUEBLES;
        }
        // Selecciona un producto
        String[] nombresProductos = new String[productosCategoria.length];
        for (int i = 0; i < productosCategoria.length; i++) {
            nombresProductos[i] = productosCategoria[i].getNombre() + " (" + productosCategoria[i].getCantidad() + ")";
        }
        String seleccionProducto = (String) JOptionPane.showInputDialog(null, "Seleccione el producto:", "Descontar stock", JOptionPane.QUESTION_MESSAGE, null, nombresProductos, nombresProductos[0]);
        if (seleccionProducto == null) {
            return;
        }
        int indiceProducto = -1;
        for (int i = 0; i < productosCategoria.length; i++) {
            if (seleccionProducto.startsWith(productosCategoria[i].getNombre())) {
                indiceProducto = i;
                break;
            }
        }
        if (indiceProducto == -1) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Productos productoSeleccionado = productosCategoria[indiceProducto];
        String cantidadStr = JOptionPane.showInputDialog(null, "¿Cuántas unidades desea descontar? (Disponibles: " + productoSeleccionado.getCantidad() + ")");
        if (cantidadStr == null) {
            return;
        }
        int cantidadDescontar = 0;
        try {
            cantidadDescontar = Integer.parseInt(cantidadStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cantidadDescontar <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cantidadDescontar > productoSeleccionado.getCantidad()) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        productoSeleccionado.setCantidad(productoSeleccionado.getCantidad() - cantidadDescontar);
        JOptionPane.showMessageDialog(null, "Stock descontado correctamente.");
    }
}

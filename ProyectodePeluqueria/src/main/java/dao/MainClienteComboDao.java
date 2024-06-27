/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Luis
 */
import modelo.Pago;
import modelo.Servicio;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;

public class MainClienteComboDao {
    public static void main(String[] args) {
        ClienteComboDao clienteComboDao = new ClienteComboDao();

        try {
            // Obtener todos los clientes
            ArrayList<Cliente> clientes = clienteComboDao.obtenerClientes();

            // Imprimir detalles de cada cliente
            for (Cliente cliente : clientes) {
                System.out.println("ID Cliente: " + cliente.getIdCliente());
                System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
                System.out.println("Teléfono: " + cliente.getTelefono());
                System.out.println("Correo: " + cliente.getCorreo());
                System.out.println("Clave: " + cliente.getClave());
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

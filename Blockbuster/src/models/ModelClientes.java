package models;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Azaelmglw
 */

public class ModelClientes {
    private final ModelMain model_main;
    
    private String cliente_id;
    private String nombre_cliente;
    private String telefono_cliente;
    private String email_cliente;
    private String direccion_cliente;
    
    public ModelClientes(ModelMain model_main){
        this.model_main = model_main;
    }
    
    public void Actualizar_Datos(){
        model_main.setSQL_String("SELECT * FROM Clientes ORDER BY ClienteID ASC;");
        model_main.Ejecutar_Consulta();
        model_main.Mover_Primero();
        Asignar_Datos();
    }
    
    public void Asignar_Datos() {
        try{
            cliente_id = model_main.getSQL_Result_Set().getString("ClienteID");
            nombre_cliente = model_main.getSQL_Result_Set().getString("Nombre_Cliente");
            telefono_cliente = model_main.getSQL_Result_Set().getString("Teléfono_Cliente");
            email_cliente = model_main.getSQL_Result_Set().getString("Email_Cliente");
            direccion_cliente = model_main.getSQL_Result_Set().getString("Dirección_Cliente");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al asignar datos de clientes: " + e);
        }
    }
    
    public void Insertar_Cliente(){
        try{
            model_main.setSQL_String("INSERT INTO Clientes(Nombre_Cliente, Teléfono_Cliente, Email_Cliente, Dirección_Cliente) VALUES(?, ?, ?, ?);");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setString(1, nombre_cliente);
            model_main.getSQL_Prepared_Statement().setString(2, telefono_cliente);
            model_main.getSQL_Prepared_Statement().setString(3, email_cliente);
            model_main.getSQL_Prepared_Statement().setString(4, direccion_cliente);
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al insertar cliente: " + e);
        }
    }
    
    public void Modificar_Cliente(){
        try {
            model_main.setSQL_String("UPDATE Clientes SET Nombre_Cliente = ?, Teléfono_Cliente = ?, Email_Cliente = ?, Dirección_Cliente = ? WHERE ClienteID = ?;");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setString(1, nombre_cliente);
            model_main.getSQL_Prepared_Statement().setString(2, telefono_cliente);
            model_main.getSQL_Prepared_Statement().setString(3, email_cliente);
            model_main.getSQL_Prepared_Statement().setString(4, direccion_cliente);
            model_main.getSQL_Prepared_Statement().setInt(5, Integer.parseInt(cliente_id));
            model_main.Ejecutar_Actualizacion();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar cliente: " + e);
        }
    }
    
    public void Eliminar_Cliente(){
        try {
            model_main.setSQL_String("DELETE FROM Clientes WHERE ClienteID = ?;");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setInt(1, Integer.parseInt(cliente_id));
            model_main.Ejecutar_Actualizacion();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e);
        }
    }
    
    public String getCliente_ID(){
        return cliente_id;
    }
    
    public String getNombre_Cliente(){
        return nombre_cliente;
    }
    
    public String getTelefono_Cliente(){
        return telefono_cliente;
    }
    
    public String getEmail_Cliente(){
        return email_cliente;
    }
    
    public String getDireccion_Cliente(){
        return direccion_cliente;
    }
    
    public void setCliente_ID(String cliente_id){
        this.cliente_id = cliente_id;
    }
    
    public void setNombre_Cliente(String nombre_cliente){
        this.nombre_cliente = nombre_cliente;
    }
    
    public void setTelefono_Cliente(String telefono_cliente){
        this.telefono_cliente = telefono_cliente;
    }
    
    public void setEmail_Cliente(String email_cliente){
        this.email_cliente = email_cliente;
    }
    
    public void setDireccion_Cliente(String direccion_cliente){
        this.direccion_cliente = direccion_cliente;
    }
}
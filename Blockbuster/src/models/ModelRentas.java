package models;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Azaelmglw
 */

public class ModelRentas {
    private ModelMain model_main;
    
    private DefaultComboBoxModel modelo_combo_dinamico;
    private TableModel modelo_tabla_rentas;
    private int tamaño_consultas;
    private String[] contenido_combo_dinamico;
    private int[] id_clientes;
    private int[] id_peliculas;
    
    private int renta_id;
    private int cliente_id;
    private int pelicula_id;
    private String nombre_cliente;
    private String nombre_pelicula;
    private String formato_pelicula;
    private double costo_dia;
    private String dias_renta;
    private double costo_renta;
    
    
    public ModelRentas(ModelMain model_main){
        this.model_main = model_main;
    }
    
    public void Crear_Modelo_Combo_Clientes(){
        try{
            int tamaño_consultas;
            model_main.setSQL_String("SELECT COUNT(ClienteID) AS Aux FROM Clientes;");
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            tamaño_consultas = model_main.getSQL_Result_Set().getInt("Aux");
            
            model_main.setSQL_String("SELECT ClienteID AS Clave, Nombre_Cliente FROM Clientes ORDER BY ClienteID ASC;");
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            
            contenido_combo_dinamico = new String[tamaño_consultas];
            id_clientes = new int[tamaño_consultas];
            
            for(int x = 0; x < tamaño_consultas; x ++){
                id_clientes[x] = model_main.getSQL_Result_Set().getInt("Clave");
                contenido_combo_dinamico[x] = model_main.getSQL_Result_Set().getString("Nombre_Cliente");
                System.out.print("ClienteID obtenida: " + model_main.getSQL_Result_Set().getString("Clave"));
                System.out.println("Cliente obtenido: " + model_main.getSQL_Result_Set().getString("Nombre_Cliente"));
                model_main.getSQL_Result_Set().next();
            }
            modelo_combo_dinamico = new DefaultComboBoxModel(contenido_combo_dinamico);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al llenar combo de clientes: " + e);
        }
    }
    
    public void Crear_Modelo_Combo_Peliculas(){
        try{
            model_main.setSQL_String("SELECT COUNT(PelículaID) AS Aux FROM Películas;");
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            tamaño_consultas = model_main.getSQL_Result_Set().getInt("Aux");
            
            model_main.setSQL_String("SELECT PelículaID AS Clave, Nombre_Película FROM Películas ORDER BY PelículaID ASC;");
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            
            contenido_combo_dinamico = new String[tamaño_consultas];
            id_peliculas = new int[tamaño_consultas];
            
            for(int x = 0; x < tamaño_consultas; x ++){
                id_peliculas[x] = model_main.getSQL_Result_Set().getInt("Clave");
                contenido_combo_dinamico[x] = model_main.getSQL_Result_Set().getString("Nombre_Película");
                System.out.print("PelículaID obtenida: " + model_main.getSQL_Result_Set().getString("Clave"));
                System.out.println("Película obtenida: " + model_main.getSQL_Result_Set().getString("Nombre_Película"));
                model_main.getSQL_Result_Set().next();
            }
            modelo_combo_dinamico = new DefaultComboBoxModel(contenido_combo_dinamico);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al llenar combo de clientes: " + e);
        }
    }
    
    public void Obtener_Rentas(){
        model_main.setSQL_String("SELECT * FROM Rentas ORDER BY RentaID ASC;");
        model_main.Ejecutar_Consulta();
        modelo_tabla_rentas = DbUtils.resultSetToTableModel(model_main.getSQL_Result_Set());
    }
    
    public void Extraer_IDS_Tabla(int fila_seleccionada){
        try{
            renta_id = Integer.parseInt("" + modelo_tabla_rentas.getValueAt(fila_seleccionada, 0));
            cliente_id = Integer.parseInt("" + modelo_tabla_rentas.getValueAt(fila_seleccionada, 1));
            pelicula_id = Integer.parseInt("" + modelo_tabla_rentas.getValueAt(fila_seleccionada, 2));
            
            model_main.setSQL_String("CREATE OR REPLACE VIEW Im_so_sorry AS\n"
                    + "SELECT Rentas.RentaID AS RentaID, Clientes.Nombre_Cliente AS Cliente, Películas.Nombre_Película AS Película, Rentas.Formato AS Formato,\n"
                    + "Rentas.Costo_Día AS Costo_Día, Rentas.Días AS Días, Rentas.Costo_Renta AS Costo_Renta\n"
                    + "FROM Rentas\n"
                    + "INNER JOIN Clientes ON (Rentas.ClienteID = Clientes.ClienteID)\n"
                    + "INNER JOIN Películas ON (Rentas.PelículaID = Películas.PelículaID);");
            model_main.Ejecutar_Sentencia();
            
            model_main.setSQL_String("SELECT * FROM Im_so_sorry WHERE RentaID = " + renta_id + ";");
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            
            nombre_cliente = model_main.getSQL_Result_Set().getString("Cliente");
            nombre_pelicula = model_main.getSQL_Result_Set().getString("Película");
            formato_pelicula = model_main.getSQL_Result_Set().getString("Formato");
            costo_dia = model_main.getSQL_Result_Set().getDouble("Costo_Día");
            dias_renta = model_main.getSQL_Result_Set().getString("Días");
            costo_renta = model_main.getSQL_Result_Set().getDouble("Costo_Renta");
            
            System.out.println("Nombre de cliente obtenido de la vista: " + nombre_cliente);
            System.out.println("Nombre de la película obtenido de la vista: " + nombre_pelicula);
            System.out.println("Formato de la película obtenido de la vista: " + formato_pelicula);
            System.out.println("Costo por día de la película obtenida de la vista: " + costo_dia);
            System.out.println("Días de renta obtenidos de la vista: " + dias_renta);
            System.out.println("Costo total de renta obtenido de la vista: " + costo_renta);
            
            //cliente_id = Integer.parseInt("" + modelo_tabla_rentas.getValueAt(fila_seleccionada, 1));
            //pelicula_id = Integer.parseInt("" + Integer.parseInt("" + modelo_tabla_rentas.getValueAt(fila_seleccionada, 2)));
                   
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al extraer ID de la tabla rentas: " + e);
        }
    }
    
    public void Ingresar_Renta(){
        try{
            model_main.setSQL_String("SELECT Formato_Película FROM Películas WHERE PelículaID = " + id_peliculas[pelicula_id] + ";");
            //System.out.println(id_peliculas[1]);
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            System.out.println(model_main.getSQL_Result_Set().first());
            formato_pelicula = model_main.getSQL_Result_Set().getString("Formato_Película");
            
            if(formato_pelicula.equals("Blu-ray")){
                costo_dia = 15.00;
            }
            else{
                costo_dia = 10.00;
            }
      
            model_main.setSQL_String("INSERT INTO Rentas(ClienteID, PelículaID, Formato, Costo_Día, Días, Costo_Renta) VALUES(?, ?, ?, ?, ?, ?);");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setInt(1, id_clientes[cliente_id]);
            model_main.getSQL_Prepared_Statement().setInt(2, id_peliculas[pelicula_id]);
            model_main.getSQL_Prepared_Statement().setString(3, formato_pelicula);
            model_main.getSQL_Prepared_Statement().setDouble(4, costo_dia);
            model_main.getSQL_Prepared_Statement().setInt(5, Integer.parseInt(dias_renta));
            model_main.getSQL_Prepared_Statement().setDouble(6, costo_dia * Integer.valueOf(dias_renta));
            //System.out.println("Días de renta: " + dias_renta);
            //System.out.println("Costo_Renta: " + costo_dia * Integer.valueOf(dias_renta));
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al ingresar renta: " + e);
        }
    }
    
    public void Modificar_Renta(){
        try{
            model_main.setSQL_String("SELECT Formato_Película FROM Películas WHERE PelículaID = " + id_peliculas[pelicula_id] + ";");
            //System.out.println(id_peliculas[1]);
            model_main.Ejecutar_Consulta();
            model_main.getSQL_Result_Set().first();
            System.out.println(model_main.getSQL_Result_Set().first());
            formato_pelicula = model_main.getSQL_Result_Set().getString("Formato_Película");
            
            if(formato_pelicula.equals("Blu-ray")){
                costo_dia = 15.00;
            }
            else{
                costo_dia = 10.00;
            }
      
            model_main.setSQL_String("UPDATE Rentas SET ClienteID = ?, PelículaID = ?, Formato = ?, Costo_Día = ?, Días = ?, Costo_Renta = ? WHERE RentaID = ?;");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setInt(1, id_clientes[cliente_id]);
            model_main.getSQL_Prepared_Statement().setInt(2, id_peliculas[pelicula_id]);
            model_main.getSQL_Prepared_Statement().setString(3, formato_pelicula);
            model_main.getSQL_Prepared_Statement().setDouble(4, costo_dia);
            model_main.getSQL_Prepared_Statement().setInt(5, Integer.parseInt(dias_renta));
            model_main.getSQL_Prepared_Statement().setDouble(6, costo_dia * Integer.valueOf(dias_renta));
            model_main.getSQL_Prepared_Statement().setInt(7, renta_id);
            //System.out.println("Días de renta: " + dias_renta);
            //System.out.println("Costo_Renta: " + costo_dia * Integer.valueOf(dias_renta));
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al ingresar renta: " + e);
        }
    }
    
    public void Eliminar_Renta(){
        try{
            model_main.setSQL_String("DELETE FROM Rentas WHERE RentaID = ?;");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setInt(1, renta_id);
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e ){
            JOptionPane.showMessageDialog(null, "Error al eliminar renta: " + e);
        }
    }
    
    public DefaultComboBoxModel getModelo_Combo_Dinamico(){
        return modelo_combo_dinamico;
    }
    
    public TableModel getModelo_Tabla_Rentas(){
        return modelo_tabla_rentas;
    }
    
    public int getCliente_ID(){
        return cliente_id;
    }
    
    public int getPelicula_ID(){
        return pelicula_id;
    }
    
    public String getNombre_Cliente(){
        return nombre_cliente;
    }
    
    public String getNombre_Pelicula(){
        return nombre_pelicula;
    }
    
    public String getFormato_Pelicula(){
        return formato_pelicula;
    }
    
    public String getDias_Renta(){
        return dias_renta;
    }
    
    public void setRenta_ID(int renta_id){
        this.renta_id = renta_id;
    }
    
    public void setCliente_ID(int cliente_id){
        this.cliente_id = cliente_id;
    }
    
    public void setPelicula_ID(int pelicula_id){
        this.pelicula_id = pelicula_id;
    }
    
    public void setDias_Renta(String dias_renta){
        this.dias_renta = dias_renta;
    }
    
}
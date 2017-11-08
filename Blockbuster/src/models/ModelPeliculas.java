package models;

import java.sql.Time;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Azaelmglw
 */

public class ModelPeliculas {
    private final ModelMain model_main;
    
    private String pelicula_id;
    private String nombre_pelicula;
    private String formato_pelicula;
    private String duracion_pelicula;
    private String descripcion_pelicula;
    
    public ModelPeliculas(ModelMain model_main){
        this.model_main = model_main;
    }
       
    public void Actualizar_Datos(){
        model_main.setSQL_String("SELECT * FROM Películas ORDER BY PelículaID ASC;");
        model_main.Ejecutar_Consulta();
        model_main.Mover_Primero();
        Asignar_Datos();
    }
    
    public void Asignar_Datos() {
        try {
            pelicula_id = model_main.getSQL_Result_Set().getString("PelículaID");
            nombre_pelicula = model_main.getSQL_Result_Set().getString("Nombre_Película");
            formato_pelicula = model_main.getSQL_Result_Set().getString("Formato_Película");
            duracion_pelicula = model_main.getSQL_Result_Set().getString("Duración_Película");
            descripcion_pelicula = model_main.getSQL_Result_Set().getString("Descripción_Película");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al asignar datos: " + e);
        }
    }
    
    public void Insertar_Pelicula(){
        try{
            model_main.setSQL_String("INSERT INTO Películas(Nombre_Película, Formato_Película, Duración_Película, Descripción_Película) VALUES(?, ?, ?, ?);");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setString(1, nombre_pelicula);
            model_main.getSQL_Prepared_Statement().setString(2, formato_pelicula);
            model_main.getSQL_Prepared_Statement().setTime(3, Time.valueOf(duracion_pelicula));
            model_main.getSQL_Prepared_Statement().setString(4, descripcion_pelicula);
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al insertar registro: " + e);
        }
    }
    
    public void Modificar_Pelicula(){
        try{
            model_main.setSQL_String("UPDATE Películas SET Nombre_Película = ?, Formato_Película = ?, Duración_Película = ?, Descripción_Película = ? WHERE PelículaID = ?;");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setString(1, nombre_pelicula);
            model_main.getSQL_Prepared_Statement().setString(2, formato_pelicula);
            model_main.getSQL_Prepared_Statement().setTime(3, Time.valueOf(duracion_pelicula));
            model_main.getSQL_Prepared_Statement().setString(4, descripcion_pelicula);
            model_main.getSQL_Prepared_Statement().setInt(5, Integer.parseInt(pelicula_id));
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar registro: " + e);
        }
    }
    
    public void Eliminar_Pelicula(){
        try{
            model_main.setSQL_String("DELETE FROM Películas WHERE PelículaID = ? ;");
            model_main.Preparar_Statement();
            model_main.getSQL_Prepared_Statement().setInt(1, Integer.parseInt(pelicula_id));
            model_main.Ejecutar_Actualizacion();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar registro: " + e);
        }
    }
    
    public String getPelicula_ID(){
        return pelicula_id;
    }
    
    public String getNombre_Pelicula(){
        return nombre_pelicula;
    }
    
    public String getFormato_Pelicula(){
        return formato_pelicula;
    }
    
    public String getDuracion_Pelicula(){
        return duracion_pelicula;
    }
    
    public String getDescripcion_Pelicula(){
        return descripcion_pelicula;
    }
    
    public void setPelicula_ID(String pelicula_id){
        this.pelicula_id = pelicula_id;
    }
    
    public void setNombre_Pelicula(String nombre_pelicula){
        this.nombre_pelicula= nombre_pelicula;
    }
    
    public void setFormato_Pelicula(String formato_pelicula){
        this.formato_pelicula = formato_pelicula;
    }
    
    public void setDuracion_Pelicula(String duracion_pelicula){
        this.duracion_pelicula = duracion_pelicula;
    }
    
    public void setDescripcion_Pelicula(String descripcion_pelicula){
        this.descripcion_pelicula = descripcion_pelicula;
    }
}

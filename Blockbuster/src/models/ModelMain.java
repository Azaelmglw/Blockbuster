package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Azaelmglw
 */

public class ModelMain {
    private Connection sql_connection;
    private Statement sql_statement;
    private PreparedStatement sql_prepared_statement;
    private ResultSet sql_result_set;
    private String  sql;
    
    public void Connect(){
        try{
            Class.forName("org.postgresql.Driver");
            sql_connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blockbuster", "postgres", "");
            sql_statement = sql_connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
        catch(ClassNotFoundException f){
            JOptionPane.showMessageDialog(null,":(");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e);
        }
    }
    
    public void Ejecutar_Consulta(){
        try{
            Connect();
            sql_result_set = sql_statement.executeQuery(sql);
            sql_connection.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " +  e);
        }
    }
    
    public void Ejecutar_Actualizacion(){
        try{
            sql_prepared_statement.executeUpdate();
            sql_connection.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al ejecutar actualización: " + e);
        }
    }
    
    public void Preparar_Statement(){
        try{
            Connect();
            sql_prepared_statement = sql_connection.prepareStatement(sql);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al preparar statement: " + e);
        }
    }
    
    public void Mover_Primero(){
        try{
            if(sql_result_set.isFirst() == false){
                sql_result_set.first();
            }
            else{
                //
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mover el cursor al primer registro:" + e);
        }
    }
    
    public void Mover_Anterior(){
        try{
            if(sql_result_set.isFirst() == false){
                sql_result_set.previous();
            }
            else{
                //
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mover el cursor al registro anterior: " + e);
        }
    }
    
    public void Mover_Siguiente(){
        try{
            if(sql_result_set.isLast() == false){
                sql_result_set.next();
            }
            else{
                //
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mover el cursor al registro siguiente: " + e);
        }
    }
    
    public void Mover_Ultimo(){
        try{
            if(sql_result_set.isLast() == false){
                sql_result_set.last();
            }
            else{
                //
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mover el cursor al último registro: " + e);
        }
    }
    
    public Connection getSQL_Connector(){
        return sql_connection;
    }
    
    public PreparedStatement getSQL_Prepared_Statement(){
        return sql_prepared_statement;
    }
    
    public ResultSet getSQL_Result_Set(){
        return sql_result_set;
    }
    
    public String getSQL_String(){
        return sql;
    }
    
    public void setSQL_String(String sql){
        this.sql = sql;
    }
}
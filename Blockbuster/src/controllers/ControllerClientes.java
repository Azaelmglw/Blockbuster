package controllers;

import models.ModelMain;
import models.ModelClientes;
import views.ViewClientes;

/**
 *
 * @author Azaelmglw
 */

public class ControllerClientes {
    private final ModelMain model_main;
    private final ModelClientes model_clientes;
    private final ViewClientes view_clientes;
    
    public ControllerClientes(Object models[], Object views[], Object controllers[]){
        this.model_main = (ModelMain)models[0];
        this.model_clientes = (ModelClientes)models[2];
        this.view_clientes = (ViewClientes)views[2];
        initView();
    }
    
    public void initView(){
        view_clientes.jbtn_primero.addActionListener(e -> jbtn_primeroMouseClicked());
        view_clientes.jbtn_anterior.addActionListener(e -> jbtn_anteriorMouseClicked());
        view_clientes.jbtn_siguiente.addActionListener(e -> jbtn_siguienteMouseClicked());
        view_clientes.jbtn_ultimo.addActionListener(e -> jbtn_ultimoMouseClicked());
        view_clientes.jbtn_almacenar.addActionListener(e -> jbtn_almacenarMouseClicked());
        view_clientes.jbtn_modificar.addActionListener(e -> jbtn_modificarMouseClicked());
        view_clientes.jbtn_eliminar.addActionListener(e -> jbtn_eliminarMouseClicked());
        view_clientes.jbtn_nuevo.addActionListener(e -> jbtn_nuevoMouseClicked());
        view_clientes.jtf_id.setEnabled(false);
    }
    
    public void getDatos(){
        view_clientes.jtf_id.setText(model_clientes.getCliente_ID());
        view_clientes.jtf_nombre.setText(model_clientes.getNombre_Cliente());
        view_clientes.jtf_telefono.setText(model_clientes.getTelefono_Cliente());
        view_clientes.jtf_email.setText(model_clientes.getEmail_Cliente());
        view_clientes.jtf_direccion.setText(model_clientes.getDireccion_Cliente());
    }
    
    public void setDatos(){
        model_clientes.setCliente_ID(view_clientes.jtf_id.getText());
        model_clientes.setNombre_Cliente(view_clientes.jtf_nombre.getText());
        model_clientes.setTelefono_Cliente(view_clientes.jtf_telefono.getText());
        model_clientes.setEmail_Cliente(view_clientes.jtf_email.getText());
        model_clientes.setDireccion_Cliente(view_clientes.jtf_direccion.getText());
    }
    
    public void Actualizar_Interfaz() {
        model_clientes.Actualizar_Datos();
        getDatos();
    }
    
    public void jbtn_primeroMouseClicked(){
        model_main.Mover_Primero();
        model_clientes.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_anteriorMouseClicked(){
        model_main.Mover_Anterior();
        model_clientes.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_siguienteMouseClicked(){
        model_main.Mover_Siguiente();
        model_clientes.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_ultimoMouseClicked(){
        model_main.Mover_Ultimo();
        model_clientes.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_almacenarMouseClicked(){
        setDatos();
        model_clientes.Insertar_Cliente();
        Actualizar_Interfaz();
    }
    
    public void jbtn_modificarMouseClicked(){
        setDatos();
        model_clientes.Modificar_Cliente();
        Actualizar_Interfaz();
    }
    
    public void jbtn_eliminarMouseClicked(){
        model_clientes.setCliente_ID(view_clientes.jtf_id.getText());
        model_clientes.Eliminar_Cliente();
        Actualizar_Interfaz();
    }
    
    public void jbtn_nuevoMouseClicked(){
        view_clientes.jtf_id.setText("");
        view_clientes.jtf_nombre.setText("");
        view_clientes.jtf_telefono.setText("");
        view_clientes.jtf_email.setText("");
        view_clientes.jtf_direccion.setText("");
    }
    
}

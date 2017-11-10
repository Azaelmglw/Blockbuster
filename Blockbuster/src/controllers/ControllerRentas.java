package controllers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import models.ModelRentas;
import views.ViewRentas;

/**
 *
 * @author Azaelmglw
 */

public class ControllerRentas {
    private final ModelRentas model_rentas;
    private final ViewRentas view_rentas;
    
    public ControllerRentas(Object models[], Object views[], Object controllers[]){
        this.model_rentas = (ModelRentas)models[3];
        this.view_rentas = (ViewRentas)views[3];
        initView();
    }
    
    public void initView(){
        Configurar_Plantilla();
        Definir_Action_Listeners();
    }
    
    public void Configurar_Plantilla(){
        
    }
    
    public void Definir_Action_Listeners(){
        FocusListener foco_ventana = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Actualizar_Interfaz();
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        };
        FocusListener foco_tabla = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                model_rentas.Extraer_IDS_Tabla(view_rentas.jtb_tabla_rentas.getSelectedRow());
                view_rentas.jcb_cliente.setSelectedItem(model_rentas.getNombre_Cliente());
                view_rentas.jcb_pelicula.setSelectedItem(model_rentas.getNombre_Pelicula());
                view_rentas.jspn_dias.setValue(Integer.parseInt(model_rentas.getDias_Renta()));
                view_rentas.jtb_tabla_rentas.transferFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        };
                
        view_rentas.addFocusListener(foco_ventana);
        view_rentas.jtb_tabla_rentas.addFocusListener(foco_tabla);
        view_rentas.jbtn_modificar.addActionListener(e -> jbtn_modificarMouseClicked());
        view_rentas.jbtn_eliminar.addActionListener(e -> jbtn_eliminarMouseClicked());
        view_rentas.jbtn_almacenar.addActionListener(e -> jbtn_almacenarMouseClicked());
    }
    
    public void Actualizar_Interfaz(){
        model_rentas.Crear_Modelo_Combo_Clientes();
        view_rentas.jcb_cliente.setModel(model_rentas.getModelo_Combo_Dinamico());
        model_rentas.Crear_Modelo_Combo_Peliculas();
        view_rentas.jcb_pelicula.setModel(model_rentas.getModelo_Combo_Dinamico());
        view_rentas.jspn_dias.setValue(0);
        model_rentas.Obtener_Rentas();
        view_rentas.jtb_tabla_rentas.setModel(model_rentas.getModelo_Tabla_Rentas());
    }
    
    public void jbtn_modificarMouseClicked(){
        model_rentas.setCliente_ID(view_rentas.jcb_cliente.getSelectedIndex());
        model_rentas.setPelicula_ID(view_rentas.jcb_pelicula.getSelectedIndex());
        model_rentas.setDias_Renta("" + view_rentas.jspn_dias.getValue());
        model_rentas.Modificar_Renta();
        Actualizar_Interfaz();
    }
    
    public void jbtn_eliminarMouseClicked(){
        model_rentas.Extraer_IDS_Tabla(view_rentas.jtb_tabla_rentas.getSelectedRow());
        model_rentas.Eliminar_Renta();
        Actualizar_Interfaz();
    }
    
    public void jbtn_almacenarMouseClicked(){
        model_rentas.setCliente_ID(view_rentas.jcb_cliente.getSelectedIndex());
        model_rentas.setPelicula_ID(view_rentas.jcb_pelicula.getSelectedIndex());
        model_rentas.setDias_Renta("" + view_rentas.jspn_dias.getValue());
        model_rentas.Ingresar_Renta();
        Actualizar_Interfaz();
    }
    
    
}

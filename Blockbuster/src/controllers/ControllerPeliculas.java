package controllers;

import models.ModelMain;
import models.ModelPeliculas;
import views.ViewPeliculas;

/**
 *
 * @author Azaelmglw
 */

public class ControllerPeliculas {
    private final ModelMain model_main;
    private final ModelPeliculas model_peliculas;
    private final ViewPeliculas view_peliculas;
    
    public ControllerPeliculas(Object models[], Object views[], Object controllers[]){
        this.model_main = (ModelMain)models[0];
        this.model_peliculas = (ModelPeliculas)models[1];
        this.view_peliculas = (ViewPeliculas)views[1];
        initView();
    }
    
    public void initView(){
        view_peliculas.jbtn_primero.addActionListener(e -> jbtn_primeroMouseClicked());
        view_peliculas.jbtn_anterior.addActionListener(e -> jbtn_anteriorMouseClicked());
        view_peliculas.jbtn_siguiente.addActionListener(e -> jbtn_siguienteMouseClicked());
        view_peliculas.jbtn_ultimo.addActionListener(e -> jbtn_ultimoMouseClicked());
        view_peliculas.jbtn_almacenar.addActionListener(e -> jbtn_almacenarMouseClicked());
        view_peliculas.jbtn_modificar.addActionListener(e -> jbtn_modificarMouseClicked());
        view_peliculas.jbtn_eliminar.addActionListener(e -> jbtn_eliminarMouseClicked());
        view_peliculas.jbtn_nuevo.addActionListener(e -> jbtn_nuevoMouseClicked());
        view_peliculas.jtf_id.setEnabled(false);
    }
    
    public void getDatos(){
        view_peliculas.jtf_id.setText(model_peliculas.getPelicula_ID());
        view_peliculas.jtf_nombre.setText(model_peliculas.getNombre_Pelicula());
        view_peliculas.jcb_formato.setSelectedItem(model_peliculas.getFormato_Pelicula());
        view_peliculas.jtf_duracion.setText(model_peliculas.getDuracion_Pelicula());
        view_peliculas.jta_descripcion.setText(model_peliculas.getDescripcion_Pelicula());
    }
    
    public void setDatos(){
        model_peliculas.setPelicula_ID(view_peliculas.jtf_id.getText());
        model_peliculas.setNombre_Pelicula(view_peliculas.jtf_nombre.getText());
        model_peliculas.setFormato_Pelicula("" + view_peliculas.jcb_formato.getSelectedItem());
        model_peliculas.setDuracion_Pelicula(view_peliculas.jtf_duracion.getText());
        model_peliculas.setDescripcion_Pelicula(view_peliculas.jta_descripcion.getText());
    }
    
    public void Actualizar_Interfaz(){
        model_peliculas.Actualizar_Datos();
        getDatos();
    }
    
    public void jbtn_primeroMouseClicked(){
        model_main.Mover_Primero();
        model_peliculas.Asignar_Datos();
        getDatos();
        
    }
    
    public void jbtn_anteriorMouseClicked(){
        model_main.Mover_Anterior();
        model_peliculas.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_siguienteMouseClicked(){
        model_main.Mover_Siguiente();
        model_peliculas.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_ultimoMouseClicked(){
        model_main.Mover_Ultimo();
        model_peliculas.Asignar_Datos();
        getDatos();
    }
    
    public void jbtn_almacenarMouseClicked(){
        setDatos();
        model_peliculas.Insertar_Pelicula();
        Actualizar_Interfaz();
    }
    
    public void jbtn_modificarMouseClicked(){
        setDatos();
        model_peliculas.Modificar_Pelicula();
        Actualizar_Interfaz();
    }
    
    public void jbtn_eliminarMouseClicked(){
        model_peliculas.setPelicula_ID(view_peliculas.jtf_id.getText());
        model_peliculas.Eliminar_Pelicula();
        Actualizar_Interfaz();
    }
    
    public void jbtn_nuevoMouseClicked(){
        view_peliculas.jtf_id.setText("");
        view_peliculas.jtf_nombre.setText("");
        view_peliculas.jcb_formato.setSelectedItem("DVD");
        view_peliculas.jtf_duracion.setText("");
        view_peliculas.jta_descripcion.setText("");
    }
    
}

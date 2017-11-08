package controllers;

import models.*;
import views.*;

/**
 *
 * @author Azaelmglw
 */

public class ControllerMain {
    private final ModelPeliculas model_peliculas;
    private final ModelClientes model_clientes;
    private final ViewMain view_main;
    private final ViewPeliculas view_peliculas;
    private final ViewClientes view_clientes;
    private final ControllerClientes controller_clientes;
    private final ControllerPeliculas controller_peliculas;
    
    public ControllerMain(Object models[], Object views[], Object controllers[]){
        this.model_peliculas = (ModelPeliculas)models[1];
        this.model_clientes = (ModelClientes)models[2];
        this.view_main = (ViewMain)views[0];
        this.view_peliculas = (ViewPeliculas)views[1];
        this.view_clientes = (ViewClientes)views[2];
        this.controller_clientes = (ControllerClientes)controllers[0];
        this.controller_peliculas = (ControllerPeliculas)controllers[1];
        initView();
    }
    
    public void initView(){
        view_main.setTitle("Blockbuster");
        view_main.setLocationRelativeTo(null);
        view_main.setVisible(true);
        view_main.jmi_peliculas.addActionListener(e ->jmi_peliculasMouseClicked());
        view_main.jmi_clientes.addActionListener(e -> jmi_clientesMouseClicked());
    }
    
    public void jmi_peliculasMouseClicked(){
        view_main.setContentPane(view_peliculas);
        view_main.revalidate();
        view_main.repaint();
        controller_peliculas.Actualizar_Interfaz();
    }
    
    public void jmi_clientesMouseClicked(){
        view_main.setContentPane(view_clientes);
        view_main.revalidate();
        view_main.repaint();
        controller_clientes.Actualizar_Interfaz();
    }
}
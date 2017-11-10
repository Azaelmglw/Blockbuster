package main;

import models.*;
import views.*;
import controllers.*;

/**
 *
 * @author Azaelmglw
 */

public class Main {
    
    public static void main (String oamg[]){
        ModelMain model_main = new ModelMain();
        ModelPeliculas model_peliculas = new ModelPeliculas(model_main);
        ModelClientes model_clientes = new ModelClientes(model_main);
        ModelRentas model_rentas = new ModelRentas(model_main);
        
        ViewMain view_main = new ViewMain();
        ViewPeliculas view_peliculas = new ViewPeliculas();
        ViewClientes view_clientes = new ViewClientes();
        ViewRentas view_rentas = new ViewRentas();
        
        Object[] models = new Object[4];
        Object[] views = new Object[4];
        Object[] controllers = new Object[4];
        
        models[0] = model_main;
        models[1] = model_peliculas;
        models[2] = model_clientes;
        models[3] = model_rentas;
        
        views[0] = view_main;
        views[1] = view_peliculas;
        views[2] = view_clientes;
        views[3] = view_rentas;
        
        ControllerClientes controller_clientes = new ControllerClientes(models, views, controllers);
        controllers[0] = controller_clientes;
        
        ControllerPeliculas controller_peliculas = new ControllerPeliculas(models, views, controllers);
        controllers[1] = controller_peliculas;
        
        ControllerMain controller_main = new ControllerMain(models, views, controllers);
        controllers[2] = controller_main;
        
        ControllerRentas controller_rentas = new ControllerRentas(models, views, controllers);
        controllers[3] = controller_rentas;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier
 */
public class GestorBarajas {
    
    private List<Baraja> barajas;
    private Baraja barajaPorDefecto;
    private String nombreBarajaPorDefecto = "Baraja de animales";
    private CaraPosterior caraPosterior = new CaraPosterior(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"),"CaraPosterior por defecto");
    private final static String RUTA_BARAJAS = System.getProperty("user.dir") + "/src/imagenes/Barajas/";
    private final static String RUTA_IMAGENES = "imagenes/Barajas/";

    public GestorBarajas(){
        barajas = new ArrayList<>();
        barajaPorDefecto = new Baraja();
    }

    /**
     *
     * @return Lista de barajas
     */
    public List<Baraja> getBarajas(){ return barajas;}

    /**
     *
     * @return Devuelve la barajaPorDefecto
     */
    public Baraja getBarajaPorDefecto(){ return barajaPorDefecto;}

    public CaraPosterior getCaraPosterior(){return caraPosterior;}
    /**
     * Establece una lista de barajas
     * @param nuevaBarajas
     */
    public void setBarajas(List<Baraja> nuevaBarajas){this.barajas = nuevaBarajas;}

    /**
     * Establece la baraja por defecto
     * @param nuevaBarajaPorDefecto
     */
    public void setBarajaPorDefecto(Baraja nuevaBarajaPorDefecto){this.barajaPorDefecto = nuevaBarajaPorDefecto;}

    /**
     * Añade barajas a la lista de barajas
     * @param baraja
     * @return
     */
    public boolean añadirBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(!existeLaBaraja(baraja)) {
            barajas.add(baraja);
            return true;
        }
        return false;
    }

    /**
     * Elimina una baraja de la lista de barajas
     * @param baraja
     * @return
     */
    public boolean eliminarBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(existeLaBaraja(baraja)){
            barajas.remove(baraja);
            return true;
        }
        return false;
    }
    
    
    /**
     * Método que carga las barajas que hay en la carpeta de Barajas en 
     * src\imagenes\Barajas
     */
    public void cargarBarajas(){
        barajas.clear();
        GestorArchivos gestor = new GestorArchivos();
        File directorioBarajas = new File(RUTA_BARAJAS);
        String[] listaDeBarajas = directorioBarajas.list();      
        for (String listaDeBaraja : listaDeBarajas) {
            Baraja baraja = new Baraja();
            File carpetaBaraja = new File(RUTA_BARAJAS + listaDeBaraja + "/");
            String[] listaCartasBaraja = carpetaBaraja.list();
            if(listaCartasBaraja.length != 0){
                for(int j = 0; j < listaCartasBaraja.length; j++)
                    baraja.añadirCarta(new Carta(new Image(RUTA_IMAGENES
                            + listaDeBaraja + "/"
                            + listaCartasBaraja[j]), listaCartasBaraja[j], j));
                int index = listaDeBaraja.indexOf(";");           
                baraja.setNombre(listaDeBaraja.substring(0,index));
                baraja.setTematica(listaDeBaraja.substring(index + 1));
                baraja.setCaraPosterior(caraPosterior);
                añadirBaraja(baraja);  
            }
            else gestor.deleteFile(carpetaBaraja);
        }
             
    }
    
    public Baraja barajaATrios(Baraja baraja){
        List<Carta> cartas = new ArrayList();
        baraja.getCartas().forEach((carta) -> {
            boolean checkExists = false;
            for(int i = 0 ; i < cartas.size(); i++){
                if( carta.getId() == cartas.get(i).getId())
                    checkExists = true;
            }
            if(!checkExists)
                cartas.addAll(multiplicarCarta(3,carta));
        });
        Baraja barajaTrios = new Baraja(cartas,baraja.getCaraPosterior(),baraja.getNombre(),(baraja.getTamaño()/2)*3,baraja.getTematica());        
        return barajaTrios;
    }
    
    private List<Carta> multiplicarCarta(int i, Carta carta){
        List<Carta> cartaMultiplicada = new ArrayList();
        for(int j = 0; j < i; j++) {
            cartaMultiplicada.add(new Carta(carta.getImagen(),carta.getNombre(),carta.getId()));
        }
        return cartaMultiplicada;
    }
    
    /**
     * Carga la baraja por defecto
     */
    public void cargarBarajaPorDefecto(){
       barajaPorDefecto = buscarBaraja(nombreBarajaPorDefecto);
    }

    /**
     * Comprueba si existe una baraja
     * @param barajaNueva
     * @return
     */
    public boolean existeLaBaraja(Baraja barajaNueva){
        for(Baraja barajaExistente : barajas) {
            if(barajaExistente.EqualsTo(barajaNueva)) return true;
        }
        return false;       
    }
    
    /**
     * Busca la baraja a partir de su nombre
     * @param nombreBaraja
     * @return 
     */
    public Baraja buscarBaraja(String nombreBaraja){       
        for (Baraja baraja : barajas) 
            if(baraja.getNombre().equals(nombreBaraja))
                return baraja;              
    return null;
    }
    public void añadirCartaCategoria(){

    }
    public Baraja barajaCategorias(){
        Baraja barajaCategoria = new Baraja();
        int id = 0;
        for(Baraja baraja : barajas){
            for(int i = 0; i <=3; i+=2){
                Carta carta = baraja.getCartas().get(i);
                barajaCategoria.añadirCartaCategoria(new CartaCategoria(carta.getImagen(),carta.getNombre(),id,baraja.getTematica()));
                id++;
            }
        }
        barajaCategoria.setCaraPosterior(caraPosterior);
        return barajaCategoria;
    }
}

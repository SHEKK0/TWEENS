/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import javafx.scene.image.Image;

/**
 *
 * @author Javier
 */
public enum Idioma {
    Español(new Image("imagenes/ImagenesSistema/idiomas/idioma_español.jpg")),
    Frances(new Image("imagenes/ImagenesSistema/idiomas/idioma_frances.png")),
    Ingles(new Image("imagenes/ImagenesSistema/idiomas/idioma_ingles.jpg")),
    Valenciano(new Image("imagenes/ImagenesSistema/idiomas/idioma_valenciano.png"));
    
    private final Image imagenBandera;
    
    Idioma(Image imagenBandera){
        this.imagenBandera = imagenBandera;
    }
    
    public Image getImagenBandera(){ return imagenBandera; }

}

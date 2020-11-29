/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayer;

import java.io.File;
import javafx.scene.media.Media;

/**
 *
 * @author El Pitagoras
 */
public class Video implements IVideo {

    private String nombre;
    private Media videoFX;

    public Video(String nombre) {
        this.nombre = nombre;
        generarMedia();
    }

    private void generarMedia() {
        File fi = new File(nombre);
        videoFX = new Media(fi.toURI().toString());
    }

    @Override
    public String getPath() {
        return nombre;
    }

    @Override
    public Media getVideoMedia() {
        return videoFX;
    }

}

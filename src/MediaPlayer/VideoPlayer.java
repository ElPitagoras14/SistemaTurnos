/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayer;

import TDAs.CircularLinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.*;
import javafx.util.Duration;

/**
 *
 * @author El Pitagoras
 */
public class VideoPlayer {

    private static VideoPlayer instance;
    private CircularLinkedList<IVideo> listaVideo;
    private MediaPlayer reproductor;
    private MediaView ventanaVideo;
    private boolean ejecutar;

    private VideoPlayer() {
        listaVideo = new CircularLinkedList<>();
        ventanaVideo = new MediaView();
        ejecutar = true;
        ingresarVideos();
        siguienteVideo();
    }

    public static VideoPlayer getInstance() {
        if (instance == null) {
            instance = new VideoPlayer();
        }
        return instance;
    }

    private void ingresarVideos() {
        listaVideo.addLast(new Video("heladero.mp4"));
        listaVideo.addLast(new Video("gato.mp4"));
        listaVideo.addLast(new Video("inferno.mp4"));

    }

    private void siguienteVideo() {
        ejecutar = false;
        reproductor = new MediaPlayer(listaVideo.recorrerLlamada().getVideoMedia());
        ventanaVideo.setMediaPlayer(reproductor);
        reproducir();
    }

    public void reproducir() {
        ejecutar = true;
        Thread hilo = new Thread(new HiloVideo());
        hilo.start();
    }

    public void cambiarVentana() {
        ejecutar = false;
        reproductor.pause();
    }

    public MediaView getVentanaVideo() {
        return ventanaVideo;
    }

    private class HiloVideo implements Runnable {

        Duration tiempoActual;
        Duration tiempoTotal;

        public HiloVideo() {
            tiempoActual = Duration.ZERO;
        }

        @Override
        public void run() {
            try {
                reproductor.play();
                Thread.sleep(1000);
                tiempoTotal = reproductor.getTotalDuration();
                while (!tiempoActual.equals(tiempoTotal) && ejecutar) {
                    tiempoActual = reproductor.getCurrentTime();
                }
                if (ejecutar) {
                    Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE,"Cambio Video");
                    siguienteVideo();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

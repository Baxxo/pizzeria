package it.pizzeria;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.net.URL;


public class audio extends JFrame{
	 public audio() {
	     

	   }

public String s;

public void setSound(String s){
	this.s=s;
	System.out.println(s);
}
public void suona(){
    try {
       // Open an audio input stream.
       URL url = this.getClass().getClassLoader().getResource(s+".mp3");
       AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
       // Get a sound clip resource.
       Clip clip = AudioSystem.getClip();
       // Open audio clip and load samples from the audio input stream.
       clip.open(audioIn);
       clip.start();
    } catch (UnsupportedAudioFileException e) {
       e.printStackTrace();
    } catch (IOException e) {
       e.printStackTrace();
    } catch (LineUnavailableException e) {
       e.printStackTrace();
    }
}

}

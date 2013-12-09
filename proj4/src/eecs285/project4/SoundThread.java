package eecs285.project4;

import static eecs285.project4.Constants.BASE_PATH;
import static eecs285.project4.Constants.SOUND_PATH;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundThread extends Thread {
    private AudioClip clip;
    
    public SoundThread(AudioClip c) {
        clip = c;
    }
    
    public void run() {
        clip.play();        
    }
}

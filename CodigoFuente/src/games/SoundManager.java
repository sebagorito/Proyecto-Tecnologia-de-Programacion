package games;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
	private static SoundManager instance;
	private Map<String, Clip> soundClips;
	private final String generalPath = "src/sounds/";
	private float volumeLevel = -20.0f;
	
	public SoundManager() {
		soundClips = new HashMap<>();
	}
	
	public static SoundManager getInstance() {
		if(instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}
	
	public void setVolume(float volume) {
        this.volumeLevel = volume;
    }
	
	public void playSound(String soundPath) {
		String path = generalPath + soundPath + ".wav";
		Clip clip = soundClips.get(path);
		
		if(clip == null) {
			try {
				File soundFile = new File(path);
				AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
				clip = AudioSystem.getClip();
				clip.open(audio);
				soundClips.put(path, clip);
			} catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
                return;
			}
		}
		FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(volumeLevel);
		
		clip.setFramePosition(0);
        clip.start();
	}
	
	public void playLoop(String soundFilePath) {
	    String path = generalPath + soundFilePath + ".wav";
	    Clip clip = soundClips.get(path);
	    
	    try {
	        if (clip == null) {
	            File soundFile = new File(path);
	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
	            clip = AudioSystem.getClip();
	            clip.open(audioStream);
	            soundClips.put(path, clip); 
	        }

	        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        volumeControl.setValue(volumeLevel);
	        
	        clip.setFramePosition(0);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}

	
	public void stopAudio(String soundFilePath) {
		String path = generalPath+soundFilePath+".wav";
		Clip clip = soundClips.get(path);
		if(clip != null && clip.isRunning()) {
			clip.stop();
		}
	}
	
	public void stopAllSounds() {
		for(Clip clip : soundClips.values()) {
			if(clip.isRunning())
				clip.stop();
		}
	}
	
}

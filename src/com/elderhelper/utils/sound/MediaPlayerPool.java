package com.elderhelper.utils.sound;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.widget.MediaController.MediaPlayerControl;

public class MediaPlayerPool {
  
  public static enum LoadMethod {
    SYNC, ASYNC;
  }
  
  private static final int AFTER_LAST_MEDIA_PLAYER_INDEX = -1;
  
  private String[] fileNames;
  private MediaPlayer[] mediaPlayers;
  private LoadMethod loadMethod;
  
  public MediaPlayerPool(String baseDirectory, String defaultExtension, LoadMethod loadMethod, String... fileNames) {
    this.fileNames = new String[fileNames.length];
    this.loadMethod = loadMethod;
    constructFileNames(baseDirectory, defaultExtension, fileNames);
    mediaPlayers = new MediaPlayer[fileNames.length];
  }

  private void constructFileNames(String baseDirectory,
      String defaultExtension, String... fileNames) {
    for (int counter = 0; counter < fileNames.length; counter++) {
      this.fileNames[counter] = constructFileName(baseDirectory,fileNames[counter],defaultExtension);
    }
    
  }
  
  private String constructFileName(String baseDirectory, String fileName,
      String defaultExtension) {
    if (defaultExtension != null && !fileName.endsWith(defaultExtension)) {
      fileName = new StringBuilder(fileName).append(".").append(defaultExtension).toString();
    }
    if (baseDirectory != null && !fileName.startsWith(baseDirectory)) {
      return new File(baseDirectory,fileName).getAbsolutePath();
    }
    return fileName; 
  }

  public void load() throws IllegalArgumentException, IllegalStateException, IOException {
    if (loadMethod == LoadMethod.SYNC) {
      for (int counter = 0; counter < fileNames.length; counter++) {
        mediaPlayers[counter] = new MediaPlayer();
        mediaPlayers[counter].setDataSource(fileNames[counter]);
        mediaPlayers[counter].prepare();
        mediaPlayers[counter].setOnCompletionListener(new MediaPlayerPoolCompletionListener(mediaPlayers, counter));
      }
    }
  }
  
  public void play() {
    mediaPlayers[0].start();
  }
  
  private static class MediaPlayerPoolCompletionListener implements OnCompletionListener {

    private int mediaPlayerIndex;
    private MediaPlayer[] mediaPlayers;
    
    public MediaPlayerPoolCompletionListener(MediaPlayer[] mediaPlayers, int mediaPlayerIndex) {
      this.mediaPlayerIndex = mediaPlayerIndex;
      this.mediaPlayers = mediaPlayers;
    }
    
    @Override
    public void onCompletion(MediaPlayer mp) {
      if (mediaPlayerIndex == AFTER_LAST_MEDIA_PLAYER_INDEX) {
        return;
      }
      mediaPlayers[mediaPlayerIndex+1].start();
    }
  }
}

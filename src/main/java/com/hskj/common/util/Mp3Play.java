package com.hskj.common.util;

import javax.sound.sampled.*;
import java.io.File;

/**
 * MP3播放
 * Created by hongHan_gao
 * Date: 2018/1/22
 */


public class Mp3Play {

    public void play(String filePath){
        try {
            File file = new File(filePath);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = getOutFormat(inputStream.getFormat());
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip c = (Clip)AudioSystem.getLine(info);
            if(null != c){
                c.open(AudioSystem.getAudioInputStream(format, inputStream));
                c.start();
                Thread.sleep(5000);
                c.drain();
                c.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private AudioFormat getOutFormat(AudioFormat format) {
        final int ch = format.getChannels();
        final float rate = format.getSampleRate();
        return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, rate, 16, ch, ch*2, rate, false);
    }

    public static void main(String[] args) {
        Mp3Play player = new Mp3Play();
        player.play("E:/KuGou/1.mp3");
    }

}

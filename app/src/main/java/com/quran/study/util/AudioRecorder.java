package com.quran.study.util;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class AudioRecorder {
    private String mDirectory;
    private String mFileName;
    private String mPureFileName;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;

    public AudioRecorder(Activity activity, String fileName) {
        mDirectory = activity.getExternalCacheDir().getAbsolutePath();
        this.mPureFileName = (fileName.contains(".3gp") ? fileName : fileName + ".3gp");
        this.mFileName = mDirectory + "/" + mPureFileName;
    }

    public void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("START RECORDING", "prepare() failed");
        }

        mRecorder.start();
    }

    public void stopRecording() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public boolean recordingIsNull() {
        return mRecorder == null;
    }

    public boolean playerIsNull() {
        return mPlayer == null;
    }

    public boolean isPlay() {
        try {
            return mPlayer.isPlaying();
        } catch (Exception e) {
            return false;
        }
    }

    public void stopPlaying() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public AudioRecorder setMediaRecorder(MediaRecorder mediaRecorder) {
        this.mRecorder = mediaRecorder;
        return this;
    }

    public AudioRecorder setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mPlayer = mediaPlayer;
        return this;
    }

    public MediaRecorder getMediaRecorder() {
        return mRecorder;
    }

    public MediaPlayer getMediaPlayer() {
        return mPlayer;
    }

    public String getFileName() {
        return mPureFileName;
    }
}

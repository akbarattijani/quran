package com.quran.study.global;

import com.quran.study.util.AudioRecorder;

import java.util.HashMap;
import java.util.Map;

public class VariableConstant {
    public static VariableConstant instance;
    private static Map<String, AudioRecorder> audioConstant = new HashMap<>();

    public void setAudioConstant(Map<String, AudioRecorder> audioConstant) {
        VariableConstant.audioConstant = audioConstant;
    }

    public Map<String, AudioRecorder> getAudioConstant() {
        return audioConstant;
    }

    static {
        instance = new VariableConstant();
    }

    public static VariableConstant getInstance() {
        return VariableConstant.instance;
    }

    public void reset() {
        instance = new VariableConstant();
    }
}

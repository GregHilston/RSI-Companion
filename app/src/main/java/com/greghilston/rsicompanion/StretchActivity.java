package com.greghilston.rsicompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Vector;

public class StretchActivity extends AppCompatActivity {
    private Vector<Stretch> stretches;
    private int currentStretchIndex;

    private class Stretch {
        private String name;
        private int duration;

        public Stretch(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stretches = new Vector<Stretch>();
        stretches.add(new Stretch("Upside Down Wrist Extend", 10));
        stretches.add(new Stretch("Upside Down Fist", 10));
        stretches.add(new Stretch("Bicep Flex", 10));

        currentStretchIndex = 0;

        graphicallyUpdateCurrentStretch(currentStretchIndex);
    }

    /**
     * Updates the UI to reflect the currently selected stretch
     * @param currentStretchIndex    the stretch to display
     */
    private void graphicallyUpdateCurrentStretch(int currentStretchIndex) {
        TextView textView = (TextView) findViewById(R.id.stretch_name_textview);
        System.err.println("currentStretchIndex:" + this.currentStretchIndex);
        textView.setText(this.stretches.get(currentStretchIndex).name);
    }


    public void nextExercise(View view) {
        this.currentStretchIndex++;

        System.err.println("this.stretches.size():" + this.stretches.size());

        if(this.currentStretchIndex >= this.stretches.size()) { // Wrap back
            this.currentStretchIndex = 0;
        }

        graphicallyUpdateCurrentStretch(this.currentStretchIndex);
    }

    public void previousExercise(View view) {
        this.currentStretchIndex--;

        if(this.currentStretchIndex < 0) { // Wrap forward
            this.currentStretchIndex = this.stretches.size() - 1;
        }

        graphicallyUpdateCurrentStretch(this.currentStretchIndex);
    }
}

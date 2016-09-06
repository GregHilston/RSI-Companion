package com.greghilston.rsicompanion;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.AbstractBadgeableDrawerItem;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Vector;

/**
 * Creates the MVP (Model View Presentation) structure and also holds the View component.
 */
public class MainActivity extends AppCompatActivity implements ExerciseView {
    private ExercisePresenter exercisePresenter;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercisePresenter = new ExercisePresenter(this);
        this.drawer = createDrawer();
    }

    /**
     * Creates the material drawer
     * @return create drawer
     */
    private Drawer createDrawer() {
        Vector<AbstractBadgeableDrawerItem> drawerItems =  new Vector<>();

        // create the drawer and remember the `Drawer` result object
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .build();

        this.exercisePresenter.addActivitiesToDrawer(drawer);

        drawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                MainActivity.this.exercisePresenter.ithDrawerExercise(position);
                MainActivity.this.drawer.closeDrawer();
                return true;
            }
        });

        return drawer;
    }

    /**
     * Handles on next exercise button click listener
     *
     * @param view current view
     */
    public void clickNextExercise(View view) {
        exercisePresenter.nextExercise();
    }

    /**
     * Handles on previous exercise button click listener
     *
     * @param view current view
     */
    public void clickPreviousExercise(View view) {
        exercisePresenter.previousExercise();
    }

    /**
     * Handles on toggle start stop timer button click listener
     *
     * @param view current view
     */
    public void clickToggleStartStopTimer(View view) {
        exercisePresenter.toggleStartStopTimer();
    }

    /**
     * Handles on increment timer button click listener
     * @param view current view
     */
    public void clickIncrementTimer(View view) {
        exercisePresenter.incrementTimer();
    }

    /**
     * Handles on restart timer button click listener
     * @param view current view
     */
    public void clickRestartTimer(View view) {
        exercisePresenter.restartTimer();
    }

    @Override
    public void setTimerText(String timeFormattedString) {
        // Update the timer TextView
        TextView timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setText(String.valueOf(timeFormattedString));
    }

    @Override
    public void updateStretch(Exercise s) {
        // Update the exercise name
        TextView exerciseNameTextView = (TextView) findViewById(R.id.stretchNameTextView);
        exerciseNameTextView.setText(s.getName());

        // Update the exercise gif
        pl.droidsonroids.gif.GifTextView gifTextView = (pl.droidsonroids.gif.GifTextView) findViewById(R.id.currentExerciseGif);
        gifTextView.setBackgroundResource(s.getDrawableId());
        gifTextView.setFreezesAnimation(false);
    }

    @Override
    public void updatePausePlayButtonImage(int drawableId) {
        // Update the pause play button
        ImageButton pausePlayButton = (ImageButton) findViewById(R.id.pausePlayButton);
        pausePlayButton.setImageResource(drawableId);
    }
}

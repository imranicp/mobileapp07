package logic.main.com.boardgame.manager;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;

import logic.main.com.boardgame.R;


//this class manages the game music, which is always played in the background
// of the game in case in case the music setting is set to ON
public class MusicManager {

    //the variable which is used to for the Menu Music resource
    public static final int MUSIC_MENU = 0;

    //the hash Map which stores the values of the players
    private static HashMap<Integer, MediaPlayer> players = new HashMap();

    //inorder to make the music continuously playing across activities we change the same music from
    //previous to current, thus the continuity of the music is established

    //current music variable which stores the current music value
    private static int currentMusic = -1;

    //previous music variable which holds the previous music value
    private static int previousMusic = -1;

    //this function is triggered when in game the Muci Manager is triggered for the first time

    public static void start(Context context, int music) {

        //it calls the start function and passes the context of the launcher activity, with the music resource,
        //and a force which means the players can be forced stop in case the music playback has to be interrupted
        start(context, music, false);

    }


    //start playing the music in the background, the context which is supplied to the function
    public static void start(Context context, int music, boolean force) {

        if (!force && currentMusic == 0) {
            // already playing some music and not forced to change
            return;
        }

        if (currentMusic == music) {
            // already playing this music
            return;
        }

        //when currentMusic is not -1 means something else is playing and it must be stopped
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            // playing some other music, pause it and change
            pause();
        }

        //setting the current music value to the supplied music resource
        currentMusic = music;

        //all the music must be stored in a hashMap to identify each one of them uniquely
        MediaPlayer mp = players.get(music);

        //when mp is not null and it is not playing then that music must start playing
        if (mp != null) {

            //checking if it is not playing
            if (!mp.isPlaying()) {
                //starting the music
                mp.start();
            }
        } else {

            //if the music which is requested for the first time is menu music
            //then media player is created to start playing it
            if (music == MUSIC_MENU) {
                //creating the new media player object
                mp = MediaPlayer.create(context, R.raw.game_music);
            } else {
                //we return the function back if some other music is initially requested
                return;
            }

            // we add the menu music into the hash map with the resource as the key to it
            players.put(music, mp);

            if (mp == null) {
                //logging error if player was not created successfully
                Log.e("ERROR", "player was not created successfully");
            } else {
                try {
                    //if player is created successfully then we loop it continuously
                    // till the context is destroyed or the music is paused
                    mp.setLooping(true);

                    //start playing the music
                    mp.start();
                } catch (Exception e) {

                    //Logging any exception in playing the music
                    Log.e("LOG", e.getMessage(), e);
                }
            }
        }
    }

    //when an activity is paused then we must pause all the music which is playing or else it will continue to play in the background
    public static void pause() {

        //we will get all the media player values into a collection
        Collection<MediaPlayer> mps = players.values();

        //loop over the media player
        for (MediaPlayer p : mps) {

            //if the player is playing then we must pause the music
            if (p.isPlaying()) {

                //pausing the music
                p.pause();
            }
        }

        // previousMusic should always be something valid
        if (currentMusic != -1) {

            //setting previous music to current music as
            // we might need to resume it when the activity resumes
            previousMusic = currentMusic;

        }

        //setting current music to -1 which means that the music has been paused
        currentMusic = -1;

    }

    public static void release() {

        //we will get all the media player values into a collection to release them
        // media players are relesaed once they finish playing
        Collection<MediaPlayer> mps = players.values();

        //all the media players looped upon
        for (MediaPlayer mp : mps) {
            try {

                //if the media player is active then it will not be null
                if (mp != null) {

                    //if the media player is playing then it must be stopped
                    if (mp.isPlaying()) {

                        //stopping the media player
                        mp.stop();
                    }
                    //relasing the media player object
                    mp.release();
                }

            } catch (Exception e) {
                //logging any exception which occurs
                Log.e("LOG", e.getMessage(), e);
            }
        }

        //clearing the collection
        mps.clear();

        //if current music is not -1 then previous music is set to the value of current music
        if (currentMusic != -1) {
            previousMusic = currentMusic;

        }

        //setting current music to -1 which means that the music has been released
        currentMusic = -1;

    }
}

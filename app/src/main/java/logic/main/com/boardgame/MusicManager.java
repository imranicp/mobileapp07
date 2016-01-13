package logic.main.com.boardgame;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;

public class MusicManager {
    public static final int MUSIC_MENU = 0;
    private static final String TAG = "MusicManager";
    // private static final int PREF_DEFAULT_MUSIC_VOLUME_ITEM = 4;
    private static HashMap<Integer, MediaPlayer> players = new HashMap();
    private static int currentMusic = -1;
    private static int previousMusic = -1;

    /*public static float getMusicVolume(Context context) {
        String[] volumes = context.getResources().getStringArray(R.array.volume_values);
        String volumeString = PreferenceManager.getDefaultSharedPreferences(context).getString(
                context.getString(R.string.key_pref_music_volume), volumes[PREF_DEFAULT_MUSIC_VOLUME_ITEM]);
        return new Float(volumeString).floatValue();
    }*/

    public static void start(Context context, int music) {
        start(context, music, false);
    }

    public static void start(Context context, int music, boolean force) {
        if (!force && currentMusic == 0) {
            // already playing some music and not forced to change
            return;
        }

        if (currentMusic == music) {
            // already playing this music
            return;
        }
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
            // playing some other music, pause it and change
            pause();
        }
        currentMusic = music;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
        MediaPlayer mp = players.get(music);

        if (mp != null) {
            if (!mp.isPlaying()) {
                mp.start();
            }
        } else {
            if (music == MUSIC_MENU) {
                mp = MediaPlayer.create(context, R.raw.game_music);
            } else {
                Log.e(TAG, "unsupported music number - " + music);
                return;
            }

            players.put(music, mp);
            //float volume = getMusicVolume(context);
            //Log.d(TAG, "Setting music volume to " + volume);
            //mp.setVolume(volume, volume);
            //mp.setVolume(1);
            if (mp == null) {
                Log.e(TAG, "player was not created successfully");
            } else {
                try {
                    mp.setLooping(true);
                    mp.start();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        }
    }

    public static void pause() {

        Collection<MediaPlayer> mps = players.values();
        for (MediaPlayer p : mps) {
            if (p.isPlaying()) {

                p.pause();
            }
        }
// previousMusic should always be something valid
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
        }
        currentMusic = -1;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
    }

    /*public static void updateVolumeFromPrefs(Context context) {
        try {
            float volume = getMusicVolume(context);
            Log.d(TAG, "Setting music volume to " + volume);

            Collection<MediaPlayer> mps = players.values();
            for (MediaPlayer p : mps) {
                p.setVolume(volume, volume);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }*/

    public static void release() {
        Log.d(TAG, "Releasing media players");
        Collection<MediaPlayer> mps = players.values();
        for (MediaPlayer mp : mps) {
            try {
                if (mp != null) {
                    if (mp.isPlaying()) {
                        Log.d(TAG, "STOPPING media players");
                        mp.stop();
                    }
                    mp.release();
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        mps.clear();
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
        }
        currentMusic = -1;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
    }
}

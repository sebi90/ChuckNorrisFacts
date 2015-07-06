package de.chucknorrisfacts;

import android.os.AsyncTask;

/**
 * Created by Sebi on 05.07.15.
 */


public class Timer extends AsyncTask<Integer, Integer, Integer>{

    @Override
    protected Integer doInBackground(Integer... params) {

        while (true)
        {
            publishProgress();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

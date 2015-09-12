package com.kpinfotech.progressnotification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

public class Downloader extends AsyncTask<Void, Integer, Integer> {
	
	Activity activity;
	
	private NotificationManager mNotifyManager;
	private Builder mBuilder;
	int id = 1;
	
	public Downloader(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		mNotifyManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(activity);
		mBuilder.setContentTitle(activity.getResources().getString(R.string.app_name))
				.setContentText("Download in progress")
				.setSmallIcon(R.drawable.ic_launcher);

		// Displays the progress bar for the first time.
		mBuilder.setProgress(100, 0, false);
		mNotifyManager.notify(id, mBuilder.build());
	}

	@Override
	protected Integer doInBackground(Void... params) {
		
		for (int i = 0; i <= 100; i += 5) {
			// Sets the progress indicator completion percentage
			publishProgress(Math.min(i, 100));
			try {
				// Sleep for 5 seconds
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				Log.d("TAG", "sleep failure");
			}
		}
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		// Update progress
		mBuilder.setProgress(100, values[0], false);
		mNotifyManager.notify(id, mBuilder.build());
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		mBuilder.setContentText("Download complete");
		// Removes the progress bar
		mBuilder.setProgress(0, 0, false);
		mNotifyManager.notify(id, mBuilder.build());
	}

}
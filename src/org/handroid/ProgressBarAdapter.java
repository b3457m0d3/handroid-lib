package org.handroid;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarAdapter {

	public ProgressBarAdapter(ProgressBar progressBar, Handler handler) {
		this.handler = handler;
		this.progressBar = progressBar;
	}

	class ProgressTask extends TimerTask {
		@Override
		public void run() {
			boolean working = !progressJobs.isEmpty();
			int visible = getVisibility();

			if (working && visible == View.INVISIBLE) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						progressShow();
					}
				});
			} else if (!working && visible == View.VISIBLE) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						progressHide();
					}
				});
			}

		}
	}

	private Timer cycleTimer;
	private Handler handler;
	private ProgressBar progressBar;

	private ArrayList<String> progressJobs = new ArrayList<String>();

	public void progressShow() {
		if (progressBar == null) {
			return;
		}
		progressBar.setVisibility(View.VISIBLE);
	}

	public void progressHide() {
		if (progressBar == null) {
			return;
		}
		progressBar.setVisibility(View.INVISIBLE);
	}

	public int getVisibility() {
		if (progressBar == null) {
			return View.INVISIBLE;
		}
		return progressBar.getVisibility();
	}

	public final void start() {
		stop();
		cycleTimer = new Timer();
		cycleTimer.scheduleAtFixedRate(new ProgressTask(), 500, 300);
	}

	public final void stop() {
		if (cycleTimer != null) {
			cycleTimer.cancel();
			cycleTimer = null;
		}
	}

	public final void register(String tag) {
		if (!progressJobs.contains(tag))
			progressJobs.add(tag);
	}

	public final void unregister(String tag) {
		if (progressJobs.contains(tag))
			progressJobs.remove(tag);
	}
}

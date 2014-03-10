package org.handroid;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;


public class RunnableScheduler {

	private final String tag;
	private final int delay;
	private int interval;

	private final Runnable task;
	private Timer cycleTimer = null;

	public RunnableScheduler(String tag, int delay, int interval, Runnable task) {
		this.tag = tag;
		this.delay = delay;
		this.interval = interval;
		this.task = task;
	}

	public final String getTag() {
		return tag;
	}
	
	public final void setInterval(int interval) {
		this.interval = interval;
		restart();
	}
	
	public final int getInterval() {
		return interval;
	}

	public final void start() {
		stop();

		cycleTimer = new Timer();
		cycleTimer.scheduleAtFixedRate(new BackgroundTask(), delay, interval);

		Log.d(tag, "cycle started");
	}

	public final void stop() {
		if (cycleTimer != null) {
			cycleTimer.cancel();
			cycleTimer = null;

			Log.d(tag, "cycle canceled");
		}
	}
	
	public final void restart() {
		if (cycleTimer != null) {
			stop();
			start();
		}
	}

	class BackgroundTask extends TimerTask {

		@Override
		public void run() {
			task.run();
		}

	}
}

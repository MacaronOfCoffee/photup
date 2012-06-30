package uk.co.senab.photup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Application;
import android.content.Context;

public class PhotupApplication extends Application {

	static final int EXECUTOR_CORE_POOL_SIZE = 4;
	static final int EXECUTOR_MAX_POOL_SIZE = 10;

	private ExecutorService mExecutor;

	private final PhotoSelectionController mPhotoController = new PhotoSelectionController();

	public static PhotupApplication getApplication(Context context) {
		return (PhotupApplication) context.getApplicationContext();
	}

	public ExecutorService getExecutorService() {
		if (null == mExecutor) {
			mExecutor = createExecutor();
		}
		return mExecutor;
	}

	public PhotoSelectionController getPhotoSelectionController() {
		return mPhotoController;
	}

	private static ExecutorService createExecutor() {
		return new ThreadPoolExecutor(EXECUTOR_CORE_POOL_SIZE, EXECUTOR_MAX_POOL_SIZE, 1L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

}

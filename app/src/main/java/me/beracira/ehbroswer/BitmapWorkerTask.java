package me.beracira.ehbroswer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by beracira on 30/07/16.
 */
public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewWeakReference;
    private String url = "";


    public BitmapWorkerTask (ImageView imageView) {
        imageViewWeakReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        this.url = params[0];
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (isCancelled()) bitmap = null;

        if (imageViewWeakReference != null && bitmap != null) {
            final ImageView imageView = imageViewWeakReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

//    static class AsyncDrawable extends BitmapDrawable {
//        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;
//
//        public AsyncDrawable(Resources res, Bitmap bitmap,
//                             BitmapWorkerTask bitmapWorkerTask) {
//            super(res, bitmap);
//            bitmapWorkerTaskReference =
//                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
//        }
//
//        public BitmapWorkerTask getBitmapWorkerTask() {
//            return bitmapWorkerTaskReference.get();
//        }
//    }
}


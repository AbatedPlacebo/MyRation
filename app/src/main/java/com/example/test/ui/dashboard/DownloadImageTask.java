package com.example.test.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);

            float h = mIcon11.getHeight();
            float w = mIcon11.getWidth();

            float ratio = w / h ;

            System.out.println("height is r :- "+h);
            System.out.println("width is r :- "+w);
            System.out.println("ratio is w/h:- "+ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
//bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, 250, 250, false));
//bmImage.setImageBitmap(getResizedBitmap(result, 250, 250));
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
// CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
// RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);


// RECREATE THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
}
package com.zacck.androidimagedownloaddemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView downloadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadedImage = (ImageView)findViewById(R.id.downloadImageView);
    }

    public void getImage(View view)
    {
        //http://vignette4.wikia.nocookie.net/happytreefriends/images/b/b4/Nutty_profile.png/revision/latest?cb=20150120171503
        ImageDownloader mImageDownloader = new ImageDownloader();
        Bitmap mDownloadedImage;
        try {
            mDownloadedImage = mImageDownloader.execute("http://vignette4.wikia.nocookie.net/happytreefriends/images/b/b4/Nutty_profile.png/revision/latest?cb=20150120171503").get();
            downloadedImage.setImageBitmap(mDownloadedImage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.i(getPackageName(), "Button Got tapped");
    }


    //asynctask to download image
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... imageUrls) {
            try
            {
                URL mImageUrl = new URL(imageUrls[0]);
                //lets create the connection
                HttpURLConnection mConnection = (HttpURLConnection)mImageUrl.openConnection();
                //perfoprm the actual connection
                mConnection.connect();
                //get the stream of data from the connection
                InputStream imageInputStream = mConnection.getInputStream();
                //use the data from the connection to build a Bitmap
                Bitmap myBitmap = BitmapFactory.decodeStream(imageInputStream);

                return myBitmap;


            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }

        }
    }
}

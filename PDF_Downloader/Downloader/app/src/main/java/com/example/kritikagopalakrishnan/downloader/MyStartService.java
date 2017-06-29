package com.example.kritikagopalakrishnan.downloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import android.os.*;
import java.io.*;

public class MyStartService extends Service {
    public MyStartService() {
    }
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        //creating a new thread to run in the background.
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
                    String[] urls = new String[objUrls.length];
                    for (int i = 0; i < objUrls.length - 1; i++) {
                        urls[i] = (String) objUrls[i];
                    }
                    //passing the URLs to AsyncTask.
                    new DoBackgroundTask().execute(urls);
                } catch (Exception e) {
                }
            }
        };
        t.start();
        stopSelf();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //IBinder returns null as this is a start service
        return null;
    }
//AsyncTask class.
    class DoBackgroundTask extends AsyncTask<String, Integer, Long> {
        int execute = 0;
        URL url;
        protected Long doInBackground(String... urls) {
            int count = 5;
            HttpURLConnection connection = null;
            String fileName = "KritiService";
            //for loop to download the array of URLs.
            for (execute = 0; execute < count; execute++) {
                try {

                    String fileExtension = ".pdf";
                    url = new URL(urls[execute]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    String PATH = Environment.getExternalStorageDirectory() + "/Kriti'sDownloads/";
                    File file = new File(PATH);
                    file.mkdirs();
                    File outputFile = new File(file, fileName + fileExtension);
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    InputStream is = connection.getInputStream();
                    byte[] buffer = new byte[4096];
                    int len1 = 0;

                    while ((len1 = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len1);
                    }
                    fos.flush();
                    is.close();

                    System.out.println("--pdf downloaded--ok--" + url);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                stopSelf();
            }
            return null;
        }
// final toast that notifies completion of download.
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "Downloaded" + execute + " file(s)", Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }
}

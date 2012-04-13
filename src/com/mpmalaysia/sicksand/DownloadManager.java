package com.mpmalaysia.sicksand;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;


import android.content.Intent;
import android.os.Environment;
import android.util.Log;

 
 
public class DownloadManager {
 
        private final String PATH = Environment.getExternalStorageDirectory() + "/";  //put the downloaded file here
       
 
        public void DownloadFromUrl(String imageURL, String fileName) {  //this is the downloader method
        	boolean exists = (new File(PATH + fileName)).exists();
        	if (exists) { 
        	//open new intent	
        		Log.d("DownloadManager","File exist : "+ fileName);
        		
        	} else { // File or directory does not exist}
                try {
                        URL url = new URL(imageURL); //you can write here any link
                        File file = new File(PATH + fileName);
                        Log.d("DownloadManager","Filename "+ file);
                        long startTime = System.currentTimeMillis();
                        Log.d("DownloadManager", "download begining");
                        Log.d("DownloadManager", "download url:" + url);
                        Log.d("DownloadManager", "downloaded file name:" + fileName);
                        /* Open a connection to that URL. */
                        URLConnection ucon = url.openConnection();
 
                        /*
                         * Define InputStreams to read from the URLConnection.
                         */
                        InputStream is = ucon.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is);
 
                        /*
                         * Read bytes to the Buffer until there is nothing more to read(-1).
                         */
                        ByteArrayBuffer baf = new ByteArrayBuffer(50);
                        int current = 0;
                        while ((current = bis.read()) != -1) {
                                baf.append((byte) current);
                        }
 
                        /* Convert the Bytes read to a String. */
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(baf.toByteArray());
                        fos.close();
                        Log.d("DownloadManager", "download ready in"
                                        + ((System.currentTimeMillis() - startTime) / 1000)
                                        + " sec");
 
                } catch (IOException e) {
                        Log.d("DownloadManager", "Error: " + e);
                }
        	}
        }


		private void startActivity(Intent intent) {
			// TODO Auto-generated method stub
			
		}
}
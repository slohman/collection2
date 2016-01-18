package com.ocbiofuel.collection;

/**
 * Created by scott on 1/18/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class filemaintmain extends Activity {


    public static final int Main_Menu = 0;
    String Msg, response;
    String filename;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    Context ctx = this;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filemaintmain);


        // instantiate it within the onCreate method
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        // execute this when the downloader must be fired


        Button installMdb = (Button) findViewById(R.id.installMainDB);
        installMdb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Builder b = new AlertDialog.Builder(filemaintmain.this);

                b.setTitle("Download Main File and Overwrite?");

                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int which) {
                        d.dismiss();
                        fileDownLoadMain downloadFile = new fileDownLoadMain();
                        downloadFile.execute("http://199.68.191.212/");

                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int which) {
                        d.dismiss();
                        Intent myIntent = new Intent(getBaseContext(), filemaintmain.class);
                        startActivityForResult(myIntent, 0);
                    }
                });

                b.create().show();


            }
        });


    }








    public class fileDownLoadMain extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... aurl) {


            final File DataDir = new File(getFilesDir().getParent() + "/databases/");

            //this is the name of the local file you will create
            File f = new File(DataDir.toString());

            if (f.exists()) {
            } else {
                f.mkdirs();
            }

            int count;
            try {
                URL url = new URL(aurl[0] + "TsetOcbio.htm");
                HttpURLConnection conection = (HttpURLConnection) url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                try {
                    OutputStream output = new FileOutputStream(DataDir + "/PocketCal.db");


                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        int prog = (int) ((total * 100) / lenghtOfFile);
                        publishProgress(prog);

                        // writing data to file
                        output.write(data, 0, count);
                    }

                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();
                } catch (FileNotFoundException fe) {
                }

            } catch (Exception e) {


            }

            return null;

        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            // copy file to data dir here
            pDialog.dismiss();


        }

        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For example showing ProgessDialog
            super.onPreExecute();
            pDialog.show();


        }


        protected void onProgressUpdate(Integer... progress) {

            pDialog.setProgress((progress[0]));
        }


    }




}
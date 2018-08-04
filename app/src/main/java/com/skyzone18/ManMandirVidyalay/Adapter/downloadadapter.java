package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.skyzone18.ManMandirVidyalay.JsonActivity.DownloadActivity;
import com.skyzone18.ManMandirVidyalay.Model.CheckForSDCard;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rahul Patel on 2/16/2018.
 */

public class downloadadapter extends RecyclerView.Adapter<downloadadapter.ViewHolder> {

    List<Datum> downloadlist;
    Context contet;
    private static final String TAG = "Download Task";
    private Context context;
    private String downloadUrl = "", downloadFileName = "";

    public downloadadapter(DownloadActivity downloadActivity, ArrayList<Datum> items) {

        this.downloadlist = items;
        this.contet = downloadActivity;
    }

    @Override
    public downloadadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_download_data, null);


        return new downloadadapter.ViewHolder(itemview);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final downloadadapter.ViewHolder holder, final int position) {

        holder.download_name.setText(downloadlist.get(position).getTitle());
//        holder.txtsub.setText("બધા વિષય");
//         /*String dis = "abc,surat,er";*/
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        Date newDate = null;
//        try {
//            newDate = format.parse(downloadlist.get(position).getSubmitiondate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        format = new SimpleDateFormat("dd/MM/yyyy");
//        String maindate = format.format(newDate);
//
//        holder.download_date.setText("Submission Date : " + maindate);

        holder.download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadlist.get(position).getPdfFile().toString()));
                contet.startActivity(browserIntent);
//                ///  dwonload url set karavi
//                DownloadTask(contet, downloadlist.get(position).getAssignment().toString());
//                // contet.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(downloadlist.get(position).getPath())));
//            }
//
//            private void DownloadTask(Context contet, String path) {
//                context = contet;
//                downloadUrl = path;
////// parth  ni url set karavi
//                downloadFileName = downloadUrl.replace("http://mpanel.realenglishschoolinternational.com/Photos/pdfs/", "");
//                Log.e(TAG, downloadFileName);
//
//                //Start Downloading Task
//                new DownloadingTask().execute();
//            }
            }
        });

    }


    @Override
    public int getItemCount() {
        return downloadlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView download_name, download_date, txtsub;
        CardView download_btn;

        public ViewHolder(View itemView) {
            super(itemView);

            download_name = (TextView) itemView.findViewById(R.id.download_name);
            download_date = (TextView) itemView.findViewById(R.id.download_date);
            download_btn = (CardView) itemView.findViewById(R.id.download_btn);
            txtsub = (TextView) itemView.findViewById(R.id.tvSubject);


        }

    }

    private class DownloadingTask extends AsyncTask<Void, Void, Void> {
        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context, "Download Started", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Download Again", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");
                    Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();

                //Change button text if exception occurs
                Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Download Again", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }


            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection

                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }


                //Get File if SD card is present
                if (new CheckForSDCard().isSDCardPresent()) {

                    apkStorage = new File(
                            Environment.getExternalStorageDirectory() + "/"
                                    + "Assignment Downloads");
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }
    }

}

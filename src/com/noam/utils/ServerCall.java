package com.noam.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.example.myapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ServerCall extends AsyncTask<String, Integer, Void> {

  Callback callback;
  Activity activity;
  String server;

  public ServerCall(Activity activity, Callback callback) {
    super();
    this.activity = activity;
    this.callback = callback;
    this.server = activity.getString(R.string.server_url);
  }

  @Override
  protected Void doInBackground(String... params) {
    URL url;
    BufferedReader in;
    try {
      url = new URL(server);
      Log.d("server call", url.toString());
      in = new BufferedReader(new InputStreamReader(url.openStream()));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    String result = "";
    String temp;
    try {
      while ((temp = in.readLine()) != null) {
        result += temp;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    callback.call(result);
    return null;
  }
}

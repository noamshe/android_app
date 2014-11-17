package com.noam.utils;

import android.app.Activity;

public class WordsServerCall extends ServerCall {
  public WordsServerCall(Activity activity, Callback callback) {
    super(activity, callback);
  }

  public void getWords(String whatever) {
    this.execute(whatever);
  }
}

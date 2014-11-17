package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.noam.utils.Callback;
import com.noam.utils.WordsServerCall;

public class MyActivity extends Activity implements Callback {
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    WordsServerCall wordsServerCall = new WordsServerCall(this, this);
    Log.d("main", "TESTING");
    wordsServerCall.getWords("bla");
  }

  @Override
  public void call(final String result) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        ((TextView) findViewById(R.id.words)).setText(result);
      }
    });
  }
}

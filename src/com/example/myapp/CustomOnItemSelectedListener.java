package com.example.myapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.noam.utils.Callback;
import com.noam.utils.WordsServerCall;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener, Callback {
  private AdapterView<?> parent;
  private int pos;

  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
    this.parent = parent;
    this.pos = pos;
    Toast.makeText(parent.getContext(),
        "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
        Toast.LENGTH_SHORT).show();
    WordsServerCall wordsServerCall = new WordsServerCall(null, this);
    wordsServerCall.getWords("bla");
  }

  @Override
  public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void call(String result) {
    Toast.makeText(parent.getContext(),
        "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
        Toast.LENGTH_SHORT).show();
  }
}

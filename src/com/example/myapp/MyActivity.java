package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.noam.utils.Callback;
import com.noam.utils.WordsServerCall;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements Callback, AdapterView.OnItemSelectedListener {
  /**
   * Called when the activity is first created.
   */
  private Spinner spinner1, spinner2;
  private Button btnSubmit;
  private WordsServerCall wordsServerCall;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    wordsServerCall = new WordsServerCall(this, this);
    Log.d("main", "TESTING");
    wordsServerCall.getWords("bla");
  }

  // add items into spinner dynamically
  public void addItemsOnSpinner2(ThemeList themes) {
    //{"1":"The Shadow Of the Wind","10":"Theme","11":"The Client","12":"Gone with the wind","13":"The man with the best english","14":"","15":"","16":"444","17":"ggg","18":"fff"}

    spinner2 = (Spinner) findViewById(R.id.spinner2);
    List<String> list = new ArrayList<String>();
//    list.add("list 1");
//    list.add("list 2");
//    list.add("list 3");
    for (Theme theme : themes.getThemes()) {
      list.add(theme.getTheme_name());
    }

    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, list);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner2.setAdapter(dataAdapter);
  }

  public void addListenerOnSpinnerItemSelection() {
    spinner1 = (Spinner) findViewById(R.id.spinner2);
//    spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    spinner1.setOnItemSelectedListener(this);
  }

  @Override
  public void call(final String result) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
//        ((TextView) findViewById(R.id.words)).setText(result);
        Gson gson = new Gson();
        String result = "{\"themes\":[{\"id\":\"1\", \"theme_name\":\"The Shadow Of the Wind\"}, {\"id\":\"2\", \"theme_name\":\"Gone With The Wind\"}]}";
        ThemeList themeList = gson.fromJson(result, ThemeList.class);
        addItemsOnSpinner2(themeList);

        addListenerOnSpinnerItemSelection();
      }
    });
  }

  @Override
  public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
    Toast.makeText(parent.getContext(),
        "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
        Toast.LENGTH_SHORT).show();
    wordsServerCall = new WordsServerCall(this, new Callback() {
      @Override
      public void call(final String result) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Toast.makeText(parent.getContext(), result, Toast.LENGTH_SHORT).show();
          }
        });
      }
    });
    wordsServerCall.getWords("bla");
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}

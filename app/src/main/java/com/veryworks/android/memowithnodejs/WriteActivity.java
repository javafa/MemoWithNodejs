package com.veryworks.android.memowithnodejs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.veryworks.android.memowithnodejs.domain.Qna;

import static com.veryworks.android.memowithnodejs.MainActivity.SITE_URL;

public class WriteActivity extends AppCompatActivity {

    EditText editTitle, editName, editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editName = (EditText) findViewById(R.id.editAuthor);
        editContent = (EditText) findViewById(R.id.editContent);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<String, Void, String> networkTask = new AsyncTask<String, Void, String>(){
                    @Override
                    protected String doInBackground(String... params) {
                        String title = params[0];
                        String name = params[1];
                        String content = params[2];

                        Qna qna = new Qna();
                        qna.setTitle(title);
                        qna.setName(name);
                        qna.setContent(content);

                        Gson gson = new Gson();
                        String jsonString = gson.toJson(qna);
                        String result = Remote.postJson(SITE_URL+"bbs", jsonString);

                        if("SUCCESS".equals(result)){
                            // 성공적으로 등록하면 내가 쓴 글을 목록에 더해준다.
                            DataStore dataStore = DataStore.getInstance();
                            dataStore.addData(qna);
                        }

                        return result;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);
                        Toast.makeText(WriteActivity.this, result, Toast.LENGTH_LONG).show();
                        finish();
                    }
                };

                networkTask.execute(editTitle.getText().toString()
                                    ,editName.getText().toString()
                                    ,editContent.getText().toString());
            }
        });
    }
}

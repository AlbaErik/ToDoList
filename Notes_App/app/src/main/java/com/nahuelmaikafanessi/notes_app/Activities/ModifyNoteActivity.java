package com.nahuelmaikafanessi.notes_app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nahuelmaikafanessi.notes_app.DB_Managment.DBManager;
import com.nahuelmaikafanessi.notes_app.NotesListActivity;
import com.nahuelmaikafanessi.notes_app.R;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    private Button update_btn;
    private Button share_btn;
    private Button delete_btn;
    private EditText subjectText;
    private EditText descText;

    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Note");
        setContentView(R.layout.activity_modify_note);

        subjectText = findViewById(R.id.subject_edit_text);
        descText = findViewById(R.id.description_edit_text);
        update_btn = findViewById(R.id.update_btn);
        share_btn = findViewById(R.id.share_btn);
        delete_btn = findViewById(R.id.delete_btn);

        dbManager = new DBManager(this);
        dbManager.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");


        _id = Long.parseLong(id);

        subjectText.setText(name);
        descText.setText(desc);


        update_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_btn:
                final String name = subjectText.getText().toString();
                final String desc = descText.getText().toString();

                dbManager.update(_id,name,desc);
                this.returnHome();
                break;

            case R.id.delete_btn:
                dbManager.delete(_id);
                this.returnHome();
                break;

            case R.id.share_btn:
                 Intent shareIntent = new Intent(Intent.ACTION_SEND);
                 shareIntent.putExtra(Intent.EXTRA_TEXT,descText.getText().toString());
                 shareIntent.setType("text/plain");
                 startActivity(Intent.createChooser(shareIntent,"Compartir por"));

        }
    }

    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(), NotesListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
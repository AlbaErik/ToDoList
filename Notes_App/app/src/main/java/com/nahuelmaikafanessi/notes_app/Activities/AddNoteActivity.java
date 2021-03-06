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

public class AddNoteActivity extends Activity implements View.OnClickListener {

    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;

    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Note");
        setContentView(R.layout.activity_add_note);

        subjectEditText = findViewById(R.id.subject_edit_text);
        descEditText = findViewById(R.id.description_edit_text);
        addTodoBtn = findViewById(R.id.add_note);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note:
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                dbManager.insert(name,desc);

                Intent main = new Intent(AddNoteActivity.this,
                        NotesListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
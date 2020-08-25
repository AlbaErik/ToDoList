package com.nahuelmaikafanessi.notes_app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.nahuelmaikafanessi.notes_app.NotesListActivity;
import com.nahuelmaikafanessi.notes_app.R;

public class InfoActivity extends Activity implements View.OnClickListener{
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Informacion");
        setContentView(R.layout.activity_info);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                Intent main = new Intent(InfoActivity.this,
                        NotesListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}

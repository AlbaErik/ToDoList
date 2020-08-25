package com.nahuelmaikafanessi.notes_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.nahuelmaikafanessi.notes_app.Activities.AddNoteActivity;
import com.nahuelmaikafanessi.notes_app.Activities.ConfigActivity;
import com.nahuelmaikafanessi.notes_app.Activities.InfoActivity;
import com.nahuelmaikafanessi.notes_app.Activities.ModifyNoteActivity;
import com.nahuelmaikafanessi.notes_app.DB_Managment.DBManager;
import com.nahuelmaikafanessi.notes_app.DB_Managment.DatabaseHelper;

public class NotesListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC};

    final int[] to  = new int[] {R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this,R.layout.activity_view, cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        //OnClickListener for List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long view_id) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView titleTextView = view.findViewById(R.id.title);
                TextView descTextView = view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyNoteActivity.class);

                modify_intent.putExtra("title",title);
                modify_intent.putExtra("desc",desc);
                modify_intent.putExtra("id",id);

                startActivity(modify_intent);
            }
        });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_note){
            Intent add_n= new Intent(this, AddNoteActivity.class);
            startActivity(add_n);
        }
        if(id == R.id.configuracion){
            Intent config= new Intent(this, ConfigActivity.class);
            startActivity(config);
        }
        if(id == R.id.informacion){
            Intent info= new Intent(this, InfoActivity.class);
            startActivity(info);
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.nahuelmaikafanessi.notes_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.nahuelmaikafanessi.notes_app.Activities.AddNoteActivity;
import com.nahuelmaikafanessi.notes_app.Activities.InfoActivity;
import com.nahuelmaikafanessi.notes_app.Activities.ModifyNoteActivity;
import com.nahuelmaikafanessi.notes_app.DB_Managment.DBManager;
import com.nahuelmaikafanessi.notes_app.DB_Managment.DatabaseHelper;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    private ArrayList<String> listItem;
    private SimpleCursorAdapter adapter2;
    private DatabaseHelper dbHelper;
    
    
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
        
        
        listItem = new ArrayList<String>();

        dbHelper = new DatabaseHelper(this);

        adapter = new SimpleCursorAdapter(this,R.layout.activity_view, cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        //OnClickListener for List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long view_id) {

                Intent modify_intent = new Intent(getApplicationContext(), ModifyNoteActivity.class);

                if(view.findViewById(R.id.id) != null){
                    TextView idTextView = view.findViewById(R.id.id);
                    String id = idTextView.getText().toString();
                    modify_intent.putExtra("id",id);
                }
                if(view.findViewById(R.id.title) != null){
                    TextView titleTextView = view.findViewById(R.id.title);
                    String title = titleTextView.getText().toString();
                    modify_intent.putExtra("title",title);
                }
                if(view.findViewById(R.id.desc) != null){
                    TextView descTextView = view.findViewById(R.id.desc);
                    String desc = descTextView.getText().toString();
                    modify_intent.putExtra("desc",desc);
                }

                startActivity(modify_intent);
            }
        });
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Cursor cursor2 = dbHelper.searchRows(newText);
                adapter2 = new SimpleCursorAdapter(NotesListActivity.this,R.layout.activity_view, cursor2,from,to,0);
                adapter2.notifyDataSetChanged();
                listView.setAdapter(adapter2);

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_note){
            Intent add_n= new Intent(this, AddNoteActivity.class);
            startActivity(add_n);
        }
        if(id == R.id.informacion){
            Intent info= new Intent(this, InfoActivity.class);
            startActivity(info);
        }

        return super.onOptionsItemSelected(item);
    }
}
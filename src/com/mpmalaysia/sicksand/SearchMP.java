package com.mpmalaysia.sicksand;


import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SearchMP extends ListActivity {
    /** Called when the activity is first created. */
	private DBAdapter mDbHelper;
	private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
    }

}


package com.mpmalaysia.sicksand;



import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;


public class MemberofParliamentMalaysiaActivity extends ListActivity {
	 //public Object DBAdapter;



	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DBAdapter dbHelper = new DBAdapter(this);
        dbHelper.open();
        
        // Get a Cursor for the list items
        Cursor listCursor = dbHelper.fetchMP();
        startManagingCursor(listCursor);
        
        // set the custom list adapter
        setListAdapter(new MyListAdapter(this, listCursor));
    }
    
    

	private class MyListAdapter extends ResourceCursorAdapter {
        
        public MyListAdapter(Context context, Cursor cursor) {
            super(context, R.layout.list_item_with_description, cursor);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        	TextView title = (TextView) view.findViewById(R.id.item_title);
        	title.setText(cursor.getString(
        				cursor.getColumnIndex(DBAdapter.KEY_NAME)));
        	
        	TextView details = (TextView) view.findViewById(R.id.item_details);
        	StringBuffer detailsText = new StringBuffer();
        	
        	String parti = cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_PARTI));
        	
        	detailsText.append(parti);
        	
        	String description = cursor.getString(cursor.getColumnIndex(
        			DBAdapter.KEY_PAR));
        	if (description != null && description.length() > 0){
        		detailsText.append(", "+description);
        	}
        	details.setText(detailsText.toString());
        	
        }

    }
}
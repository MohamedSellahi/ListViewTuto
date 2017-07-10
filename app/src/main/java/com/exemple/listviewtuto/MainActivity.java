package com.exemple.listviewtuto;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      // create an ArrayAdapter and attach it to the list view
      final ListView _listView = (ListView) findViewById(R.id.listView);

      // test string
      String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
              "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
              "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
              "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
              "Android", "iPhone", "WindowsMobile"};

      // cretae a sample list based on the array
      final ArrayList<String> _list = new ArrayList<>();
      for(int i = 0; i < values.length; ++i){
         _list.add(values[i]);
      }

      // the adapter
      final StableArrayAdapter adp = new StableArrayAdapter(
              this,android.R.layout.simple_list_item_1,_list);
      final MySimpleArrayAdapter adp1 = new MySimpleArrayAdapter(this,values);

      final MySimpleArrayAdapter adp2 = new MySimpleArrayAdapter(this,R.layout.phone_list_layout,_list);
      _listView.setAdapter(adp2);

      // add OnitemClickListener
/*

      _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            final String item = (String)parent.getItemAtPosition(position);
            view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
               @Override
               public void run() {
                  _list.remove(item);
                  adp.notifyDataSetChanged();
                  view.setAlpha(1);
               }
            });
         }
      });
*/







      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
         }
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   // custom adapter
   // must extends ArrayAdapter
   private class StableArrayAdapter extends ArrayAdapter<String>{
      HashMap<String,Integer> mIdMap = new HashMap<>();

      public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects){
         super(context,textViewResourceId,objects);
         for(int i = 0; i < objects.size(); ++i){
            mIdMap.put(objects.get(i),i);
         }
      }

      @Override
      public long getItemId(int position){
         String item = getItem(position);
         return mIdMap.get(item);
      }

      @Override
      public boolean hasStableIds(){
         return true;
      }
   }


   // Developping a custom adapter
   public class MySimpleArrayAdapter extends ArrayAdapter<String>{
      private final Context _context;
      private String[] _values = null;
      private List<String> _listValues = null;
      private int _resourceID = -1;

      // Constructor
      public MySimpleArrayAdapter(Context context, String[] values){
         super(context,-1,values);
         this._context = context;
         this._values = values;
         // fill in the list
         _listValues = new ArrayList<>();
         for(int i = 0; i < values.length; ++i){
            _listValues.add(values[i]);
         }
      }
      public MySimpleArrayAdapter(Context context, List<String> values){
         super(context, -1 ,values);
         _resourceID = -1;
         this._context = context;
         this._listValues = values;
      }

      public MySimpleArrayAdapter(Context context,int ResourceId , List<String> values){
         super(context,ResourceId,values);
         _resourceID = ResourceId;
         this._context = context;
         this._listValues = values;
      }



      @Override
      public View getView(int position, View convertView, ViewGroup parent){
         // get inflator service
         LayoutInflater inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         // Create the row view
         View rowView = inflater.inflate(_resourceID,parent,false);
         // get first line
         TextView textView = (TextView)rowView.findViewById(R.id.thumbTextFistLine);
         textView.setText(_listValues.get(position));

         // get image
         ImageView imageView = (ImageView)rowView.findViewById(R.id.thumbImg);
         if (position%2 == 1){
            imageView.setImageResource(R.drawable.homer);
         }



         // change icons according to the os
      /*   String s = _values[position];
         if (s.startsWith("iPhone")) {
            imageView.setImageResource(R.drawable.no);
         } else {
            imageView.setImageResource(R.drawable.ok);
         }*/
      return rowView;
      }

      @Override
      public long getItemId(int position){
         return position;
      }

      @Override
      public boolean hasStableIds(){
         return true;
      }


   }


   //


}

























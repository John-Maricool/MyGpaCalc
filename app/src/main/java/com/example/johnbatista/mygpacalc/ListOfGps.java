package com.example.johnbatista.mygpacalc;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListOfGps extends AppCompatActivity {

    ListView listView;
    MyDatabase myDatabase;
    MyAdapter myAdapter;

    TextView GpView;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> gpes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_gps);

        listView = (ListView)findViewById(R.id.list);
        myAdapter = new MyAdapter();
        myDatabase = new MyDatabase(this);

        Cursor data = myDatabase.DisplayList();

        if(data.getCount() == 0){
            Toast.makeText(this, "Nothing Entered", Toast.LENGTH_SHORT).show();
        }else{
            while(data.moveToNext()){
                names.add(data.getString(2));
                gpes.add(data.getString(1));
            }
        }
        myAdapter.notifyDataSetChanged();
        listView.setAdapter(myAdapter);
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected( MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        String PosName = names.get(position).toString();

        switch(item.getItemId()){
            case R.id.delete:
                names.remove(position);
                gpes.remove(position);
                if(myDatabase.DeleteRow(PosName)){
             Toast.makeText(this, "successfully deleted", Toast.LENGTH_SHORT).show();
            }
                myAdapter.notifyDataSetChanged();
                return true;
        }
        return true;
    }


    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(R.layout.entries, null);
            TextView nameView = (TextView) view.findViewById(R.id.name);
           TextView GpView = (TextView) view.findViewById(R.id.gp);

            nameView.setText(names.get(i));
            GpView.setText(gpes.get(i));

            return view;
        }
    }
}
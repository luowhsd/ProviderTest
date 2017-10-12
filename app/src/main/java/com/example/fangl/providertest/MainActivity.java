package com.example.fangl.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String AUTHORITY = "com.example.fangl.databasetest.provider";
    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("content://"+AUTHORITY+"/book");
                try{
                    ContentValues values = new ContentValues();
                    values.put("name","xasdajsd789");
                    values.put("author","Gsada dasdaw3");
                    values.put("pages",400);
                    values.put("price",20.55);
                    Uri newUri = getContentResolver().insert(uri,values);
                    newId = newUri.getPathSegments().get(1);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        Button queryData = (Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://"+AUTHORITY+"/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                  try{
                      while (cursor.moveToNext()){
                          String name = cursor.getString(cursor.getColumnIndex("name"));
                          String author = cursor.getString(cursor.getColumnIndex("author"));
                          int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                          double price = cursor.getDouble(cursor.getColumnIndex("price"));
                          Log.d(TAG, "name:"+name);
                          Log.d(TAG, "author:"+author);
                          Log.d(TAG, "pages:"+pages);
                          Log.d(TAG, "price:"+price);

                      }
                  }catch (Exception e){
                      e.printStackTrace();
                  }finally {
                      if(cursor!=null)
                          cursor.close();
                  }
                }
            }
        });
        Button updateData = (Button)findViewById(R.id.updata_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://"+AUTHORITY+"/book");
                ContentValues values = new ContentValues();
                values.put("name","heyheyhey2017");
                int updateRows = 0;
                updateRows = getContentResolver().update(uri,values,"name=?",new String[]{"xasdajsd789"});
                Toast.makeText(MainActivity.this, updateRows+"行更新", Toast.LENGTH_SHORT).show();
            }
        });
        Button deleteData = (Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://"+AUTHORITY+"/book");
                int deleteRows = 0;
//                deleteRows = getContentResolver().delete(uri,"author=?",new String[]{"jijijojo"});
                deleteRows = getContentResolver().delete(uri,null,null);
                Toast.makeText(MainActivity.this, deleteRows+"行删除", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

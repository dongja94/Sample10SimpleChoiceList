package com.example.dongja94.samplesimplechoicelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        String[] items = getResources().getStringArray(R.array.items);
        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(items));
        mAdpater = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, list);
        listView.setAdapter(mAdpater);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button btn = (Button)findViewById(R.id.btn_select);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                    int position = listView.getCheckedItemPosition();
                    String text = (String) listView.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this, "text : " + text, Toast.LENGTH_SHORT).show();
                } else if (listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
                    SparseBooleanArray selectArray = listView.getCheckedItemPositions();
                    StringBuilder sb = new StringBuilder();
                    for (int index = 0; index < selectArray.size(); index++) {
                        int position = selectArray.keyAt(index);
                        if (selectArray.get(position)) {
                            String text = (String)listView.getItemAtPosition(position);
                            sb.append(text).append(",");
                        }
                    }
                    Toast.makeText(MainActivity.this, "select : " + sb.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

//        listView.setItemChecked(0, true);
//        listView.setItemChecked(1, true);
    }
}

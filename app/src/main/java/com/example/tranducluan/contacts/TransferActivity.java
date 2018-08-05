package com.example.tranducluan.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity{
    TransferNumber transferNumber;
    ArrayList<MultiContacts> contactList;
    ArrayList<String> contactListShowBefore;
    ArrayList<String> contactListShowAfter;
    Button btnFinish;
    private ListView mListViewBefore;
    private ListView mListViewAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        mListViewBefore = (ListView) findViewById(R.id.list);
        mListViewAfter = findViewById(R.id.list1);
        btnFinish = findViewById(R.id.finish);

        contactList = new ArrayList<>();
        contactListShowBefore = new ArrayList<>();
        contactListShowAfter = new ArrayList<>();
        transferNumber = new TransferNumber();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransferActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent getListContacts = getIntent();
        contactList = getListContacts.getParcelableArrayListExtra("CONTACTS");
        for (MultiContacts contact: contactList) {
            contactListShowBefore.add("Tên: " + contact.getName() + "\nSĐT: " + contact.getNumberPhone()
                    + "     Nhà Mạng: " + contact.getNhaMang());
            contactListShowAfter.add("Tên: " + contact.getName() + "\nSĐT: " + contact.getPhoneAfterChange()
                    + "     Nhà Mạng: " + contact.getNhaMang());

        }
        showListContacts();
    }
    public void showListContacts(){
        // ListView has to be updated using a ui thread
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                ArrayAdapter<String> adapterBefore = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, R.id.textName, contactListShowBefore);
                mListViewBefore.setAdapter(adapterBefore);
                adapterBefore.notifyDataSetChanged();
                ArrayAdapter<String> adapterAfter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, R.id.textName, contactListShowAfter);
                mListViewAfter.setAdapter(adapterAfter);
                adapterAfter.notifyDataSetChanged();
            }
        });
    }
}

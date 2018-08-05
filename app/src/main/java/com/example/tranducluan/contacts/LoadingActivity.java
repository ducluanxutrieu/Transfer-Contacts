package com.example.tranducluan.contacts;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    MyAsyncTask myAsyncTask;
    private CheckNhaMang checkNhaMang;
    TransferNumber transferNumber;
    ArrayList<MultiContacts> contactList;
    TextView loading;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.txtLoading);
        myAsyncTask = new MyAsyncTask(this);
        contactList = new ArrayList<>();
        transferNumber = new TransferNumber();
        checkNhaMang = new CheckNhaMang();
        myAsyncTask.execute();

    }
    public void countDownTime() {
        new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress((int)(100-l/100));
                loading.setText("Loading: " + (100-l/100) + "%");
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        Activity contextParent;

        public MyAsyncTask(Activity contextParent) {
            this.contextParent = contextParent;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            countDownTime();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            getContacts();
            TransferHere();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(LoadingActivity.this, TransferActivity.class);
            intent.putParcelableArrayListExtra("CONTACTS", contactList);
            startActivity(intent);
            finish();
        }
    }
    public void getContacts() {
        String phoneNumber;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;

        Uri Phone_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        ContentResolver contentResolver = getContentResolver();

        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                // Update the progress message
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                String hasPhoneNumber = cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)).replace(" ", "");
                if (hasPhoneNumber.length() > 0) {
                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(Phone_CONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        String numberAfterChange = transferNumber.ChangeNumber(phoneNumber);
                        String nhaMang = checkNhaMang.KiemTraNhaMang(numberAfterChange);
                        contactList.add(new MultiContacts(contact_id, phoneNumber, name, numberAfterChange, nhaMang)); }
                        phoneCursor.close();
                    // Add the contact to the ArrayList
                }
            }

        }

    }
    public void TransferHere(){

        for(MultiContacts contacts : contactList)
        {
            updateContact(contacts.getName(), contacts.getPhoneAfterChange(), contacts.getId());
        }
    }

    public boolean updateContact(String name, String number, String ContactId) {
        boolean success = true;
        String phnumexp = "^[0-9]*$";

        try {
            if ((!number.isEmpty()) && (!match(number, phnumexp))) {
                success = false;
            }else {
                ContentResolver contentResolver = getContentResolver();

                String where = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                String[] nameParams = new String[]{ContactId, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
                String[] numberParams = new String[]{ContactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE};

                ArrayList<ContentProviderOperation> ops = new ArrayList<android.content.ContentProviderOperation>();


                if (!name.equals("")) {
                    ops.add(android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract.Data.CONTENT_URI)
                            .withSelection(where, nameParams)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                            .build());
                }

                if (!number.equals("")) {

                    ops.add(android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract.Data.CONTENT_URI)
                            .withSelection(where, numberParams)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                            .build());
                }
                contentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
            }
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }


    private boolean match(String stringToCompare, String regularExpression) {
        boolean success = false;
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(stringToCompare);
        if (matcher.matches())
            success = true;
        return success;
    }
}

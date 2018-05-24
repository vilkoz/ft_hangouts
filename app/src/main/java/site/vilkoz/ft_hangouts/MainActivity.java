package site.vilkoz.ft_hangouts;

import android.content.Intent;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import site.vilkoz.ft_hangouts.database.Contact;
import site.vilkoz.ft_hangouts.database.ContactCreateHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                openCreateContact();
            }
        });


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup main = (ViewGroup) findViewById(R.id.contact_item_list);

        ContactCreateHelper dbHelper = new ContactCreateHelper(this);
        ArrayList<Contact> Contacts = dbHelper.getContacts();

        for (Contact contact: Contacts) {
            View view = inflater.inflate(R.layout.contact_item_layout, null);
            TextView name_text = (TextView) view.findViewById(R.id.text_name);
            name_text.setText(contact.firstName + " " + contact.lastName);
            TextView phone_text = (TextView) view.findViewById(R.id.text_number);
            phone_text.setText(contact.phone);
//            if (name_text.getParent() != null) {
//                ((ViewGroup)name_text.getParent()).removeView(name_text);
//            }
            main.addView(view);
        }
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
            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void openCreateContact() {
        Intent settingsIntent = new Intent(MainActivity.this, CreateContactActivity.class);
        startActivity(settingsIntent);
    }
}

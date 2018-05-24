package site.vilkoz.ft_hangouts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import site.vilkoz.ft_hangouts.database.ContactCreateHelper;

public class CreateContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
    }

    public void createContact(View view) {
        Intent settingsIntent = new Intent(CreateContactActivity.this, MainActivity.class);
        EditText firstName = findViewById(R.id.editText5);
        EditText lastName = findViewById(R.id.editText3);
        EditText email = findViewById(R.id.editText2);
        EditText phone = findViewById(R.id.editText);
        EditText secondPhone = findViewById(R.id.editText6);

        if (firstName.getText().toString().isEmpty()
                || phone.getText().toString().isEmpty()) {
            showMessage(view, getString(R.string.create_contact_error));
            return ;
        }

        ContactCreateHelper dbHelper = new ContactCreateHelper(this);
        if (!dbHelper.addContact(firstName.getText().toString(),
                lastName.getText().toString(),
                phone.getText().toString(),
                secondPhone.getText().toString(),
                email.getText().toString())) {
            showMessage(view, getString(R.string.create_contact_sql_error));
            return ;
        }
        startActivity(settingsIntent);
    }

    private void showMessage(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
    }
}

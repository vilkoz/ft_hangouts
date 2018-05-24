package site.vilkoz.ft_hangouts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import site.vilkoz.ft_hangouts.database.ContactReaderContract.ContactEntry;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ContactCreateHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContactEntry.TABLE_NAME +
            " (" + ContactEntry._ID + " INTEGER PRIMARY KEY," +
            ContactEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
            ContactEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
            ContactEntry.COLUMN_NAME_PHONE + " TEXT," +
            ContactEntry.COLUMN_NAME_SECOND_PHONE + " TEXT," +
            ContactEntry.COLUMN_NAME_EMAIL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contacts.db";

    public ContactCreateHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Upgrade schema without deleting data
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

//    @Override
//    public void onDonwgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
//    }

    public boolean addContact(String firstName, String lastName, String phone,
                              String secondPhone, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactEntry.COLUMN_NAME_FIRST_NAME, firstName);
        values.put(ContactEntry.COLUMN_NAME_LAST_NAME, lastName);
        values.put(ContactEntry.COLUMN_NAME_PHONE, phone);
        values.put(ContactEntry.COLUMN_NAME_SECOND_PHONE, secondPhone);
        values.put(ContactEntry.COLUMN_NAME_EMAIL, email);

        long newRowId = db.insert(ContactEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            return FALSE;
        }
        return TRUE;
    }

    public ArrayList getContacts() {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                ContactEntry._ID,
                ContactEntry.COLUMN_NAME_FIRST_NAME,
                ContactEntry.COLUMN_NAME_LAST_NAME,
                ContactEntry.COLUMN_NAME_PHONE,
                ContactEntry.COLUMN_NAME_SECOND_PHONE,
                ContactEntry.COLUMN_NAME_EMAIL
        };

        String sortOrder = ContactEntry._ID + " DESC";

        Cursor cursor = db.query(
                ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Contact> contacts = new ArrayList<>();

        while (cursor.moveToNext()) {
            Contact tmp;
            tmp = new Contact(
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.COLUMN_NAME_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.COLUMN_NAME_LAST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.COLUMN_NAME_PHONE)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.COLUMN_NAME_SECOND_PHONE)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.COLUMN_NAME_EMAIL))
            );
            contacts.add(tmp);
        }
        cursor.close();
        return contacts;
    }
}

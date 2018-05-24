package site.vilkoz.ft_hangouts.database;

import android.provider.BaseColumns;

public final class ContactReaderContract {
    private ContactReaderContract() {}

    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_SECOND_PHONE = "second_phone";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}

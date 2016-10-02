package iiitd.nehacompany.datastrorage_a3;

import android.provider.BaseColumns;

/**
 * Created by nehaj on 2/10/16.
 */
public class FeedReaderContract {
    // We make the constructor of this class private to prevent someone from accidentally instantiating the contract class.

    private FeedReaderContract() {}
    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "student_entry";
        public static final String COLUMN_NAME_1 = "student_name";
        public static final String COLUMN_NAME_2 = "favourite_movie";
        public static final String COLUMN_NAME_3 = "roll_number";
    }
}

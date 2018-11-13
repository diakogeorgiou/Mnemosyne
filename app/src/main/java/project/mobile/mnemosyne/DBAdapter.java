package project.mobile.mnemosyne;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

public class DBAdapter {
    private static final String DATABASE_NAME = "mnemosyne.db";
    private static final int DATABASE_VERSION = 1;

    private static final String tableUsers = "CREATE TABLE [users](\n" +
            "  [_id] INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
            "  [username] NVARCHAR NOT NULL UNIQUE, \n" +
            "  [password] NVARCHAR NOT NULL, \n" +
            "  [date_of_birth] DATETIME);";

    private static final String tableDecks = "CREATE TABLE [decks](\n" +
            "  [did] INTEGER PRIMARY KEY AUTOINCREMENT, \n" + //Deck's Unique ID.
            "  [cid] INTEGER FOREIGN KEY REFERENCES tableCards [cid] ON UPDATE CASCADE ON DELETE CASCADE, \n" + //Card's Unique ID.
            "  [uid] INTEGER FOREIGN KEY, REFERENCES tableUsers [_id] ON DELETE CASCADE;);";

    private static final String tableCards = "CREATE TABLE [cards](\n" +
            "  [cid] INTEGER PRIMARY KEY AUTOINCREMENT, \n" + //Card's Unique ID.
            "  [ftext] NVARCHAR, \n" + //Front Text of a card (A part of the FrontCardFragment).
            "  [fmedia] NVARCHAR, \n" + //Front Media of a card (B part of the FrontCardFragment).
            "  [btext] NVARCHAR, \n" + //Back Text of a card (A part of the BackCardFragment).
            "  [bmedia] NVARCHAR, \n" + //Back Media of a card (B part of the BackCardFragment).
            "  [nexttostudy] DATETIME, \n" + //This field holds the 'when this card will be shown again'.
            "  [ncard] INTEGER DEFAULT 1, \n" + // This integer acts as a flag to show if a card is shown for the first time.
            "  [bcard] INTEGER DEFAULT 0);"; //Bury this card to review it once all new cards are done. Once all cards are done All buried cards are being casted in a method as new cards.

    private static final String tableSessions = "CREATE TABLE [sessions] (\n" +
            "  [sid] INTEGER PRIMARY KEY AUTOINCREMENT, \n" + //Session's Unique ID.
            "  [did] INTEGER FOREIGN KEY REFERENCES tableDecks [did], \n" + //Point to decks' id.
            "  [cid] INTEGER FOREIGN KEY REFERENCES tableCards [cid], \n" + //Point to cards' id.
            "  [ncards] INTEGER, \n" + // This holds the user's preference in how many NEW cards wants to study.
            "  [rcards] INTEGER, \n" + // This holds the user's preference in how many REVISION cards wants to study.
            "  [starttime] INTEGER NOT NULL, \n" + // This holds the start time of the session.
            "  [endtime] INTEGER NOT NULL, \n" + // The end time of the session.
            "  [efficiency] INTEGER);"; // X cards (new + old) over Y time (endtime - start time). STUDY EFFICIENCY = CARDS/TIME





    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(this.context);
    }

    //Open database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Close database
    public void close() {
        DBHelper.close();
    }

    public long saveUser(User user) throws Exception {
        ContentValues initialValues = new ContentValues();

        initialValues.put("username", user.getUsername());
        initialValues.put("password", user.getHashedPassword());

        //Convert date to string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        initialValues.put("date_of_birth", dateFormat.format(user.getDate_of_birth()));

        return db.insert("users", null, initialValues);
    }

//    public long saveCurrency(Currency_Object Curr) {
//        ContentValues initialValues = new ContentValues();
//
//        //Search if currency exists
//        String sql = "select * from [currencies] where [short_descr]=" + Curr.getShort_descr();
//        Cursor c = db.rawQuery(sql, null);
//        c.moveToFirst();
//        if (c.getCount() == 0) {
//            c.close();
//
//            initialValues.put("descr", Curr.getDescr());
//            initialValues.put("eng_descr", Curr.getEng_descr());
//            initialValues.put("short_descr", Curr.getShort_descr());
//            initialValues.put("symbol", Curr.getSymbol());
//            initialValues.put("decimal", Curr.getDecimal());
//            initialValues.put("isdefault", Curr.getDecimal());
//
//            return db.insert("currencies", null, initialValues);
//        } else {
//            c.close();
//            return Long.parseLong(c.getString(c.getColumnIndex("currency_id")));
//        }
//    }
//
//    public long saveAccount(Account_Object Acc) {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put("descr", Acc.getDescr());
//        initialValues.put("notes", Acc.getNotes());
//        initialValues.put("currency_id", Acc.getCurrency_id());
//        initialValues.put("balance", Acc.getBalance());
//
//        return db.insert("accounts", null, initialValues);
//    }
//
//    public boolean deleteAccount(long rowId) {
//        return db.delete("accounts", "_id=" + rowId, null) > 0;
//    }
//
//    public void initializeDatabase() {
//
//    }
//
//    public ArrayList<Account_Object> getAccounts() {
//        Cursor c, d;
//        String sql = "SELECT [accounts].*, [currencies].[symbol], [currencies].[decimal]\n" +
//                "FROM [accounts] INNER JOIN\n" +
//                "  [currencies] ON [currencies].[_id] = [accounts].[currency_id]";
//
//        c = db.rawQuery(sql, null);
//
//        ArrayList<Account_Object> arAccounts = new ArrayList<Account_Object>();
//
//        c.moveToFirst();
//        do {
//            try {
//                Account_Object acc = new Account_Object();
//
//                acc.set_id(Integer.valueOf(c.getString(c.getColumnIndex("_id"))));
//                acc.setNotes(c.getString(c.getColumnIndex("notes")));
//                acc.setCurrency_id(Integer.parseInt(c.getString(c.getColumnIndex("currency_id"))));
//                acc.setDescr(c.getString(c.getColumnIndex("descr")));
//                acc.setBalance(Float.parseFloat(c.getString(c.getColumnIndex("balance"))));
//
//                acc.setCurrencySymbol(c.getString(c.getColumnIndex("symbol")));
//                acc.setCurrencyDecimals(Integer.parseInt(c.getString(c.getColumnIndex("decimal"))));
//
//                arAccounts.add(acc);
//
//            } catch (Exception e) {
//                Log.d("Error", e.getMessage());
//                return null;
//            }
//
//        } while (c.moveToNext());
//        c.close();
//
//        return arAccounts;
//    }
//
//    public float getAccountsTotal(ArrayList<Account_Object> arAcc) {
//        float acTotal = 0;
//        for (int i = 0; i < arAcc.size(); i++) {
//            acTotal = acTotal + arAcc.get(i).getBalance();
//        }
//        return acTotal;
//    }
//
//    public Currency_Object getDefaultCurrency() {
//        Cursor c;
//        String sql = "select * from [currencies] where [isdefault]=1";
//
//        c = db.rawQuery(sql, null);
//        c.moveToFirst();
//
//        Currency_Object curr = new Currency_Object();
//        curr.set_id(Integer.valueOf(c.getString(c.getColumnIndex("_id"))));
//        curr.setDescr(c.getString(c.getColumnIndex("descr")));
//        curr.setEng_descr(c.getString(c.getColumnIndex("eng_descr")));
//        curr.setShort_descr(c.getString(c.getColumnIndex("short_descr")));
//        curr.setSymbol(c.getString(c.getColumnIndex("symbol")));
//        curr.setDecimal(Integer.parseInt(c.getString(c.getColumnIndex("decimal"))));
//        curr.setIsdefault(Integer.parseInt(c.getString(c.getColumnIndex("isdefault"))));
//
//        c.close();
//        return curr;
//    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(tableUsers);
            db.execSQL(tableDecks);
            db.execSQL(tableCards);
            db.execSQL(tableSessions);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

        }
    }
}

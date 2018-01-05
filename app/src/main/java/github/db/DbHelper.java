package github.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import github.model.User;

public class DbHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "users";
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String USER_ADDRESS = "user_address";
    public static final String USER_CREATEDAT = "created_at";
    public static final String USER_UPDATEDAT = "updated_at";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       tableCreateStatements(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if Exists " + USER_TABLE_NAME );
        onCreate(db);
    }

    public void tableCreateStatements(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + USER_TABLE_NAME + "("
                    + USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_NAME + "TEXT,"
                    + USER_ADDRESS + "TEXT,"
                    + USER_CREATEDAT + "TEXT, " + getCurrentTimeStamp() + ","
                    + USER_UPDATEDAT + "TEXT,"  + getCurrentTimeStamp() +  ")" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                    + USER_TABLE_NAME
                    + "WHERE"
                    + USER_ID
                    + " EQUALS ", new String[]{userId + ""});

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_CREATEDAT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_UPDATEDAT)));
                return user;
            } else {
                throw new Resources.NotFoundException("USer with id " + USER_ID + "does not exists ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(cursor != null) {
                 cursor.close();
            }
        }
    }

    protected Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_NAME, user.getName());
            contentValues.put(USER_ADDRESS, user.getAddress());
            return db.insert(USER_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}

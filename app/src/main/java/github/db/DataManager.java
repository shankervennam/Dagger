package github.db;

import android.content.Context;

public class DataManager {
    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(Context context, DbHelper dbHelper, SharedPrefsHelper sharedPrefsHelper) {
        this.mContext = context;
        this.mDbHelper = dbHelper;
        this.mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void sample() {

    }

    public void saveAccessToken(String accessToken) {
        //mSharedPrefsHelper.put(SharedPrefs)
    }
}

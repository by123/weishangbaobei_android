package org.greenrobot.greendao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DatabaseOpenHelper extends SQLiteOpenHelper {
    private final Context context;
    private EncryptedHelper encryptedHelper;
    private boolean loadSQLCipherNativeLibs;
    private final String name;
    private final int version;

    public DatabaseOpenHelper(Context context2, String str, int i) {
        this(context2, str, (SQLiteDatabase.CursorFactory) null, i);
    }

    public DatabaseOpenHelper(Context context2, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context2, str, cursorFactory, i);
        this.loadSQLCipherNativeLibs = true;
        this.context = context2;
        this.name = str;
        this.version = i;
    }

    private EncryptedHelper checkEncryptedHelper() {
        if (this.encryptedHelper == null) {
            this.encryptedHelper = new EncryptedHelper(this, this.context, this.name, this.version, this.loadSQLCipherNativeLibs);
        }
        return this.encryptedHelper;
    }

    public Database getEncryptedReadableDb(String str) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.wrap(checkEncryptedHelper.getReadableDatabase(str));
    }

    public Database getEncryptedReadableDb(char[] cArr) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.wrap(checkEncryptedHelper.getReadableDatabase(cArr));
    }

    public Database getEncryptedWritableDb(String str) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.wrap(checkEncryptedHelper.getReadableDatabase(str));
    }

    public Database getEncryptedWritableDb(char[] cArr) {
        EncryptedHelper checkEncryptedHelper = checkEncryptedHelper();
        return checkEncryptedHelper.wrap(checkEncryptedHelper.getWritableDatabase(cArr));
    }

    public Database getReadableDb() {
        return wrap(getReadableDatabase());
    }

    public Database getWritableDb() {
        return wrap(getWritableDatabase());
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        onCreate(wrap(sQLiteDatabase));
    }

    public void onCreate(Database database) {
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        onOpen(wrap(sQLiteDatabase));
    }

    public void onOpen(Database database) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(wrap(sQLiteDatabase), i, i2);
    }

    public void onUpgrade(Database database, int i, int i2) {
    }

    public void setLoadSQLCipherNativeLibs(boolean z) {
        this.loadSQLCipherNativeLibs = z;
    }

    /* access modifiers changed from: protected */
    public Database wrap(SQLiteDatabase sQLiteDatabase) {
        return new StandardDatabase(sQLiteDatabase);
    }
}

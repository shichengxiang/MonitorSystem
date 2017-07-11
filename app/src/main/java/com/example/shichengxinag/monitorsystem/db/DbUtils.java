package com.example.shichengxinag.monitorsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

/**
 * Created by shichengxinag on 2017/7/11.
 */

public class DbUtils {
    private DatabaseOpenHelper mHelper;

    private void init(Context context) {

        mHelper = new DatabaseOpenHelper(context, "name", 1) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onCreate(Database db) {
                super.onCreate(db);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                super.onUpgrade(db, oldVersion, newVersion);
            }

            @Override
            public void onUpgrade(Database db, int oldVersion, int newVersion) {
                super.onUpgrade(db, oldVersion, newVersion);
            }

            @Override
            public void onOpen(SQLiteDatabase db) {
                super.onOpen(db);
            }

            @Override
            public void onOpen(Database db) {
                super.onOpen(db);
            }

            @Override
            public Database getEncryptedWritableDb(String password) {
                return super.getEncryptedWritableDb(password);
            }

            @Override
            public Database getEncryptedWritableDb(char[] password) {
                return super.getEncryptedWritableDb(password);
            }

            @Override
            public Database getEncryptedReadableDb(String password) {
                return super.getEncryptedReadableDb(password);
            }

            @Override
            public Database getEncryptedReadableDb(char[] password) {
                return super.getEncryptedReadableDb(password);
            }
        };
    }
    public void addEntity(){
    }
    public void close(){
        mHelper.close();
    }
}

package com.example.personaltools;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.icu.text.IDNA;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class BooksProvider extends ContentProvider {
    static final String PROVIDER_NAME =
            "com.example.personaltools.personalinformation";
    static final int MATCH_INFO = 1;
    static final int BOOK_ID = 2;
    static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/info");

    private static final UriMatcher uriMatcher;
    static {
        /*其中第一个参数authority:就是URI对应的authority
          path:就是我们在URI中 authority后的那一串
          code:表示匹配成功以后的返回值*/

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //这里的#代表匹配任意数字，另外还可以用*来匹配任意文本
        uriMatcher.addURI(PROVIDER_NAME, "info", MATCH_INFO);
    }

    //DB 相关
    SQLiteDatabase persDB;
    static final String DATABASE_NAME = "PersonInfo.db";//数据库名字 确定
    static final String DATABASE_TABLE = "info";// 表名
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table " + DATABASE_TABLE +
            " (_id integer primary key autoincrement, "
            + "name text not null, code text not null);";  //这行是加列名




private static class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS names");
        onCreate(db);
    }
}

//provider oncreat
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper((context));
        persDB = dbHelper.getReadableDatabase();//这里走db 的 onCreate 或者 onUpgrade 和内部类建立联系

        return (persDB == null) ? false : true;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

         switch (uriMatcher.match(uri)){
             case MATCH_INFO:
                 return "vnd.android.cursor.dir/vnd.manoel.books ";
             case BOOK_ID:
                 return "vnd.android.cursor.item/vnd.manoel.books ";
                 default:
                     throw new IllegalArgumentException("Unsupported URI:" + uri);
         }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

    switch (uriMatcher.match(uri)){
        case MATCH_INFO:
            Long rowID = persDB.insert(DATABASE_TABLE,null, values);
            if(rowID>0){
                Uri retUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
                return retUri;
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);

       }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteBuilder = new SQLiteQueryBuilder();
        sqLiteBuilder.setTables((DATABASE_TABLE));

          switch(uriMatcher.match(uri)){

              case MATCH_INFO:
                  sqLiteBuilder.setTables(DATABASE_TABLE);

                  break;

              default:
                  throw new IllegalArgumentException("Unknown URI " + uri);

          }


        Cursor cursor = sqLiteBuilder.query(persDB, projection, selection, selectionArgs, null, null, null);


        return cursor;
    }







    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        return 1;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {


        return 1;
    }
}

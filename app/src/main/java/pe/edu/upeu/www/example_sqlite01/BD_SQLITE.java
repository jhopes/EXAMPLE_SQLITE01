package pe.edu.upeu.www.example_sqlite01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JOEL on 06/05/2015.
 */
public class BD_SQLITE extends SQLiteOpenHelper {

    String sql="CREATE TABLE usuario(id_usuario INTEGER PRIMARY KEY AUTOINCREMENT , " +
            " campo1 TEXT , campo2 TEXT , campo3 TEXT )";

    public BD_SQLITE(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario ");
        db.execSQL(sql);
    }

}

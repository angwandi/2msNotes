package app.a2ms.a2msnotes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Basic database class for the application
 *and the only class that should  use this is [AppProvider].
 */
private const val TAG = "AppDatabase"

private const val DATABASE_NAME = "Notes2ms.db"
private const val DATABASE_VERSION = 1

class AppDatabase private constructor(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sSQL: String = """CREATE TABLE ${NotesContract.TABLE_NAME}(
    ${NotesContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL,
    ${NotesContract.Columns.NOTE_TITLE} TEXT,
    ${NotesContract.Columns.NOTE_DESCRIPTION} TEXT,
    ${NotesContract.Columns.NOTE_SORT_ORDER} INTEGER);""".replaceIndent(" ")
        db?.execSQL(sSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            1 -> {
                //upgrade logic from version 1
            }
            else -> throw IllegalArgumentException("onUpgrade() with unknown newVersion: $newVersion")
        }
    }
}
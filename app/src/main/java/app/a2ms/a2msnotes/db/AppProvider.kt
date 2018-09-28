package app.a2ms.a2msnotes.db

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

const val CONTENT_AUTHORITY = "app.a2ms.a2msnotes.provider"

private const val NOTES = 100
private const val NOTES_ID = 101

//private const val TIMINGS = 200
//private const val TIMINGS_ID = 201
//
//private const val NOTE_DURATIONS = 400
//private const val NOTE_DURATIONS_ID = 401

val CONTENT_AUTHORITY_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

class AppProvider : ContentProvider() {
    //variable to hold UriMatcher
    private val uriMatcher by lazy { buildUriMatch() }

    //create a matcher function to use the UriMatcher class
    private fun buildUriMatch(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        //e.g content//app.a2ms.a2msnotes.provider/Tasks
        matcher.addURI(CONTENT_AUTHORITY, NotesContract.TABLE_NAME, NOTES)
        //e.g content//app.a2ms.a2msnotes.provider/Tasks/8
        matcher.addURI(CONTENT_AUTHORITY, "${NotesContract.TABLE_NAME}/#", NOTES_ID)
        return matcher
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(uri: Uri): String? {
        val match = uriMatcher.match(uri)
        return when (match) {
            NOTES -> NotesContract.CONTENT_TYPE
            NOTES_ID -> NotesContract.CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("unknown Uri: $uri")
        }
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val match = uriMatcher.match(uri)
        val queryBuilder = SQLiteQueryBuilder()
        when (match) {
            NOTES -> queryBuilder.tables = NotesContract.TABLE_NAME
            NOTES_ID -> {
                queryBuilder.tables = NotesContract.TABLE_NAME
                val noteId: Long = NotesContract.getId(uri)
                queryBuilder.appendWhere("${NotesContract.Columns.ID} = ")
                queryBuilder.appendWhereEscapeString("$noteId")
            }
            else -> throw IllegalArgumentException("unknown URI: $uri")
        }
        val db = AppDatabase.getInstance(context).readableDatabase
        val cursor = queryBuilder.query(db, projection,
                selection, selectionArgs, null, null, sortOrder)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
package app.a2ms.a2msnotes.db

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

object NotesContract {
    internal const val TABLE_NAME = "Notes"
    /**
     * The URI to access the Tasks table
     * */
    val CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)

    const val CONTENT_TYPE = "vnd.android.cursor.dir/vdn.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"


    object Columns {
        const val ID = BaseColumns._ID
        const val NOTE_TITLE = "Title"
        const val NOTE_DESCRIPTION = "Description"
        const val NOTE_SORT_ORDER = "SortOrder"
    }

    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}

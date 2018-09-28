package app.a2ms.a2msnotes.db

import android.provider.BaseColumns

object NotesContract {
    internal const val TABLE_NAME = "Notes"

    object Columns {
        const val ID = BaseColumns._ID
        const val NOTE_TITLE = "Title"
        const val NOTE_DESCRIPTION = "Description"
        const val NOTE_SORT_ORDER = "SortOrder"
    }
}
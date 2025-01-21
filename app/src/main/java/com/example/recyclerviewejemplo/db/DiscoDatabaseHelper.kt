import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.recyclerviewejemplo.modelo.Disco

class DiscoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "disco.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "disco"
        const val COLUMNA_ID = "id"
        const val COLUMNA_NOMBRE = "nombre"
        const val COLUMNA_PORTADA = "portada"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE TEXT,
                $COLUMNA_PORTADA INTEGER NOT NULL
            )
        """
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert a new Disco
    fun insertDisco(disco: Disco): Long {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMNA_NOMBRE, disco.nombre)
        contentValues.put(COLUMNA_PORTADA, disco.portada)
        return db.insert(TABLE_NAME, null, contentValues)
    }

    // Retrieve all Discos
    fun getAllDiscos(): ArrayList<Disco> {
        val discos = ArrayList<Disco>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME,
            null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val portada = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMNA_PORTADA))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_NOMBRE))
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMNA_ID))
                discos.add(Disco(nombre, portada, id))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return discos
    }

    // Update a Disco by ID
    fun updateDisco(disco: Disco): Int {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMNA_NOMBRE, disco.nombre)
        contentValues.put(COLUMNA_PORTADA, disco.portada)
        return db.update(TABLE_NAME, contentValues, "$COLUMNA_ID = ?", arrayOf(disco.id.toString()))
    }

    // Delete a Disco by ID
    fun deleteDisco(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMNA_ID = ?", arrayOf(id.toString()))
    }
}

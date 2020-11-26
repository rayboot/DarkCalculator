package com.justforfun.calc.memo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {
    init {
        instance = this
    }

    companion object {
        val DB_NAME = "memo.db"
        val DB_VERSION = 1
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: DatabaseOpenHelper(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MemoTable.TABLE_NAME, true,
                MemoTable.ID to INTEGER + PRIMARY_KEY + UNIQUE,
                MemoTable.DATE to INTEGER,
                MemoTable.CONTENT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.dropTable(MemoTable.TABLE_NAME, true)
//        onCreate(db)
    }
}


fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T {
                return parser(columns)
            }
        })

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun SelectQueryBuilder.byId(id: Long) = whereSimple("_id = ?", id.toString())
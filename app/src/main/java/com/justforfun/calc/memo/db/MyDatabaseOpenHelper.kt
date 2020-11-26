package com.justforfun.calc.memo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class MyDatabaseOpenHelper private constructor(ctx: Context) :
        ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {
    //初始化
    init {
        instance = this
    }

    //单例模式
    companion object {
        private var instance: MyDatabaseOpenHelper? = null
        private val DB_VERSION = 1
        private val DB_NAME = "memo.db"

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: MyDatabaseOpenHelper(ctx.applicationContext)
    }

    //创建数据库时 做一些事情 比如创建一些默认的表
    override fun onCreate(db: SQLiteDatabase) {
    }

    //数据库版本更新 版本发生改变时做一些事情 比如删除所有表
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
//        db.dropTable("User", true)
    }

    // Access property for Context
    val Context.database: MyDatabaseOpenHelper
        get() = MyDatabaseOpenHelper.getInstance(this)
}
package com.justforfun.calc.memo.db

import com.justforfun.calc.App
import org.jetbrains.anko.db.*

class MemoDbProvider {
    //数据库操作
    private val database = MyDatabaseOpenHelper.getInstance(App.instance())

    //初始化 调用createTable建表
    init {
        createTable()
    }

    //单例模式
    companion object {
        val instance: MemoDbProvider by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MemoDbProvider() }
    }


    //建表
    private fun createTable() {
        database.use {
            //检查表是否存在 不存在则创建表
            createTable(MemoTable.TABLE_NAME, true,
                    MemoTable.ID to INTEGER + PRIMARY_KEY + UNIQUE,
                    MemoTable.DATE to INTEGER,
                    MemoTable.CONTENT to TEXT)
        }
    }


    //增加数据
    fun insertData(id: Long, content: String, date: Long) {
        database.use {
            if (id == 0L) {
                insert(MemoTable.TABLE_NAME, MemoTable.CONTENT to content, MemoTable.DATE to date)
            } else {
                update(
                        MemoTable.TABLE_NAME,
                        MemoTable.CONTENT to content, MemoTable.DATE to date
                ).whereArgs("${MemoTable.ID} = {id}", "id" to id)
                        .exec()
            }
        }
    }

    fun insertData(data: Memo) {
        insertData(data.id, data.content, data.date)
    }

    fun deleteData(id: Long) {
        database.use {
            delete(MemoTable.TABLE_NAME, "${MemoTable.ID} = {id}", "id" to id)
        }
    }


    //查询所有数据
    fun queryAll(): ArrayList<Memo> {
        val list = ArrayList<Memo>()
        database.use {
            select(MemoTable.TABLE_NAME).exec {
                while (moveToNext()) {
                    val id = getLong(getColumnIndex(MemoTable.ID))
                    val content = getString(getColumnIndex(MemoTable.CONTENT))
                    val date = getLong(getColumnIndex(MemoTable.DATE))
                    list.add(Memo(id, content, date))
                }
            }
        }
        //倒序
        list.reverse()
        return list
    }

    //清除所有数据
    fun clearAll() {
        database.use {
            delete(MemoTable.TABLE_NAME)
        }
    }
}
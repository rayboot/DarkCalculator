package com.justforfun.calc.memo.db

data class Memo(val map: MutableMap<String, Any?>) {
    var _id: Long by map
    var date: Long by map
    var content: String by map

    constructor() : this(HashMap()) {
    }

    constructor(id: Long, date: Long, content: String) : this(HashMap()) {
        this._id = id
        this.date = date
        this.content = content
    }

}
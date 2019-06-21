package com.ethan.core.api.network

class ApiException constructor(code: Int, msg: String) : RuntimeException() {
    var code: Int
    var msg: String

    init {
        this.code = code
        this.msg = msg
    }

}
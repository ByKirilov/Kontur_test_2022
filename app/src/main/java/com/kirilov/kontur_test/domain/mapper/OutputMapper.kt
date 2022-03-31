package com.kirilov.kontur_test.domain.mapper

interface OutputMapper<in From, out To> {
    fun transformFromDomain(item: From): To
}
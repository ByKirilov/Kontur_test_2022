package com.kirilov.kontur_test.domain.mapper

interface InputMapper<in From, out To> {
    fun transformToDomain(item: From): To
}
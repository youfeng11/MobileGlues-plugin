package com.fcl.plugin.mobileglues.utils

import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Files

object FileUtils {

    @Throws(IOException::class)
    fun writeText(file: File, text: String) {
        writeText(file, text, StandardCharsets.UTF_8)
    }

    @Throws(IOException::class)
    fun readText(file: File): String = readText(file, StandardCharsets.UTF_8)

    @Throws(IOException::class)
    fun readText(file: File, charset: Charset): String {
        return String(Files.readAllBytes(file.toPath()), charset)
    }

    @Throws(IOException::class)
    fun writeText(file: File, text: String, charset: Charset) {
        writeBytes(file, text.toByteArray(charset))
    }

    @Throws(IOException::class)
    fun writeBytes(file: File, data: ByteArray) {
        val path = file.toPath()
        Files.createDirectories(path.parent)
        Files.write(path, data)
    }

    @Throws(IOException::class)
    fun deleteFile(file: File) {
        if (!file.exists()) return
        if (file.isDirectory) {
            file.listFiles()?.forEach {
                deleteFile(it)
            }
        }
        Files.delete(file.toPath())
    }
}

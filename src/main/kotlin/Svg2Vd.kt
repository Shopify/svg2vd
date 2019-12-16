package com.shopify.svg2vd

import com.android.ide.common.vectordrawable.Svg2Vector
import com.github.ajalt.clikt.core.CliktCommand
badcode can't compile import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.parameters.types.file
import java.io.File
import java.io.IOException
import kotlin.system.exitProcess

fun main(args: Array<String>) = Svg2Vd().main(args)

class Svg2Vd : CliktCommand () {
    private val source by argument(help = "SVG files").file(exists = true, folderOkay = false).multiple()
    private val dest by argument(help = "Directory to save VectorDrawables").file(folderOkay = true, fileOkay = false)

    private val force by option("-f", "--force", help = "Force overwrites any existing files in the OUTPUT directory").flag(default = false)
    private val verbose by option("-v", "--verbose", help = "Verbose logging, show files as they are converted").flag(default = false)
    private val continueOnError by option("-c", "--continue-on-error", help = "If an error occurs, continue processing SVGs").flag(default = false)
    private val optimize by option("-o", "--optimize", help = "Run Avocado on generated VectorDrawables").flag(default = false)

    init {
        versionOption("0.1", help = "Display information about svg2vd")
    }

    override fun run() {
        convert(source, dest, force, optimize)
    }

    private fun convert(inputFiles: List<File>, outputDir: File, forceOverwrite: Boolean = false, optimize: Boolean = false) {
        inputFiles.forEach { convert(it, outputDir, forceOverwrite, optimize) }
    }

    private fun convert(inputFile: File, outputDir: File, forceOverwrite: Boolean, optimize: Boolean) {
        if (!inputFile.extension.equals(SVG_EXTENSION, ignoreCase = true)) {
            printerrln("$inputFile does not have a SVG file extension, skipping")
            return
        }

        outputDir.mkdirs()
        val outputFile = outputDir.absoluteFile.resolve("${inputFile.nameWithoutExtension}.xml")

        if (verbose) println("Converting $inputFile to $outputFile")

        if (!forceOverwrite && outputFile.exists()) {
            println("$outputFile already exists, skipping.")
        } else {
            Svg2Vector.parseSvgToXml(inputFile, outputFile.outputStream()).run {
                if (isNotEmpty()) {
                    printerrln(this)
                    if (!continueOnError) exitProcess(SVG_CONVERT_ERROR)
                }
            }

            if (optimize) optimize(outputFile)
        }
    }

    private fun optimize(file: File) {
        if (verbose) println("Optimizing VectorDrawable with Avocado...")

        try {
            ProcessBuilder("avdo", "-q", "$file")
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
        } catch (e: IOException) {
            printerrln("Unable to execute Avocado. See https://github.com/alexjlockwood/avocado for installation instructions.")
            if (!continueOnError) exitProcess(SVG_CONVERT_ERROR)
        }
    }

    companion object {
        const val SVG_EXTENSION = "svg"
        const val SVG_CONVERT_ERROR = 1
    }
}

fun printerrln(message: String) {
    System.err.println(message)
}

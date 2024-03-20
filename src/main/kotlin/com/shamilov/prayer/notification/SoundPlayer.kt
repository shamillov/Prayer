package com.shamilov.prayer.notification

import java.io.BufferedInputStream
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine
import kotlin.concurrent.thread

/**
 * @author Shamilov on 08.03.2024
 */
object SoundPlayer {
    private const val EXTERNAL_BUFFER_SIZE = 128000

    @Volatile
    private var isPlaying = false

    fun play() {
        if (isPlaying) return

        thread {
            isPlaying = true

            val audioInputStream: AudioInputStream
            try {
                val soundFile = javaClass.classLoader.getResourceAsStream("bismillah.wav") ?: return@thread
                val bufferedInputStream = BufferedInputStream(soundFile)
                audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)
            } catch (e: Throwable) {
                e.printStackTrace()
                return@thread
            }

            val line: SourceDataLine?

            val info = DataLine.Info(SourceDataLine::class.java, audioInputStream.format)

            try {
                line = AudioSystem.getLine(info) as SourceDataLine
                line.open(audioInputStream.format)
            } catch (e: Throwable) {
                e.printStackTrace()
                return@thread
            }

            line.start()
            var nBytesRead = 0
            val abData = ByteArray(EXTERNAL_BUFFER_SIZE)
            while (nBytesRead != -1) {
                try {
                    nBytesRead = audioInputStream.read(abData, 0, abData.size)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    return@thread
                }
                if (nBytesRead >= 0) line.write(abData, 0, nBytesRead)
            }
            line.drain()
            line.close()

            isPlaying = false
        }
    }
}

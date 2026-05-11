package com.github.util.resource

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import com.github.util.R
import java.io.IOException

class SoundPlay {
    companion object {
        private val TAG = javaClass.simpleName

        private var soundMediaPlayer: MediaPlayer? = null
        val INSTRUCTION_SEND = 1
        val INSTRUCTION_CHANGE = 2

        var uri: Uri? = null
        var isPlayingRecording = false

        /**
         * 사운드 파일 재생 함수
         * */
        fun startSound(context: Context, _type: Int) {
            val descriptor: AssetFileDescriptor = when (_type) {
                INSTRUCTION_SEND -> context.resources?.openRawResourceFd(R.raw.instruction_info_send)
                    ?: return

                INSTRUCTION_CHANGE -> context.resources?.openRawResourceFd(R.raw.instruction_info_change)
                    ?: return

                else -> return
            }
            try {
                stopSound()

                soundMediaPlayer = MediaPlayer().apply {
                    setDataSource(
                        descriptor.fileDescriptor,
                        descriptor.startOffset,
                        descriptor.length
                    )
                    // 볼륨 셋팅
                    // setVolume(5f, 5f)
                    prepare()
                    start()
                }
            } catch (e: IOException) {
                stopSound()
                Log.e(TAG, "startSound IOException1 예외처리: ${e.message}")
            } finally {
                try {
                    descriptor.close()
                } catch (e: IOException) {
                    Log.e(TAG, "startSound IOException2 예외처리: ${e.message}")
                }

            }
        }

        /**
         * 사운드 파일 정지 함수
         * */
        fun stopSound() {
            soundMediaPlayer = soundMediaPlayer?.let {
                it.stop()
                it.release()
                null
            }
        }

        /**
         * 녹취 재생/정지 함수
         * */
        fun requestRecording(_context: Context, _uri: Uri) {
            if(isPlayingRecording) {
                if(uri == _uri) {
                    // 다른 녹취록 재생
                    playRecording(_context, _uri)
                } else {
                    // 녹취록 정지
                    stopRecording()
                }
            } else {
                // 녹취록 재생
                playRecording(_context, _uri)
            }

        }

        /**
         * 녹취 재생 함수
         * */
        fun playRecording(_context: Context, _uri: Uri) {
            stopSound()
            soundMediaPlayer = MediaPlayer.create(_context, _uri).apply {
                start()
                setOnCompletionListener { stopRecording() }
            }
            uri = _uri
            isPlayingRecording = true
        }

        /**
         * 녹취 정지 함수
         * */
        fun stopRecording() {
            soundMediaPlayer = soundMediaPlayer?.let {
                it.stop()
                it.release()
                null
            }
            uri = null
            isPlayingRecording = false
        }
    }

}
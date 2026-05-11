package com.github.util.resource

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Base64
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.createBitmap
import com.caverock.androidsvg.SVG
import java.io.ByteArrayOutputStream


fun Context.getBase64FromDrawable(drawableResId: Int): String {
    val bitmap = BitmapFactory.decodeResource(resources, drawableResId)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun Context.getBase64FromSvgDrawable(drawableResId: Int, width: Int = 24, height: Int = 24): String {
    val inputStream = resources.openRawResource(drawableResId)
    val svg = SVG.getFromInputStream(inputStream)

    // 원하는 크기로 비트맵 생성 (예: 512x512)
    val bitmap = createBitmap(width, height)
    val canvas = Canvas(bitmap)

    // 뷰포트 설정 및 렌더링
    svg.setDocumentWidth(width.toFloat())
    svg.setDocumentHeight(height.toFloat())
    svg.renderToCanvas(canvas)

    // 비트맵 → Base64
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun Context.getBase64FromVectorDrawable(drawableResId: Int): String {
    val drawable = AppCompatResources.getDrawable(this, drawableResId) ?: return ""

    // 원하는 출력 크기 (원본 drawable 크기에 맞춰도 되고, 고정값도 가능)
    val width = drawable.intrinsicWidth
    val height = drawable.intrinsicHeight

    // 벡터 drawable을 비트맵으로 렌더링
    val bitmap = createBitmap(width, height)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    // 비트맵 → Base64
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun Context.getBase64FromVectorDrawableWithShadow(drawableResId: Int): String {
    val drawable = AppCompatResources.getDrawable(this, drawableResId) ?: return ""

    val width = drawable.intrinsicWidth
    val height = drawable.intrinsicHeight
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // 그림자 효과용 페인트
    val shadowPaint = Paint().apply {
        color = Color.BLACK
        alpha = 100
        setShadowLayer(8f, 4f, 4f, Color.BLACK)
        isAntiAlias = true
    }

    // 그림자 비트맵
    val shadowBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val shadowCanvas = Canvas(shadowBitmap)
    drawable.setBounds(0, 0, width, height)
    drawable.draw(shadowCanvas)

    // 원래 drawable을 그림자 처리된 비트맵으로 다시 그림
    canvas.drawBitmap(shadowBitmap, 0f, 0f, shadowPaint)

    // 본 drawable도 덮어쓰기
    drawable.draw(canvas)

    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}
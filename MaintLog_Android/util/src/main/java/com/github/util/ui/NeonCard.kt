package com.github.util.ui//package com.github.util.ui
//
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.CornerRadius
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.Stroke
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun NeonCard(modifier: Modifier = Modifier) {
//    val shimmerTranslate = rememberInfiniteTransition().animateFloat(
//        initialValue = -1f,
//        targetValue = 2f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(2000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        )
//    )
//
//    val shimmerBrush = Brush.linearGradient(
//        colors = listOf(Color.Transparent, Color.Gray.copy(alpha = 0.5f), Color.Transparent),
//        start = Offset(shimmerTranslate.value * 600f, 0f),
//        end = Offset((shimmerTranslate.value + 1f) * 600f, 800f)
//    )
//
//    Box(
//        modifier
//            .size(200.dp, 300.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(
//                Brush.linearGradient(
//                    colors = listOf(Color(0xFF666666), Color(0xFF222222), Color(0xFF666666)),
//                    start = Offset.Zero,
//                    end = Offset(400f, 400f)
//                )
//            )
//            .drawBehind {
//                // Glow Stroke
//                drawRoundRect(
//                    brush = Brush.linearGradient(colors = listOf(Color(0xFFFFE97D), Color(0xFFFFFACD))),
//                    style = Stroke(width = 6.dp.toPx()),
//                    cornerRadius = CornerRadius(16.dp.toPx())
//                )
//                // Shimmer overlay
//                drawRoundRect(
//                    brush = shimmerBrush,
//                    cornerRadius = CornerRadius(16.dp.toPx())
//                )
//            }
//    )
//}

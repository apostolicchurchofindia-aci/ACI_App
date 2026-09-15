package com.aci.core.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aci.core.ui.theme.ACIShapes
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme
import com.aci.core.ui.theme.ACIColors

@Composable
fun shimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "shimmer")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "shimmerTranslation"
        )

        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
    }
}

@Composable
fun ACIShimmer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = shimmerBrush(),
                shape = ACIShapes.roundedLarge
            )
    )
}

@Preview(showBackground = true, name = "Light Shimmer")
@Composable
private fun ACIShimmerLightPreview() {
    ACITheme(useDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ACISpacing.md),
            verticalArrangement = Arrangement.spacedBy(ACISpacing.md)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.md)
            ) {
                ACIShimmer(modifier = Modifier.size(64.dp).then(Modifier.background(Color.Transparent, CircleShape)))
                Column(
                    verticalArrangement = Arrangement.spacedBy(ACISpacing.sm),
                    modifier = Modifier.weight(1f)
                ) {
                    ACIShimmer(modifier = Modifier.fillMaxWidth().height(20.dp))
                    ACIShimmer(modifier = Modifier.fillMaxWidth().height(16.dp))
                }
            }
            ACIShimmer(modifier = Modifier.fillMaxWidth().height(120.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(ACISpacing.sm)
            ) {
                ACIShimmer(modifier = Modifier.height(32.dp).weight(1f))
                ACIShimmer(modifier = Modifier.height(32.dp).weight(1f))
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark Shimmer")
@Composable
private fun ACIShimmerDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ACISpacing.md),
                verticalArrangement = Arrangement.spacedBy(ACISpacing.md)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(ACISpacing.md)
                ) {
                    ACIShimmer(modifier = Modifier.size(64.dp).then(Modifier.background(Color.Transparent, CircleShape)))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(ACISpacing.sm),
                        modifier = Modifier.weight(1f)
                    ) {
                        ACIShimmer(modifier = Modifier.fillMaxWidth().height(20.dp))
                        ACIShimmer(modifier = Modifier.fillMaxWidth().height(16.dp))
                    }
                }
                ACIShimmer(modifier = Modifier.fillMaxWidth().height(120.dp))
            }
        }
    }
}

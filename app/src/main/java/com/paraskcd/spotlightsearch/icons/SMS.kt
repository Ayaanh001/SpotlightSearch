package com.paraskcd.spotlightsearch.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SMS: ImageVector
    get() {
        if (_Sms != null) return _Sms!!

        _Sms = ImageVector.Builder(
            name = "Sms",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21f, 15f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
                horizontalLineTo(7f)
                lineToRelative(-4f, 4f)
                verticalLineTo(5f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
                horizontalLineToRelative(14f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
                close()
                moveToRelative(-8f, -7f)
                horizontalLineTo(7f)
                moveToRelative(10f, 4f)
                horizontalLineTo(7f)
            }
        }.build()

        return _Sms!!
    }

private var _Sms: ImageVector? = null

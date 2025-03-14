package gg.happy.linebutton.render

import gg.happy.linebutton.LineButtonClient
import net.minecraft.client.gui.DrawContext
import kotlin.math.pow
import kotlin.math.round

abstract class RenderAdapter
{
    val conf = LineButtonClient.conf

    private var lastSelected = false

    private var last = 0L

    fun update(isSelected: Boolean): Double
    {
        val t = System.currentTimeMillis()
        val d = conf.line.duration

        val flag = isSelected && (SliderRender.dragging == null || SliderRender.dragging === this)

        val result =
            if (t - last >= d)
                if (lastSelected) 1.0
                else 0.0
            else
                if (lastSelected) ((t - last) / d.toDouble() - 1).cubicPower() + 1
                else (1 - (t - last) / d.toDouble()).cubicPower()

        if (flag xor lastSelected)
        {
            last =
                if (t - last >= d) t
                else round(t - d + (d.cubicPower() + (t - last - d).cubicPower()).toDouble().pow(1.0 / 3.0)).toLong()
            lastSelected = flag
        }

        return result
    }

    fun drawShadow(context: DrawContext, x1: Int, y1: Int, x2: Int, y2: Int) =
        context.fill(x1 + 1, y1 + 1, x2 + 1, y2 + 1, conf.line.shadowColor)

    fun getTextColor(isActive: Boolean, isSelected: Boolean): Int =
        when
        {
            !isActive -> conf.text.disabledColor
            isSelected && (SliderRender.dragging == null || SliderRender.dragging === this) -> conf.text.hoveredColor
            else -> conf.text.color
        }

    private fun Int.cubicPower() = this * this * this
    private fun Long.cubicPower() = this * this * this
    private fun Double.cubicPower() = this * this * this
}
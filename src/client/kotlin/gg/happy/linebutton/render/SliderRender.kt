package gg.happy.linebutton.render

import net.minecraft.client.gui.DrawContext
import kotlin.math.round

class SliderRender : RenderAdapter()
{
    companion object
    {
        var dragging: SliderRender? = null

        @JvmStatic
        fun onSetScreen()
        {
            dragging = null
        }
    }

    fun render(
        context: DrawContext,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        isActive: Boolean,
        isSelected: Boolean,
        value: Double
    )
    {
        val a = update(isSelected)
        val b = (width * a * 0.5).toInt()

        val midX = x + width / 2
        if (b != 0)
            draw(
                context,
                midX - b,
                y + height - 1,
                midX + b,
                y + height,
                round(x + width * value).toInt()
            )
    }

    private fun draw(context: DrawContext, x1: Int, y1: Int, x2: Int, y2: Int, x3: Int)
    {
        if (x3 > x2)
            context.fill(x1, y1, x2, y2, conf.line.color)
        else if (x3 < x1)
            context.fill(x1, y1, x2, y2, conf.line.sliderBackgroundColor)
        else
        {
            context.fill(x1, y1, x3, y2, conf.line.color)
            context.fill(x3, y1, x2, y2, conf.line.sliderBackgroundColor)
        }
        drawShadow(context, x1, y1, x2, y2)
    }

    fun onClick()
    {
        dragging = this
    }

    fun onRelease()
    {
        dragging = null
    }
}
package gg.happy.linebutton.render

import net.minecraft.client.gui.DrawContext

class ButtonRender : RenderAdapter()
{
    fun render(
        context: DrawContext,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        isActive: Boolean,
        isSelected: Boolean
    )
    {
        val a = update(isSelected)
        val b = (width * a * 0.5 * getMultiple(isActive)).toInt()

        val midX = x + width / 2
        if (b != 0)
            draw(
                context,
                midX - b,
                y + height - 1,
                midX + b,
                y + height,
                isActive
            )
    }

    private fun getMultiple(isActive: Boolean) =
        if (isActive) conf.line.buttonMultiple
        else conf.line.disableMultiple

    private fun draw(context: DrawContext, x1: Int, y1: Int, x2: Int, y2: Int, isActive: Boolean)
    {
        context.fill(x1, y1, x2, y2, if (isActive) conf.line.color else conf.line.disabledColor)
        drawShadow(context, x1, y1, x2, y2)
    }
}
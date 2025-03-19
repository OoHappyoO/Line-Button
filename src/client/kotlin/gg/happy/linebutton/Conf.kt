package gg.happy.linebutton

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry
import kotlin.jvm.Throws

@Config(name = "line-button")
class Conf : ConfigData
{
    @Throws(ConfigData.ValidationException::class)
    override fun validatePostLoad()
    {
        //TODO
    }

    @ConfigEntry.Gui.CollapsibleObject
    var line = Line()

    @ConfigEntry.Gui.CollapsibleObject
    var text = Text()


    class Line
    {
        var duration = 400

        var buttonMultiplier = 1.0

        var disableMultiplier = 0.5

        @ConfigEntry.ColorPicker(allowAlpha = true)
        var color = 0xFFFBFBFB.toInt()

        @ConfigEntry.ColorPicker(allowAlpha = true)
        var shadowColor = 0x7F3E3E3E

        @ConfigEntry.ColorPicker(allowAlpha = true)
        var sliderBackgroundColor = 0xFF7F7F7F.toInt()

        @ConfigEntry.ColorPicker(allowAlpha = true)
        var disabledColor = 0xFFA0A0A0.toInt()
    }

    class Text
    {
        @ConfigEntry.ColorPicker(allowAlpha = true)
        var color = 0xFFFBFBFB.toInt()

        @ConfigEntry.ColorPicker(allowAlpha = true)
        var disabledColor = 0xFFA0A0A0.toInt()

        @ConfigEntry.ColorPicker(allowAlpha = true)
        var hoveredColor = 0xFFFFFFA0.toInt()
    }
}
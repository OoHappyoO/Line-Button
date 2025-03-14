package gg.happy.linebutton

import net.fabricmc.api.ClientModInitializer
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import net.minecraft.client.MinecraftClient

object LineButtonClient : ClientModInitializer
{
    lateinit var conf: Conf
        private set

    override fun onInitializeClient()
    {
        AutoConfig.register(Conf::class.java, ::JanksonConfigSerializer)
        conf = AutoConfig.getConfigHolder(Conf::class.java).config
    }
}
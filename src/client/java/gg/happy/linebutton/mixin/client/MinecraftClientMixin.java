package gg.happy.linebutton.mixin.client;

import gg.happy.linebutton.render.SliderRender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin
{
    @Inject(method = "setScreen", at = @At("HEAD"))
    public void setScreen(Screen screen, CallbackInfo ci)
    {
        SliderRender.onSetScreen();
    }
}

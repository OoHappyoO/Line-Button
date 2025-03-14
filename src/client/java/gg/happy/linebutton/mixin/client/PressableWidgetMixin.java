package gg.happy.linebutton.mixin.client;

import gg.happy.linebutton.render.ButtonRender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PressableWidget.class)
public abstract class PressableWidgetMixin extends ClickableWidget
{
    public PressableWidgetMixin(int i, int j, int k, int l, Text text)
    {
        super(i, j, k, l, text);
    }

    @Unique
    ButtonRender render = new ButtonRender();

    @Unique
    boolean flag = false;

    @Shadow
    public abstract void drawMessage(DrawContext context, TextRenderer textRenderer, int color);

    @Inject(method = "renderWidget", at = @At("HEAD"), cancellable = true)
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if(flag)
        {
            setFocused(false);
            flag = false;
        }
        render.render(context, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.active, this.isSelected());
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        drawMessage(context, minecraftClient.textRenderer, render.getTextColor(this.active, this.isSelected()));
        ci.cancel();
    }

    @Inject(method = "onClick", at = @At("HEAD"))
    public void onClick(double mouseX, double mouseY, CallbackInfo ci)
    {
        flag = true;
    }
}

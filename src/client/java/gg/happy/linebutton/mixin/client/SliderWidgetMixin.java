package gg.happy.linebutton.mixin.client;

import gg.happy.linebutton.render.SliderRender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SliderWidget.class)
public abstract class SliderWidgetMixin extends ClickableWidget
{
    public SliderWidgetMixin(int x, int y, int width, int height, Text text, double value)
    {
        super(x, y, width, height, text);
    }

    @Unique
    SliderRender render = new SliderRender();

    @Shadow
    protected double value;

    @Shadow
    protected abstract void setValue(double value);

    @Override
    public boolean isSelected()
    {
        return this.isHovered() || this.isFocused();
    }

    @Inject(method = "renderWidget", at = @At("HEAD"), cancellable = true)
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        render.render(context, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.active, this.isSelected(), this.value);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        this.drawScrollableText(context, minecraftClient.textRenderer, 2, render.getTextColor(this.active, this.isSelected()));
        ci.cancel();
    }

    @Inject(method = "setValueFromMouse", at = @At("HEAD"), cancellable = true)
    public void setValueFromMouse(double mouseX, CallbackInfo ci)
    {
        this.setValue((mouseX - this.getX()) / (double) this.width);
        ci.cancel();
    }

    @Inject(method = "onClick", at = @At("HEAD"))
    public void onClick(double mouseX, double mouseY, CallbackInfo ci)
    {
        render.onClick();
        super.playDownSound(MinecraftClient.getInstance().getSoundManager());
    }

    @Inject(method = "onRelease", at = @At("HEAD"))
    public void onRelease(double mouseX, double mouseY, CallbackInfo ci)
    {
        setFocused(false);
    }

    @Inject(method = "setFocused", at = @At("HEAD"))
    public void setFocused(boolean focused, CallbackInfo ci)
    {
        if (!focused)
            render.onRelease();
    }
}

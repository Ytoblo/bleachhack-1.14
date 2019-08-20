package bleach.hack.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import bleach.hack.module.ModuleManager;
import bleach.hack.module.mods.Nametags;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M> {
	
	protected MixinEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1);
	}

	@Inject(at = @At("HEAD"), method = "method_4041(Lnet/minecraft/entity/LivingEntity;DDD)V")
	public void render(T livingEntity_1, double double_1, double double_2, double double_3, CallbackInfo info) {
		if(ModuleManager.getModule(Nametags.class).isToggled()) {
			((Nametags) ModuleManager.getModule(Nametags.class)).drawNametags(livingEntity_1);
		}
	}
}
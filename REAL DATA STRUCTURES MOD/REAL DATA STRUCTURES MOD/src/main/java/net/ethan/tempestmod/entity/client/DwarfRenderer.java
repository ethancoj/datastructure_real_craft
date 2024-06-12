package net.ethan.tempestmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ethan.tempestmod.TempestMod;
import net.ethan.tempestmod.entity.custom.DwarfEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DwarfRenderer extends MobRenderer<DwarfEntity, DwarfModel<DwarfEntity>> {
    public DwarfRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DwarfModel<>(pContext.bakeLayer(ModModelLayers.DWARF_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity dwarfEntity) {
        return new ResourceLocation(TempestMod.MOD_ID,"textures/entity/dwarf.png");
    }

    @Override
    public void render(DwarfEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {



        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}

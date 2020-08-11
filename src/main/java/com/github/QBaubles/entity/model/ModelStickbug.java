package com.github.QBaubles.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
//Made with Blockbench 3.6.5
//Exported for Minecraft version 1.12
//Paste this class into your mod and generate all required imports
import net.minecraft.util.math.MathHelper;


public class ModelStickbug extends ModelBase {
	private final ModelRenderer lf_leg;
	private final ModelRenderer lm_leg;
	private final ModelRenderer lr_leg;
	private final ModelRenderer rf_leg;
	private final ModelRenderer rm_leg;
	private final ModelRenderer rr_leg;
	private final ModelRenderer bb_main;

	public ModelStickbug() {
		textureWidth = 32;
		textureHeight = 32;

		lf_leg = new ModelRenderer(this);
		lf_leg.setRotationPoint(0.0F, 17.0F, -4.0F);
		lf_leg.cubeList.add(new ModelBox(lf_leg, 0, 0, 0.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		lm_leg = new ModelRenderer(this);
		lm_leg.setRotationPoint(0.0F, 17.0F, 1.0F);
		lm_leg.cubeList.add(new ModelBox(lm_leg, 0, 0, 0.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		lr_leg = new ModelRenderer(this);
		lr_leg.setRotationPoint(0.0F, 17.0F, 6.0F);
		lr_leg.cubeList.add(new ModelBox(lr_leg, 0, 0, 0.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		rf_leg = new ModelRenderer(this);
		rf_leg.setRotationPoint(-2.0F, 17.0F, -4.0F);
		rf_leg.cubeList.add(new ModelBox(rf_leg, 0, 0, -9.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		rm_leg = new ModelRenderer(this);
		rm_leg.setRotationPoint(-2.0F, 17.0F, 1.0F);
		rm_leg.cubeList.add(new ModelBox(rm_leg, 0, 0, -9.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		rr_leg = new ModelRenderer(this);
		rr_leg.setRotationPoint(-2.0F, 17.0F, 6.0F);
		rr_leg.cubeList.add(new ModelBox(rr_leg, 0, 0, -9.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -9.0F, -9.0F, 2, 2, 24, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		lf_leg.render(f5);
		lm_leg.render(f5);
		lr_leg.render(f5);
		rf_leg.render(f5);
		rm_leg.render(f5);
		rr_leg.render(f5);
		bb_main.render(f5);
	}

//	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
	
	private final float LEG_ANGLE = 0.58119464F;
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.rr_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        this.rf_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        this.lm_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.rm_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.lf_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        this.lr_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        // change: lf and lr to be with rf and rr, and change lm to be with rm
        
        this.rr_leg.rotateAngleZ = -LEG_ANGLE;
        this.rf_leg.rotateAngleZ = -LEG_ANGLE;
        this.rm_leg.rotateAngleZ = -LEG_ANGLE;
        this.lm_leg.rotateAngleZ = LEG_ANGLE;
        this.lf_leg.rotateAngleZ = LEG_ANGLE;
        this.lr_leg.rotateAngleZ = LEG_ANGLE;
    }
}
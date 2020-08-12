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
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		lf_leg.render(scale);
		lm_leg.render(scale);
		lr_leg.render(scale);
		rf_leg.render(scale);
		rm_leg.render(scale);
		rr_leg.render(scale);
		bb_main.render(scale);
	}

//	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
	
	private final float SPEEDMULT = 1.0F;
	private final float LEG_ANGLE = 0.58119464F;
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.rr_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F * SPEEDMULT + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rf_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F * SPEEDMULT + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.lm_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F * SPEEDMULT) * 1.4F * limbSwingAmount;
        this.rm_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F * SPEEDMULT) * 1.4F * limbSwingAmount;
        this.lf_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F * SPEEDMULT + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.lr_leg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F * SPEEDMULT + (float)Math.PI) * 1.4F * limbSwingAmount;
        
//        if (Math.abs((MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount))>0.2F) {
//        	System.out.println("rr_leg Angle: "+ (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount));
//        }
        
        this.rr_leg.rotateAngleZ = -LEG_ANGLE;
        this.rf_leg.rotateAngleZ = -LEG_ANGLE;
        this.rm_leg.rotateAngleZ = -LEG_ANGLE;
        this.lm_leg.rotateAngleZ = LEG_ANGLE;
        this.lf_leg.rotateAngleZ = LEG_ANGLE;
        this.lr_leg.rotateAngleZ = LEG_ANGLE;
    }

// Flapping version
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
//        this.rr_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.rf_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.lm_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.rm_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.lf_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.lr_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//    }
}
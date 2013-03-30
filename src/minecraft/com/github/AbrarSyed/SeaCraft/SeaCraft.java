package com.github.AbrarSyed.SeaCraft;

import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.network.HandlerClient;
import com.github.AbrarSyed.SeaCraft.network.HandlerServer;
import com.github.AbrarSyed.SeaCraft.network.PacketSCBase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = true,
		clientPacketHandlerSpec = @SidedPacketHandler(channels = { PacketSCBase.CHANNEL }, packetHandler = HandlerClient.class),
		serverPacketHandlerSpec = @SidedPacketHandler(channels = { PacketSCBase.CHANNEL }, packetHandler = HandlerServer.class))
@Mod(modid = SeaCraft.MODID, version = SeaCraft.VERSION, name = SeaCraft.MODID)
public class SeaCraft
{
	public static final String		MODID	= "SeaCraft";
	public static final String		VERSION	= "0.0.1";

	@Instance(value = MODID)
	public static SeaCraft			instance;

	@SidedProxy(serverSide = "com.github.AbrarSyed.SeaCraft.ProxyServer", clientSide = "com.github.AbrarSyed.SeaCraft.client.ProxyClient")
	public static ProxyServer		proxy;

	public static Logger			logger;

	public static ItemBoatKayak		kayak;

	public static CreativeTabBoats	tab;

	@PreInit
	public void preLoad(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		// creative tab
		tab = new CreativeTabBoats();

		// items.
		kayak = new ItemBoatKayak(9001);

		// registrations
		GameRegistry.registerItem(kayak, "SeaCraft_Kayak", MODID);

		// localizations
		LanguageRegistry.addName(kayak, "Kayak");
		LanguageRegistry.instance().addStringLocalization("SeaCraft.boats.furnace", "Furnace Boat");

		// entities.
		int ID = 1;
		EntityRegistry.registerModEntity(EntityBoatFurnace.class, "SeaCraft_Kayak", ID++, instance, 80, 3, true);

		// renderring stuff
		proxy.registerRenderStuff();

		// the gui handler
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
	}

	/**
	 * Get player's looking spot.
	 * @param player
	 * @param restrict Keep max distance to 5.
	 * @return The position as a MovingObjectPosition Null if not existent.
	 */
	public static MovingObjectPosition getPlayerLookingSpot(EntityPlayer player, boolean restrict)
	{
		float var4 = 1.0F;
		float var5 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * var4;
		float var6 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * var4;
		double var7 = player.prevPosX + (player.posX - player.prevPosX) * var4;
		double var9 = player.prevPosY + (player.posY - player.prevPosY) * var4 + 1.62D - player.yOffset;
		double var11 = player.prevPosZ + (player.posZ - player.prevPosZ) * var4;
		Vec3 var13 = player.worldObj.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * 0.017453292F - (float) Math.PI);
		float var15 = MathHelper.sin(-var6 * 0.017453292F - (float) Math.PI);
		float var16 = -MathHelper.cos(-var5 * 0.017453292F);
		float var17 = MathHelper.sin(-var5 * 0.017453292F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 500D;
		if (player instanceof EntityPlayerMP && restrict)
		{
			var21 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
		}
		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
		return player.worldObj.rayTraceBlocks_do_do(var13, var23, false, false);
	}
}

package me.cumhax.soviet.impl.module.movement;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HoleTP extends Module
{
	private final Setting holeOnly = new Setting("HoleOnly", this, true);

    public HoleTP() 
	{	
        super("HoleTP", "", Category.MOVEMENT);
    }


	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event)
	{
		if (mc.player == null || mc.world == null || mc.player.isInLava() || mc.player.isDead || mc.player.isInWater()) return;

		if (holeOnly.getBoolValue())
		{
			if (fallingIntoHole() && mc.player.onGround)
			{
				--mc.player.motionY;
			}
		}
		else
		{
			if (mc.player.onGround)
			{
				--mc.player.motionY;
			}
		}

	}

	private boolean fallingIntoHole()
	{
		final Vec3d vec = interpolateEntity(mc.player, mc.getRenderPartialTicks());

		final BlockPos pos = new BlockPos(vec.x, vec.y - 1, vec.z);

		BlockPos[] posList =
				{
						pos.north(),
						pos.south(),
						pos.east(),
						pos.west(),
						pos.down()
				};

		int blocks = 0;

		for (BlockPos blockPos : posList)
		{
			Block block = mc.world.getBlockState(blockPos).getBlock();

			if (block == Blocks.OBSIDIAN || block == Blocks.BEDROCK) ++blocks;

		}

		return blocks == 5;
	}

	private Vec3d interpolateEntity(EntityPlayerSP entity, float time)
	{
		return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * time,
				entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * time,
				entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * time);
	}

}

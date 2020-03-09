package com.kwpugh.gobber2.items.rings;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SeaGrassBlock;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.StemGrownBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.block.TallSeaGrassBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class ItemCustomRingFarmer extends Item
{

	public ItemCustomRingFarmer(Properties properties)
	{
		super(properties);
	}
 
	public void inventoryTick(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {      
    	if(!(entity instanceof PlayerEntity) || world.isRemote)
        {
            return;
        }

    	PlayerEntity player = (PlayerEntity)entity;
        ItemStack equippedMain = player.getHeldItemMainhand();
        ItemStack equippedOff = player.getHeldItemOffhand();
        
        if(stack == equippedMain || stack == equippedOff)
        {
        	if (!world.isRemote)
        	{
        	    int range = 15;
                for(int x = -range; x < range+1; x++)
                {
                    for(int z = -range; z < range+1; z++)
                    {
                        for(int y = -range; y < range+1; y++)
                        {
                            int theX = MathHelper.floor(player.getPosX()+x);
                            int theY = MathHelper.floor(player.getPosY()+y);
                            int theZ = MathHelper.floor(player.getPosZ()+z);
                            
                            BlockPos targetPos = new BlockPos(theX, theY, theZ);                       
                            BlockState blockstate = world.getBlockState(targetPos);
                          
                            if ((blockstate.getBlock() instanceof CropsBlock) ||
                            		(blockstate.getBlock() instanceof SaplingBlock) ||
                            		(blockstate.getBlock() instanceof VineBlock) ||                     		               
                            		(blockstate.getBlock() instanceof SugarCaneBlock) ||
                            		(blockstate.getBlock() instanceof SweetBerryBushBlock) ||
                            		(blockstate.getBlock() instanceof NetherWartBlock) ||
                            		(blockstate.getBlock() instanceof CactusBlock) ||
                            		(blockstate.getBlock() instanceof MelonBlock) ||
                            		(blockstate.getBlock() instanceof StemBlock) ||
                            		(blockstate.getBlock() instanceof PumpkinBlock) ) 
                            {
                        		if (player.ticksExisted % 20 == 0)
                        		{
                        			blockstate.tick((ServerWorld) world, targetPos, world.rand);
                       		 	}                                                               
                            }

                            if ((blockstate.getBlock() instanceof CoralBlock) ||		
                            		(blockstate.getBlock() instanceof BambooSaplingBlock) || 
                            		(blockstate.getBlock() instanceof BambooBlock)  ||
                            		(blockstate.getBlock() instanceof CocoaBlock) || 
                            		(blockstate.getBlock() instanceof StemGrownBlock) ||
                            		(blockstate.getBlock() instanceof CoralPlantBlock) ||
                            		(blockstate.getBlock() instanceof CoralBlock) ||
                            		(blockstate.getBlock() instanceof TallSeaGrassBlock) ||
                            		(blockstate.getBlock() instanceof SeaGrassBlock) ||
                            		(blockstate.getBlock() instanceof SeaPickleBlock) ||
                            		(blockstate.getBlock() instanceof ChorusFlowerBlock) )
                            {
                        		if (player.ticksExisted % 40 == 0)
                        		{
                        			blockstate.tick((ServerWorld) world, targetPos, world.rand);
                        		}                     		
                            }
                            
            		        if(blockstate.getBlock() instanceof GrassBlock && player.isShiftKeyDown())
                    		{
            		        	if (player.ticksExisted % 60 == 0)
            		        	{
            		        		((GrassBlock) blockstate.getBlock()).grow((ServerWorld) world, world.rand, targetPos, blockstate);	
            		        	}
                    		}
                        }
                    }
                }
        	}
        }
    }
       
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_farmer.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_farmer.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_farmer.line3").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}  
}
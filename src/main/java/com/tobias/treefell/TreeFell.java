package com.tobias.treefell;

import com.mojang.logging.LogUtils;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TreeFell.MOD_ID)
public class TreeFell {
    public static final String MOD_ID = "treefell";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TreeFell() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

    public static boolean isBottomOfTree(ClientLevel clientWorld, double x, double y, double z) {
        // convert doubles to ints
        int xInt = (int) x;
        int yInt = (int) y;
        int zInt = (int) z;


        BlockPos abovePos = new BlockPos(xInt, yInt + 1, zInt);
        BlockPos belowPos = new BlockPos(xInt, yInt - 1, zInt);

        // check if block above is air
        if (!clientWorld.getBlockState(abovePos).isAir()) {
            return false;
        }

        // check if block below is a log
        if (!clientWorld.getBlockState(belowPos).is(BlockTags.LOGS)) {
            return false;
        }

        return true;
    }

    public static void breakTree(ClientLevel clientWorld, double x, double y, double z) {
        // convert doubles to ints
        int xInt = (int) x;
        int yInt = (int) y;
        int zInt = (int) z;

        BlockPos pos = new BlockPos(xInt, yInt, zInt);

        breakTreeRecursive(clientWorld, pos);
    }

    private static void breakTreeRecursive(ClientLevel clientWorld, BlockPos pos) {
        BlockState blockState = clientWorld.getBlockState(pos);

        if (!isPartOfTree(blockState)) {
            return;
        }

        clientWorld.destroyBlock(pos, true);

        breakTreeRecursive(clientWorld, pos.north());
        breakTreeRecursive(clientWorld, pos.east());
        breakTreeRecursive(clientWorld, pos.south());
        breakTreeRecursive(clientWorld, pos.west());
        breakTreeRecursive(clientWorld, pos.above());
        breakTreeRecursive(clientWorld, pos.below());
    }

    private static boolean isPartOfTree(BlockState blockState) {
        return blockState.is(BlockTags.LOGS) || blockState.is(BlockTags.LEAVES);
    }
}
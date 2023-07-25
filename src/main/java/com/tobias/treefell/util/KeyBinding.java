package com.tobias.treefell.util;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {
    public static final String KEY_CATEGORY_TREEFELLING = "key.category.treefell.treefelling";
    public static final String KEY_FELLING = "key.treefell.felling";

    public static final KeyMapping FELLING_KEY = new KeyMapping(KEY_FELLING, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, KEY_CATEGORY_TREEFELLING)
}

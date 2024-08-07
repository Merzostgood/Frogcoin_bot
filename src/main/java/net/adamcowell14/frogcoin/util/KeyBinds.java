package net.adamcowell14.frogcoin.util;

import net.adamcowell14.frogcoin.Frogcoin;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.text.DecimalFormat;

public class KeyBinds {
    public static final String KEY_CATEGORY = "Frogcoin bot";
    public static KeyBinding START;
    public static KeyBinding STOP;
//    public static KeyBinding GET_DATA;

    public static class Task implements Runnable {
        @Override
        public void run() {
            PlayerEntity player = MinecraftClient.getInstance().player;
            Frogcoin.config.load();
            long delay = Frogcoin.delay;
            while (!STOP.isPressed()) {
                for (int i = 0; i < Frogcoin.points.length; i++) {
                    player.setYaw(Frogcoin.points[i].getStartPeriod());
                    player.setPitch(Frogcoin.points[i].getEndPeriod());
                    MinecraftClient.getInstance().options.useKey.setPressed(true);
                    try {
                        for (int j = 0; j <= delay; j+=1) {
                            Thread.sleep(1);
                            if (STOP.isPressed()) {
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                MinecraftClient.getInstance().options.useKey.setPressed(false);
            }
            Frogcoin.config.changeRunning(false);
        }
    }


    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client ->{
            if(START.wasPressed()) {
                PlayerEntity player = MinecraftClient.getInstance().player;
                if(!Frogcoin.running) {
                    Frogcoin.config.changeRunning(true);
                    Thread thread = new Thread(new Task());
                    thread.start();
                } else {
                    player.sendMessage(Text.of("§aFrogCoin Bot §2| §7Bot now started!"));
                }
            }

//            if(GET_DATA.wasPressed()) {
//                PlayerEntity player = MinecraftClient.getInstance().player;
//                if(player != null) {
//                    DecimalFormat Format = new DecimalFormat("#.##");
//                    String pitch = Format.format(player.getPitch());
//                    String yaw = Format.format(player.getYaw());
//
//                    player.sendMessage(Text.of("§aFrogCoin Bot §2| §7Yaw - " + yaw + ". Pitch - " + pitch));
//                }
//            }
        });
    }

    public static void register() {
//        GET_DATA = KeyBindingHelper.registerKeyBinding(new KeyBinding(
//                "Get data",
//                InputUtil.Type.KEYSYM,
//                GLFW.GLFW_KEY_Z,
//                KEY_CATEGORY
//        ));
        START = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Start",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                KEY_CATEGORY
        ));
        STOP = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Stop",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY
        ));

        registerKeyInputs();
    }
}

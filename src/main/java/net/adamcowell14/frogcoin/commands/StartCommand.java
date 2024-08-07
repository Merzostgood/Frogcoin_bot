//package net.adamcowell14.frogcoin.commands;
//
//import com.mojang.brigadier.CommandDispatcher;
//import com.mojang.brigadier.arguments.IntegerArgumentType;
//import com.mojang.brigadier.arguments.StringArgumentType;
//import com.mojang.brigadier.context.CommandContext;
//import net.adamcowell14.frogcoin.config.Point;
//import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
//import net.adamcowell14.frogcoin.Frogcoin;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.command.CommandRegistryAccess;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.text.Text;
//import java.lang.Thread;
//import net.minecraft.client.MinecraftClient;
//import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
//import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
//
//public class StartCommand {
//    private volatile boolean running = false;
//    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
//        fabricClientCommandSourceCommandDispatcher.register(
//                literal(Frogcoin.MOD_CMD).then(
//                        literal("start").
//                                executes(StartCommand::run)
//                )
//        );
//    }
//    public static void register2(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
//        fabricClientCommandSourceCommandDispatcher.register(
//                literal(Frogcoin.MOD_CMD).then(
//                        literal("stop").
//                                executes(StartCommand::stop)
//                )
//        );
//    }
//
//    public static class MyThread implements Runnable {
//        @Override
//        public void run() {
//            PlayerEntity player = MinecraftClient.getInstance().player;
//            Frogcoin.config.load();
//            long delay = Frogcoin.delay;
//            Frogcoin.LOGGER.info("1" + running);
//            while (running) {
//                Frogcoin.LOGGER.info("While" + running);
//                for (int i = 0; i <= Frogcoin.points.length; i++) {
//                    Frogcoin.LOGGER.info("for" + i);
//                    player.setYaw(Frogcoin.points[i].getStartPeriod());
//                    player.setPitch(Frogcoin.points[i].getEndPeriod());
//                    MinecraftClient.getInstance().options.useKey.setPressed(true);
//                    try {
//                        Thread.sleep(delay);
//                    } catch (InterruptedException e) {
//                        break;
//                    }
//                }
//                MinecraftClient.getInstance().options.useKey.setPressed(false);
//            }
//        }
//    }
//
//    private static int stop(CommandContext<FabricClientCommandSource> ctx) {
//        ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7Bot stoped!"));
//        boolean running = false;
//        return 1;
//    }
//
//
//
//    private static int run(CommandContext<FabricClientCommandSource> ctx) {
//        ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7Bot started!"));
//        boolean running = true;
//
//        return 1;
//    }
//}
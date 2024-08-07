package net.adamcowell14.frogcoin.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.adamcowell14.frogcoin.config.Point;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.frogcoin.Frogcoin;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AddPointCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(
                literal(Frogcoin.MOD_CMD).then(
                        literal("add").then(
                                argument("name", StringArgumentType.word()).
                                        executes(AddPointCommand::run)
                                )
                        )
                );
    }

    private static int run(CommandContext<FabricClientCommandSource> ctx) {
        String waypointName = ctx.getArgument("name", String.class);
        PlayerEntity player = MinecraftClient.getInstance().player;
        float yaw = player.getYaw();
        float pitch = player.getPitch();

        Frogcoin.config.addPoint(new Point(waypointName, yaw, pitch));
        ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7Succesful added point with name - " + waypointName));
        return 1;
    }
}
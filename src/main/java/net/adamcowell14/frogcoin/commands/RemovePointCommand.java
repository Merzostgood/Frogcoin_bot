package net.adamcowell14.frogcoin.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.frogcoin.Frogcoin;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class RemovePointCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(
                literal(Frogcoin.MOD_CMD).then(
                        literal("remove").then(
                                argument("name", StringArgumentType.word()).
                                        executes(RemovePointCommand::run)
                        )
                )
        );
    }

    private static int run(CommandContext<FabricClientCommandSource> ctx) {
        String waypointName = ctx.getArgument("name", String.class);
        if (Frogcoin.config.deletePoint(waypointName)) {
            ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7Succesful removed point with name - " + waypointName));
        } else {
            ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7There is no such point. \"/fcbot list\" to find out all the points"));
        }
        return 1;
    }
}
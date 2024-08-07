package net.adamcowell14.frogcoin.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.frogcoin.Frogcoin;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ChangeDelayCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(
                literal(Frogcoin.MOD_CMD).then(
                        literal("set").then(
                                literal("delay").then(
                                        argument("delay in milliseconds", LongArgumentType.longArg(1, 10000)).
                                                executes(ChangeDelayCommand::run)
                                )
                        )
                ));
    }

    private static int run(CommandContext<FabricClientCommandSource> ctx) {
        long delay = ctx.getArgument("delay in milliseconds", Long.class);
        Frogcoin.config.changeDelay(delay);
        ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7Now delay " + delay));

        return 1;
    }
}
package net.adamcowell14.frogcoin.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.frogcoin.Frogcoin;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ListPointsCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(
                literal(Frogcoin.MOD_CMD).then(
                        literal("list").executes(ListPointsCommand::run)
                )
        );
    }

    private static int run(CommandContext<FabricClientCommandSource> ctx) {
        String[] pointRepr = new String[Frogcoin.points.length];
        for (int i = 0; i < Frogcoin.points.length; i++) {pointRepr[i] = "§a" + i  + "§2 | §7" + Frogcoin.points[i].getName();}
        String message = "list of loaded Points:\n" + String.join("\n", pointRepr);
        ctx.getSource().sendFeedback(Text.of("§aFrogCoin Bot §2| §7" + message));
        return 1;
    }
}
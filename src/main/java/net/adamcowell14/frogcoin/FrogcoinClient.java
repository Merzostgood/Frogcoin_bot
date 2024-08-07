package net.adamcowell14.frogcoin;

import net.adamcowell14.frogcoin.commands.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.adamcowell14.frogcoin.util.KeyBinds;

public class FrogcoinClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinds.register();
        ClientCommandRegistrationCallback.EVENT.register(ChangeDelayCommand::register);
        ClientCommandRegistrationCallback.EVENT.register(AddPointCommand::register);
        ClientCommandRegistrationCallback.EVENT.register(RemovePointCommand::register);
        ClientCommandRegistrationCallback.EVENT.register(ListPointsCommand::register);
    }
}

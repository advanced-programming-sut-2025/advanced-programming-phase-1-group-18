package io.github.group18.Network.Client.App;

import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class VoteMessageHandler {
    public static Message getPlayers() {
        Message send = new Message(new HashMap<>(), Message.Type.get_players_usernames, Message.Menu.game);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        while (response.getType() != Message.Type.get_players_usernames) {
            response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        }
        return response;
    }
    public static void votePlayer(int playerIndex,int voterId) {
        HashMap<String,Object>body = new HashMap<>();
        body.put("playerIndex", playerIndex);
        body.put("voterId", voterId);
        Message send = new Message(body, Message.Type.vote_user, Message.Menu.game);
        ClientModel.getServerConnectionThread().sendMessage(send);
    }
    public static void voteTerminateGame(int voterId,boolean vote) {
        HashMap<String,Object>body = new HashMap<>();
        body.put("voterId", voterId);
        body.put("vote", vote?"true":"false");
        Message send = new Message(body, Message.Type.vote_terminate_game, Message.Menu.game);
        ClientModel.getServerConnectionThread().sendMessage(send);
    }
}

package io.github.group18.Network.common.models;

import java.util.HashMap;

public class Message {
    private Type type;
    private HashMap<String, Object> body;
    private Menu menu;

    /*
     * Empty constructor needed for JSON Serialization/Deserialization
     */
    public Message() {
    }

    public Message(HashMap<String, Object> body, Type type, Menu menu) {
        this.body = body;
        this.type = type;
        this.menu = menu;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }

    public <T> T getFromBody(String fieldName) {
        return (T) body.get(fieldName);
    }

    public int getIntFromBody(String fieldName) {
        return (int) ((double) ((Double) body.get(fieldName)));
    }

    public enum Type {
        command,
        Register,
        Login,
        Is_Unique,
        get_online_players,
        remove_from_online_players,
        add_user_to_lobby,
        create_lobby,
        get_all_lobbies,
        remove_user_to_lobby,
        change_menu,
        choose_map,
        refresh_maps,
        select_map,
        start_game,
        status,
        load_game_screen,
        add_player_to_Clientmain,
        get_kashi_using_x_y,
        get_kashis_using_2x_2y,
        get_dateTime,
        get_npc,
        get_players
    }

    public enum Menu {
        Basic,
        Register,
        Login,
        OnlinePlayers,
        lobby,
        choosing_map,
        CHANGE_MENU,
        game_menu,
        download_request,
        game
    }
}

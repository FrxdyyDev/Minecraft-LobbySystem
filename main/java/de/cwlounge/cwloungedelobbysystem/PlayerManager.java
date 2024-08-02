package de.cwlounge.cwloungedelobbysystem;

import de.cwlounge.cwloungedelobbysystem.Database.DatabaseManager;
import de.cwlounge.cwloungedelobbysystem.UTILS.MYSQLStorage;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    public static PlayerManager getInstance() {
        return instance;
    }

    private static PlayerManager instance;

    MYSQLStorage storage;

    ConcurrentHashMap<String, LobbyPlayer> lobbyplayercache = new ConcurrentHashMap<>();

    public PlayerManager(DatabaseManager db) {
        if (instance == null) {
            instance = this;
        }
        storage = new MYSQLStorage(db,"lobbyplayer");
    }

    public LobbyPlayer getLobbyPlayer(Player player) {
        return getLobbyPlayer(player.getUniqueId());
    }

    public LobbyPlayer getLobbyPlayer(UUID uuid) {
        return getLobbyPlayer(uuid.toString());
    }

    public LobbyPlayer getLobbyPlayer(String uuid) {
        return lobbyplayercache.get(uuid);
    }


    public LobbyPlayer loadLobbyPlayer(UUID uuid) {
        return loadLobbyPlayer(uuid.toString());
    }

    public LobbyPlayer loadLobbyPlayer(String uuid) {

        if (storage.existsObj(uuid)) {
            LobbyPlayer lobbyplayer = (LobbyPlayer) storage.getObj(uuid).getObject(LobbyPlayer.class);
            lobbyplayercache.put(uuid,lobbyplayer);
        } else {
            LobbyPlayer lobbyplayer = new LobbyPlayer();
            lobbyplayer.canFly = false;
            storage.storeObj(uuid,lobbyplayer);
            lobbyplayercache.put(uuid,lobbyplayer);
        }

        return getLobbyPlayer(uuid);
    }

    public void unloadLobbyPlayer(UUID uuid) {
        unloadLobbyPlayer(uuid.toString());
    }

    public void unloadLobbyPlayer(String uuid) {
        if (storage.existsObj(uuid)) {
            storage.updateObj(uuid, getLobbyPlayer(uuid));
        } else {
            storage.storeObj(uuid, getLobbyPlayer(uuid));
        }
        lobbyplayercache.remove(uuid);
    }

}

package de.cwlounge.cwloungedelobbysystem;

import org.bukkit.GameMode;

public class LobbyPlayer {

    boolean canFly;

    boolean flying;

    boolean inSilentLobby = false;

    boolean AccessSilentLobby = false;

    boolean doublejump;

    boolean buildmode;

    String name;

    GameMode gamemode = GameMode.SURVIVAL;

    String HidingStatus = "Everyone";

    boolean ableToFly = false;

    String IP = "";

    Boolean DayToggled = true;

    public Boolean getDayToggled() {
        return DayToggled;
    }

    public void setDayToggled(Boolean dayToggled) {
        DayToggled = dayToggled;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isAbleToFly() {
        return ableToFly;
    }

    public void setAbleToFly(boolean ableToFly) {
        this.ableToFly = ableToFly;
    }

    public boolean canAccessSilentLobby() {
        return AccessSilentLobby;
    }

    public void setcanAccessSilentLobby(boolean canAccessSilentLobby) {
        this.AccessSilentLobby = canAccessSilentLobby;
    }

    public boolean isInSilentLobby() {
        return inSilentLobby;
    }

    public void setInSilentLobby(boolean inSilentLobby) {
        this.inSilentLobby = inSilentLobby;
    }

    public String getHidingStatus() {
        return HidingStatus;
    }

    public void setHidingStatus(String hidingStatus) {
        HidingStatus = hidingStatus;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public boolean canFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean doublejumpEnabled() {
        return doublejump;
    }

    public void setDoublejump(boolean doublejump) {
        this.doublejump = doublejump;
    }

    public boolean isBuildmode() {
        return buildmode;
    }

    public void setBuildmode(boolean buildmode) {
        this.buildmode = buildmode;
    }

    public GameMode getGamemode() {
        return gamemode;
    }

    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

}
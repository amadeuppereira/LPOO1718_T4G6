package com.fr.funrungame.model;

import com.fr.funrungame.model.entities.PlatformModel;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private int startPosition;

    private int endPosition;

    private int mapNumber;

    private List<PlatformModel> platforms;

    public GameMap(){
        platforms = new ArrayList<PlatformModel>();
    }

    public List<PlatformModel> getPlatforms() {
        return platforms;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public int getMapNumber() {
        return mapNumber;
    }
}

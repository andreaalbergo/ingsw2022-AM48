package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Collection;

public class IslandTile {
    private int id_isle; //id of isles can be changed when we merge them into one isle
    private boolean mother_nature = false;
    private boolean empty_isle = true;
    private boolean is_tower = false;
    private int isle_owner;
    private Collection<Integer> students = new ArrayList<Integer>(); //I need to fix it

    //CONSTRUCTOR:
    public IslandTile(int id_isle) {
        this.id_isle = id_isle;
    }

    public int getIdIsle() { //maybe useless, I'll check it later
        return id_isle;
    }

    public int getIsleOwner() {
        return isle_owner;
    }

    public void mergeIsles(IslandTile i1, IslandTile i2) {
        //TODO
    }

    public void changeOwner(IslandTile isle, int owner) {
        //TODO
    }
}
//TALK TO OTHERS ABOUT UNIFIEDISLANDS
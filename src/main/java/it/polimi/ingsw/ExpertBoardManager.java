package it.polimi.ingsw;

public class ExpertBoardManager extends BoardManagerDecorator{
    public ExpertBoardManager(BoardManager decoratedBoardManager) { super(decoratedBoardManager);}

}

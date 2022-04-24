package it.polimi.ingsw;

public enum CharacterCard {
    MONK(1),
    INNKEEPER(2),
    PRINCE(3),
    HERALD(1),
    GROCER(2),
    CENTAUR(3),
    JESTER(1),
    KNIGHT(2),
    MERCHANT(3),
    MINSTREL(1),
    WARRIOR_PRINCESS(2),
    THIEF(3);

    private int characterEffectCost;

    CharacterCard(int characterEffectCost) {
        this.characterEffectCost = characterEffectCost;
    }

    public int getCharacterEffectCost() {
        return characterEffectCost;
    }

    public void incrementCostEffect(){
        this.characterEffectCost++;
    }

    public void activateCharacterEffect(CharacterCard characterCard) {
        switch (characterCard) {
            case MONK -> {
                incrementCostEffect();
            }
            case INNKEEPER -> {
                incrementCostEffect();
            }
            case PRINCE -> {
                incrementCostEffect();
            }
            case HERALD -> {
                incrementCostEffect();
            }
            case GROCER -> {
                incrementCostEffect();
            }
            case CENTAUR -> {
                incrementCostEffect();
            }
            case JESTER -> {
                incrementCostEffect();
            }
            case KNIGHT -> {
                incrementCostEffect();
            }
            case MERCHANT -> {
                incrementCostEffect();
            }
            case MINSTREL -> {
                incrementCostEffect();
            }
            case WARRIOR_PRINCESS -> {
                incrementCostEffect();
            }
            case THIEF -> {
                incrementCostEffect();
            }
            default -> {
                System.out.println("You have chosen a wrong character card!!!");
            }
        }
    }

    public void terminateCharacterEffect() {

    }
}

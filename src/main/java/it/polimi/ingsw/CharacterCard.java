package it.polimi.ingsw;

//HOW DO I CREATE CHARACTER CARDS RANDOMLY WITHOUT HAVING SIMILAR OBJECTS? NICE QUESTION...I SHOULD FIX THIS ISSUE

public class CharacterCard {
    private final Character character;
    private int characterEffectCost;

    public CharacterCard() {
        this.character = Character.getRandomCharacter();
        this.characterEffectCost = Character.getCharacterEffectCost(this.character);
    }

    public void chooseCard(CharacterCard characterCard) {
        switch(characterCard.character) {
            case MONK -> {
                incrementCostEffect();
                monkEffect();
            }
            case INNKEEPER -> {
                incrementCostEffect();
                innkeeperEffect();
            }
            case PRINCE -> {
                incrementCostEffect();
                princeEffect();
            }
            case HERALD -> {
                incrementCostEffect();
                heraldEffect();
            }
            case GROCER -> {
                incrementCostEffect();
                grocerEffect();
            }
            case CENTAUR -> {
                incrementCostEffect();
                centaurEffect();
            }
            case JESTER -> {
                incrementCostEffect();
                jesterEffect();
            }
            case KNIGHT -> {
                incrementCostEffect();
                knightEffect();
            }
            case MERCHANT -> {
                incrementCostEffect();
                merchantEffect();
            }
            case MINSTREL -> {
                incrementCostEffect();
                minstrelEffect();
            }
            case WARRIOR_PRINCESS -> {
                incrementCostEffect();
                warriorPrincessEffect();
            }
            case THIEF -> {
                incrementCostEffect();
                thiefEffect();
            }
            default -> System.out.println("You have chosen an unknown name!!!");
        }
    }

    private void incrementCostEffect() {
        characterEffectCost++;
    }

    private void monkEffect() {
        //TODO
    }

    private void innkeeperEffect() {
        //TODO
    }

    private void princeEffect() {
        //TODO
    }

    private void heraldEffect() {
        //TODO
    }

    private void grocerEffect() {
        //TODO
    }

    private void centaurEffect() {
        //TODO
    }

    private void jesterEffect() {
        //TODO
    }

    private void knightEffect() {
        //TODO
    }

    private void merchantEffect() {
        //TODO
    }

    private void minstrelEffect() {
        //TODO
    }

    private void warriorPrincessEffect() {
        //TODO
    }

    private void thiefEffect() {
        //TODO
    }

    public void terminateCharacterEffect() {
        //TODO
    }
}

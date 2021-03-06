package hololivemod.relics;

import hololivemod.Char.HololiveCharacter;
import hololivemod.actions.SpawnMateAction;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import hololivemod.minions.MinatoAqua;

public class OneManArmy extends CustomRelic {
    public static final String ID = "Hololive_OneManArmy";
    private boolean startcombat = true;
    public OneManArmy() {
        super(ID, ImageMaster.loadImage("img/relics/OneManArmy.png"),
                RelicTier.SPECIAL, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atBattleStart() {
        startcombat = true;
        if(AbstractDungeon.player instanceof HololiveCharacter && AbstractDungeon.player.maxOrbs > 0){
            this.counter = 0;
            this.addToBot(new DecreaseMaxOrbAction(AbstractDungeon.player.maxOrbs - 1));
            this.addToBot(new SpawnMateAction(new MinatoAqua(false),4,12));
        }

    }

    @Override
    public void atTurnStart() {
        boolean HasMem = false;
        for(AbstractOrb o:AbstractDungeon.player.orbs){
            if(o instanceof MinatoAqua){
                HasMem = true;
                break;
            }
        }
        if(!HasMem && !startcombat){
            ++this.counter;
        }
        if(this.counter >= 3){
            this.flash();
            this.addToBot(new SpawnMateAction(new MinatoAqua(false),4,12));
            this.counter = 0;
        }
        if(startcombat) startcombat = false;
    }

    @Override
    public void onEnterRoom(AbstractRoom room){
        if(AbstractDungeon.floorNum >= 50)
            if(!CardLibrary.getCard("Hololive_ChallengeOneManArmy").isSeen)
                UnlockTracker.markCardAsSeen("Hololive_ChallengeOneManArmy");
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new OneManArmy();
    }
}

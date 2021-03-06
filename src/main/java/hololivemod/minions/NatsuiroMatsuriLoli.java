package hololivemod.minions;

import hololivemod.cards.skillCard.Yeah;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

public class NatsuiroMatsuriLoli extends AbstractMinion {
    private static final String ID = "Hololive_NatsuiroMatsuriLoli";
    private static final OrbStrings cardStrings = CardCrawlGame.languagePack.getOrbString(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String[] DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/Orbs/NatsuiroMatsuri.png";
    private static final int basePassiveAmount = 8;
    private static final int baseEvokeAmount = 3;
    private static final int originalHP = 8;
    private static final int originalATK = 8;
    public NatsuiroMatsuriLoli(boolean upgraded){
        super(ID,NAME,basePassiveAmount,baseEvokeAmount,originalHP,IMG_PATH,DESCRIPTION[0],DESCRIPTION[1]);
        this.upgraded = upgraded;
        this.ATK = originalATK;
    }

    public AbstractMinion makeCopy(){
        return new NatsuiroMatsuriLoli(this.upgraded);
    }

    @Override
    public void onEvoke(){
        super.onEvoke();
    }

    @Override
    public void onDamaged(int dmg, boolean Effect){
        if(dmg != 0) {
            AbstractCard c = new Yeah();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
            c.upgrade();
            if(upgraded){
                AbstractCard c1 = new Yeah();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c1));
                c1.upgrade();
            }
        }
        super.onDamaged(dmg,Effect);
    }

    @Override
    public void triggerEvokeAnimation(){
        CardCrawlGame.sound.play("ORB_DARK_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(this.cX, this.cY));
    }


    @Override
    public void onEndOfTurn(){
       this.AttackEffect();
    }

    @Override
    public void AttackEffect(){
        Attack(AbstractGameAction.AttackEffect.POISON);
        Trigger();
        super.AttackEffect();
    }


    public void playChannelSFX(){
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }
}

package hololivemod.cards.attackCard;

import hololivemod.cards.skillCard.AbstractChooseCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hololivemod.helper.CardHelper;
import hololivemod.minions.AbstractMinion;
import hololivemod.patches.AbstractCardEnum;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;
import static hololivemod.patches.CardTagEnum.CHOOSE_CARD;

public class CocoNews extends AbstractChooseCard {
    private static final String ID = "Hololive_CocoNews";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/attackcards/CocoNews.png";
    private static final int COST = 1;
    private static final int MAGIC_NUM = 2;
    private static final int UPGRADE_MAGIC_NUM = 1;
    public CocoNews() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                ATTACK, AbstractCardEnum.Hololive_BLUE,
                CardRarity.COMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUM;
        if(CardHelper.isBalance){
            this.magicNumber = this.baseMagicNumber = MAGIC_NUM - 1;
        }

        this.tags.add(CHOOSE_CARD);
    }


    //@Override
    //public void use(AbstractPlayer p, AbstractMonster m) {
    //    int count = 0;
    //    for(AbstractOrb orb:p.orbs){
    //        if(orb instanceof AbstractMinion)
    //            ++count;
    //    }
    //    if(count == 1){
    //        for(AbstractOrb orb:p.orbs){
    //            if(orb instanceof AbstractMinion) {
    //                ((AbstractMinion) orb).ChangeHP(this.magicNumber);
    //                ((AbstractMinion) orb).Taunt = true;
    //            }
    //        }
    //    }else {
    //        ArrayList<AbstractCard> choices = OrbHelper.ChooseOneMinionGroup(ID, this.magicNumber);
    //        AbstractDungeon.actionManager.addToBottom(new ChooseOneAction(choices));
    //    }
    //}

    //public boolean canUse(AbstractPlayer p,AbstractMonster m){
    //    boolean canUse = super.canUse(p, m);
    //    int count = 0;
    //    for(AbstractOrb orb:p.orbs){
    //        if(orb instanceof AbstractMinion)
    //            ++count;
    //    }
    //    if (!canUse) {
    //        return false;
    //    } else if (count <= 0) {
    //        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
    //        return false;
    //    } else {
    //        return canUse;
    //    }
    //}

    @Override
    public void ChooseEffectAll(AbstractOrb orb){
        ((AbstractMinion)orb).ChangeStat(magicNumber, magicNumber, false);
        ((AbstractMinion) orb).AttackEffect();
    }




    @Override
    public AbstractCard makeCopy() {
        return new CocoNews();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
        }
    }
}
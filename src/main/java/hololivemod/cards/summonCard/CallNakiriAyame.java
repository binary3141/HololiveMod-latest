package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.actions.SpawnMateAction;
import hololivemod.cards.attackCard.Rasetsu;
import hololivemod.helper.CardHelper;
import hololivemod.minions.NakiriAyame;
import hololivemod.patches.AbstractCardEnum;

public class CallNakiriAyame extends AbstractSummonCard {
    private static final String ID = "Hololive_CallNakiriAyame";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallNakiriAyame.png";
    private static final int COST = 2;
    public CallNakiriAyame(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new Rasetsu();
        this.cardATK = 8;
        this.cardHP = 8;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 7;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(31), CardHelper.CombinationsDescription.get(31)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(22), CardHelper.CombinationsDescription.get(22)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(16), CardHelper.CombinationsDescription.get(16)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new NakiriAyame(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallNakiriAyame();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;


            this.initializeDescription();
        }
    }
}

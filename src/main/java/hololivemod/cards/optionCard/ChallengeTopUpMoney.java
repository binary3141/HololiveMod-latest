package hololivemod.cards.optionCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChallengeTopUpMoney extends AbstractCombinatiionCard{
    private static final String ID = "Hololive_ChallengeTopUpMoney";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/skillcards/TopUpMoney.png";

    public ChallengeTopUpMoney(){
        super(ID, NAME, IMG_PATH,DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new ChallengeTopUpMoney();
    }
}

package hololivemod.cards.attackCard;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.helper.CardHelper;
import hololivemod.patches.AbstractCardEnum;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class Rasetsu extends CustomCard {
    private static final String ID = "Hololive_Rasetsu";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/attackcards/Nakiri.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 3;
    private static final int UPGRADE_PLUS_DMG = 1;

    public Rasetsu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                ATTACK, AbstractCardEnum.Hololive_BLUE,
                CardRarity.SPECIAL, CardTarget.ENEMY);
        this.damage=this.baseDamage = ATTACK_DMG;
        if(CardHelper.isBalance){
            this.damage=this.baseDamage = ATTACK_DMG - 1;
        }
        this.selfRetain = true;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1,false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rasetsu();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}

package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.BuddyUp;
import com.jvn.degreespree.models.cards.CECS100;
import com.jvn.degreespree.models.cards.CECS105;
import com.jvn.degreespree.models.cards.CECS174;
import com.jvn.degreespree.models.cards.CECS201;
import com.jvn.degreespree.models.cards.CECS228;
import com.jvn.degreespree.models.cards.CECS274;
import com.jvn.degreespree.models.cards.CECS277;
import com.jvn.degreespree.models.cards.CECS282;
import com.jvn.degreespree.models.cards.CECS285;
import com.jvn.degreespree.models.cards.Card;
import com.jvn.degreespree.models.cards.Carpool;
import com.jvn.degreespree.models.cards.Chem111;
import com.jvn.degreespree.models.cards.ChooseMajor;
import com.jvn.degreespree.models.cards.DeansList;
import com.jvn.degreespree.models.cards.DoNothing;
import com.jvn.degreespree.models.cards.ENGL317;
import com.jvn.degreespree.models.cards.ElectiveClass;
import com.jvn.degreespree.models.cards.EnjoyPeace;
import com.jvn.degreespree.models.cards.EnjoyingNature;
import com.jvn.degreespree.models.cards.ExercisingMB;
import com.jvn.degreespree.models.cards.FallPond;
import com.jvn.degreespree.models.cards.FindLab;
import com.jvn.degreespree.models.cards.GoodbyeProfessor;
import com.jvn.degreespree.models.cards.GreenTornado;
import com.jvn.degreespree.models.cards.HaveSwim;
import com.jvn.degreespree.models.cards.Kin253;
import com.jvn.degreespree.models.cards.LBSVUCI;
import com.jvn.degreespree.models.cards.LateForClass;
import com.jvn.degreespree.models.cards.LearnNetBeans;
import com.jvn.degreespree.models.cards.LearningLinux;
import com.jvn.degreespree.models.cards.LoudBuzzing;
import com.jvn.degreespree.models.cards.LunchBratWurst;
import com.jvn.degreespree.models.cards.MakeFriend;
import com.jvn.degreespree.models.cards.Math122;
import com.jvn.degreespree.models.cards.Math123;
import com.jvn.degreespree.models.cards.MeetDean;
import com.jvn.degreespree.models.cards.Mystery;
import com.jvn.degreespree.models.cards.NewLaptop;
import com.jvn.degreespree.models.cards.OralCommunication;
import com.jvn.degreespree.models.cards.PHIL270;
import com.jvn.degreespree.models.cards.PHYS152;
import com.jvn.degreespree.models.cards.ParkingViolation;
import com.jvn.degreespree.models.cards.PassSoccer;
import com.jvn.degreespree.models.cards.Phys151;
import com.jvn.degreespree.models.cards.PressRightFloor;
import com.jvn.degreespree.models.cards.ProfessorEnglert;
import com.jvn.degreespree.models.cards.ProfessorHoffman;
import com.jvn.degreespree.models.cards.ProgramCrashes;
import com.jvn.degreespree.models.cards.ResearchCompilers;
import com.jvn.degreespree.models.cards.ScoreGoal;
import com.jvn.degreespree.models.cards.SoccerGoalie;
import com.jvn.degreespree.models.cards.StudentParking;
import com.jvn.degreespree.models.cards.TheBigGame;
import com.jvn.degreespree.models.cards.TheOutpost;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vishal on 10/29/15.
 */
public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> cardsInPlay;
    private ArrayList<Card> discards;
    private GameController controller;

    public Deck(GameController controller) {
        cards = new ArrayList<>();
        cardsInPlay = new ArrayList<>();
        discards = new ArrayList<>();
        this.controller = controller;
        initDeck();
    }

    public Card drawCard() {
        if (cardsInPlay.size() == 0) {
            cardsInPlay.addAll(discards);
            discards.clear();
            shuffle();
        }

        Card card = cardsInPlay.get(0);
        cardsInPlay.remove(0);

        return card;
    }

    public ArrayList<Card> take(int n) {
        ArrayList<Card> crds = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            crds.add(drawCard());
        }
        return crds;
    }

    public void shuffle() {
        Collections.shuffle(cardsInPlay);
    }

    public void discard(Card card) {
        discards.add(card);
    }

    private void initDeck() {

        cards.add(new BuddyUp());
        cards.add(new CECS100());
        cards.add(new CECS105());
        cards.add(new CECS174());
        cards.add(new ChooseMajor());
        cards.add(new EnjoyPeace());
        cards.add(new ExercisingMB());
        cards.add(new FindLab());
        cards.add(new GoodbyeProfessor());
        cards.add(new Kin253());
        cards.add(new LateForClass());
        cards.add(new LearnNetBeans());
        cards.add(new LunchBratWurst());
        cards.add(new Math122());
        cards.add(new Math123());
        cards.add(new ParkingViolation());
        cards.add(new PassSoccer());
        cards.add(new Phys151());
        cards.add(new ResearchCompilers());
        cards.add(new TheBigGame());
        cards.add(new Chem111());
        cards.add(new DeansList());
        cards.add(new ElectiveClass());
        cards.add(new EnjoyingNature());
        cards.add(new FallPond());
        cards.add(new LearningLinux());
        cards.add(new LoudBuzzing());
        cards.add(new MakeFriend());
        cards.add(new MeetDean());
        cards.add(new NewLaptop());
        cards.add(new OralCommunication());
        cards.add(new PressRightFloor());
        cards.add(new ProfessorEnglert());
        cards.add(new ProfessorHoffman());
        cards.add(new ProgramCrashes());
        cards.add(new ScoreGoal());
        cards.add(new SoccerGoalie());
        cards.add(new StudentParking());
        cards.add(new TheOutpost());


        // sophmore
        cards.add(new Carpool());
        cards.add(new CECS201());
        cards.add(new CECS228());
        cards.add(new CECS274());
        cards.add(new CECS277());
        cards.add(new CECS282());
        cards.add(new CECS285());
        cards.add(new HaveSwim());
        cards.add(new LBSVUCI());
        cards.add(new DoNothing());
        cards.add(new GreenTornado());
        cards.add(new Mystery());
        cards.add(new ENGL317());
        cards.add(new PHIL270());
        cards.add(new PHYS152());



        for (Card card : cards) {
            card.bind(controller);
        }

        setYear(Year.Freshman);

        shuffle();

    }

    public void setYear(Year year) {
        cardsInPlay.clear();
        discards.clear();

        for (Card card : cards) {
            if (card.getYear() == year || card.getYear() == Year.All) {
                cardsInPlay.add(card);
            }
        }
        shuffle();
    }

    public int inPlay() {
        return cardsInPlay.size();
    }

    public int outPlay() {
        return discards.size();
    }



}

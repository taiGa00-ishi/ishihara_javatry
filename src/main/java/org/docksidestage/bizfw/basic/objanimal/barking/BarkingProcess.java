package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Barking processを管理しているクラス.
 * <p>
 * このクラスでは動物の吠えるプロセスについてのメソッドを管理している。
 * </p>
 * @author taiGa00-ishi
 **/

public class BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    private final Animal animal;
    private final IDownHitPoint downHitPoint;

    public BarkingProcess(Animal animal, IDownHitPoint downHitPoint) {
        this.animal = animal;
        this.downHitPoint = downHitPoint;
    }

    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() { // actually depends on barking
        if (animal instanceof IBreatheInAction) {
            ((IBreatheInAction) animal).breatheInAction();
        }
        logger.debug("...Breathing in for barking"); // dummy implementation
        // animal.downHitPointHub();
        downHitPoint.downHitPoint();

    }

    protected void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        // animal.downHitPointHub();
        downHitPoint.downHitPoint();
    }

    protected BarkedSound doBark(String barkWord) {
        // animal.downHitPointHub();
        downHitPoint.downHitPoint();
        BarkedSound sound = new BarkedSound(barkWord);
        logger.debug(barkWord);
        return sound;
    }
}

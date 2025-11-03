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

    public BarkingProcess(Animal animal) {
        this.animal = animal;
    }

    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() { // actually depends on barking
        animal.onBreatheIn(); // フックとして呼び出す(Zombieのため)
        logger.debug("...Breathing in for barking"); // dummy implementation
        animal.downHitPointHub();
    }

    protected void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        animal.downHitPointHub();
    }

    protected BarkedSound doBark(String barkWord) {
        animal.downHitPointHub();
        BarkedSound sound = new BarkedSound(barkWord);
        logger.debug(barkWord);
        return sound;
    }
}

package org.docksidestage.bizfw.basic.objanimal.sleeper;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO ishihara javadocお願いします by jflute (2025/10/06)
public class Koala extends Animal implements LongSleeper {

    private static final Logger logger = LoggerFactory.getLogger(Koala.class);

    @Override
    protected String getBarkWord() {
        return "fugo";
    }

    @Override
    protected void downHitPoint() {
        super.downHitPoint();
        super.downHitPoint();
        super.downHitPoint();
    }

    @Override
    public void sleep() {
        logger.debug("...Sleeping now");
        super.hitPoint = super.getInitialHitPoint();
    }

}

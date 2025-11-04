package org.docksidestage.bizfw.basic.objanimal.sleeper;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// done ishihara javadocお願いします by jflute (2025/10/06)
/**
 * コアラを表すクラスです。
 * <p>
 * このクラスは、動物クラスを継承し、長時間睡眠する特性を持っています。
 * </p>
 * @author taiGa00-ishi
 */
public class Koala extends Animal implements LongSleeper {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Koala.class);

    // ===================================================================================
    //                                                                                Bark
    //                                                                         ===========
    @Override
    public String getBarkWord() {
        return "fugo";
    }
    
    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
        super.downHitPoint();
        super.downHitPoint();
        super.downHitPoint();
    }
    // ===================================================================================
    //                                                                             Sleeper
    //                                                                             =======
    @Override
    public void sleep() {
        logger.debug("...Sleeping now");
        // done ishihara super. 両方とも不要です (というか無い方がいい) by jflute (2025/10/20)
        // 仮に、getInitialHitPoint() をオーバーライドして拡張した場合、
        // super.getInitialHitPoint() だと、そのオーバーライドメソッドが呼ばれない。
        hitPoint = getInitialHitPoint();
    }

}

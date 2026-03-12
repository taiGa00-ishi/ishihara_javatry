package org.docksidestage.bizfw.basic.objanimal.barking;

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

    // #1on1: nullの可否をコメントで表現してるのとてもGood (2026/03/12)
    private final IDownHitPoint downHitPoint;
    private final IBreatheInAction breatheInAction; // null means no extra action

    public BarkingProcess(IDownHitPoint downHitPoint, IBreatheInAction breatheInAction) {
        this.downHitPoint = downHitPoint;
        this.breatheInAction = breatheInAction;
    }

    public BarkingProcess(IDownHitPoint downHitPoint) {
        this.downHitPoint = downHitPoint;
        this.breatheInAction = null;
    }

    public BarkedSound bark(String barkWord) {
        breatheIn();
        prepareAbdominalMuscle();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() { // actually depends on barking
        // done ishihara 修行#: animalへの依存を無くせたら無くしたいところ by jflute (2026/02/16)
        // (BarkingProcessのクラス内で、Animalクラスへの参照を無くしたい)
        // hint1: インターフェースにとらわれていても大丈夫だよ

        // オーバーロード的な考えでbreatheInを二つ用意して引数によって変わるようなアイデアで最初考えた
        // 結局barkで条件分岐しないといけないのでそれより前の段階で引数の違う同じ名前のものを用意しようとした
        // barkingProcessのコンストラクタで2パターンに分けることにした
        // animalかどうか知らなくて良くなったのでAnimalからthisを受け取らないようにした
        if (breatheInAction != null) {
            breatheInAction.breatheInAction();
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

/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int validDays;
    private final boolean nightOnly;
    private int usedDays;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int validDays, boolean nightOnly) {
        this.displayPrice = displayPrice;
        this.validDays = validDays;
        this.nightOnly = nightOnly;
        this.usedDays = 0;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark(boolean isNight) {
        if (usedDays >= validDays && alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }

        if (this.nightOnly && !isNight) {
            throw new IllegalStateException("You cannot enter by night-only ticket in the daytime.");
        }
        alreadyIn = true;
        usedDays++;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public int getValidDays() {
        return validDays;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public boolean isNightOnly() {
        return nightOnly;
    }

}

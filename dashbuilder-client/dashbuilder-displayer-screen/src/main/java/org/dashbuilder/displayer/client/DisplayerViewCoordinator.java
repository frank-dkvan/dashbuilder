/**
 * Copyright (C) 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dashbuilder.displayer.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.dashbuilder.displayer.client.DisplayerCoordinator;
import org.uberfire.client.workbench.events.PerspectiveChange;

/**
 * It holds the set of DisplayerView instances being displayed on the current perspective.
 * <p>It also makes sure those instances are properly synced to reflect the data set manipulation requests
 * issued by any DisplayerView on the dashboard.</p>
 */
@ApplicationScoped
public class DisplayerViewCoordinator {

    /**
     * A coordinator for all the DisplayerView instances placed on the same perspective.
     */
    private DisplayerCoordinator coordinator;

    @PostConstruct
    public void init() {
        coordinator = new DisplayerCoordinator();
    }

    /**
     * Add a DisplayerView instance to the dashboard context.
     */
    public void addDisplayerView(DisplayerView displayerView) {
        coordinator.addDisplayer(displayerView.getDisplayer());
    }

    /**
     * Reset the coordinator every time we switch from perspective.
     */
    private void onPerspectiveChanged(@Observes final PerspectiveChange event) {
        init();
    }
}
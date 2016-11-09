
package com.example;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author mstahv
 */
@SpringViewDisplay
public class MainViewDisplay extends Panel implements ViewDisplay {

    public MainViewDisplay() {
        setStyleName(ValoTheme.PANEL_BORDERLESS);
    }

    @Override
    public void showView(View view) {
        // Assuming View's are components, which is often the case
        setContent((Component) view);
    }
    

}

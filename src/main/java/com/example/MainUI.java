package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author mstahv
 */
@SpringUI
@Theme("valo")
public class MainUI extends UI {

    SpringViewProvider viewProvider;
    MainViewDisplay mainContent;
    ErrorView errorView;

    public MainUI(SpringViewProvider viewProvider, MainViewDisplay mainContent, ErrorView errorView) {
        this.viewProvider = viewProvider;
        this.mainContent = mainContent;
        this.errorView = errorView;
    }

    @Override
    protected void init(VaadinRequest request) {
        
        Navigator navigator = new Navigator(this, (ViewDisplay) mainContent);
        navigator.addProvider(viewProvider);
        
        navigator.setErrorProvider(new ViewProvider() {
            @Override
            public String getViewName(String viewAndParameters) {
                return "FirstView";
            }

            @Override
            public View getView(String viewName) {
                return errorView;
            }
        });
        setNavigator(navigator);
        
        setContent(
                new MHorizontalLayout()
                        .add(createNavigationBar())
                        .expand(mainContent)
                        .withFullHeight()
        );
    }

    private Component createNavigationBar() {
        MVerticalLayout m = new MVerticalLayout().withWidth("300px");
        m.addComponent(createNavButton("First", FirstView.class));
        m.addComponent(createNavButton("Second", SecondView.class));
        return m;
    }

    private Component createNavButton(String first, Class<? extends View> aClass) {
        Button button = new Button(first);
        button.addClickListener(e->getNavigator().navigateTo(aClass.getSimpleName().replaceAll("View", "").toLowerCase()));
        button.addStyleName(ValoTheme.BUTTON_LARGE);
        button.addStyleName(ValoTheme.BUTTON_LINK);
        return button;
    }
    
    

}

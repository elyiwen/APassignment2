package code.Scene;

import javafx.scene.control.Tab;

public class CustomTabHome extends Tab{
    
    private String tabName;

    public CustomTabHome(String tabName){
        this.tabName = tabName;
        this.setText(tabName);
    }

    public String getTabName(){
        return tabName;
    }
}

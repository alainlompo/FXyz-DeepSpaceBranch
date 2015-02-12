/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fxyz.controls;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class ControlCategory  extends TitledPane{
    
    @FXML
    private ListView<StackPane> listView;
    
    private final ObservableList<StackPane> controlItems;

    private ControlCategory() {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlPanelTitlePane.fxml"));
            loader.setRoot(ControlCategory.this);
            loader.setController(ControlCategory.this);
            loader.load();
        } catch (IOException ex) {            
            Logger.getLogger(ControlCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.controlItems = FXCollections.observableArrayList();
        this.listView.setItems(controlItems);
        
        //this.getStyleClass().add("fxyz-control-category");
    }    
    
    public ControlCategory(String title) {
        this();
        this.setText(title);
        this.setFocusTraversable(false);
        //EasyBind.listBind(controlItems, controls.getChildren());       
        this.listView.setCellFactory(new Callback<ListView<StackPane>,ListCell<StackPane>>() {
            
            @Override
            public ListCell<StackPane> call(ListView<StackPane> param) {
                return new ListCell<StackPane>(){
                    {
                        this.setFocusTraversable(false);
                        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    }
                    @Override
                    public boolean isResizable() {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void updateSelected(boolean selected) {
                        //do nothing...
                    }
                    
                    @Override
                    protected void updateItem(StackPane item, boolean empty) {
                        if(item != null && !empty){
                            
                            super.updateItem(item, empty);                            
                            super.setGraphic(item);                            
                        }
                    }
                    
                };
            }
        });
    }
        
    public void addControl(StackPane n){
        if(!controlItems.contains(n)){
            controlItems.add(n);
        }
    }
    public void addControls(StackPane ... ctrls){
        if(!controlItems.containsAll(Arrays.asList(ctrls))){
            controlItems.addAll(ctrls);
        }
    }
    public void removeControl(Node ...  n){
        controlItems.removeAll(Arrays.asList(n));
    }
    
    
    
}

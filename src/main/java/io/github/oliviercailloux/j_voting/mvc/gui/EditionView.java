package io.github.oliviercailloux.j_voting.mvc.gui;

import io.github.oliviercailloux.j_voting.Alternative;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class EditionView {
    private TabFolder tabfolder;
    private TabItem editionTab;
    private Composite mainComposite;
    private GridLayout gridLayout;
    
    public static EditionView create(TabFolder mainTabFolder) {
        return new EditionView(mainTabFolder);
    }

    private EditionView(TabFolder mainTabFolder) {
        this.tabfolder = mainTabFolder;
        this.mainComposite = new Composite(tabfolder, SWT.NONE);

        this.gridLayout = new GridLayout();
        this.gridLayout.numColumns = 2;
        this.mainComposite.setLayout(gridLayout);

        initEditionTab();
    }

    // Demander si ca passe dans le controller
    private void initEditionTab() {
        this.editionTab = new TabItem(this.tabfolder, SWT.NONE);
        editionTab.setText("Edition");
    }

    public void displayVoters(String voterName) {
    	Text voter = new Text(mainComposite, SWT.BORDER);
        voter.setData("event", "voterBox");
    	voter.setText(voterName);

        GridData gridData = new GridData();
        gridData.horizontalSpan = 2;
        gridData.horizontalAlignment = GridData.FILL;
        voter.setLayoutData(gridData);
    	//voter.setBounds(10,10,200,25);
       // editionTab.setControl(mainComposite);
    }

    // todo : revoir le layout avec les class layout de SWT c'est mieux que des set bounds avec X, Y je pense
    public void displayAlternatives(Set<Alternative> altSet) {
    	int counterY = 50;
    	int lasty = 0;

    	
    	for(Alternative a : altSet) {
    		Text alt = new Text(mainComposite, SWT.BORDER);
            alt.setData("event", "alternativeBox");
            alt.setData("alt", a);
            alt.setText(a.toString());
            //alt.setBounds(10,counterY,100,25);
            GridData data = new GridData();
            data.widthHint = 120;
            alt.setLayoutData(data);

  
            Button btn = new Button(mainComposite, SWT.NONE);
            //btn.setBounds(120, counterY,100,25);
            btn.setText("Delete");
            btn.setData("event", "deleteAlternativeBtn");
            btn.setData("alt", a);
            counterY += 30;
            
            lasty = btn.getBounds().y;      
        }
    
        //editionTab.setControl(mainComposite);
        displayAddAlternatives(lasty, altSet.size()+1);
    }
    
    public void displayAddAlternatives(int positionY, int controlId) {
    	Button btn = new Button(mainComposite, SWT.NONE);
        //btn.setBounds(120, positionY+30,100,25);
        btn.setText("Add Alternative");
        btn.setData("event", "addAlternativeBtn");
        btn.setData("addAltID", controlId);

        Text newAlt = new Text(mainComposite, SWT.BORDER);
        //newAlt.setBounds(10, positionY + 30,100,25);
        editionTab.setControl(mainComposite);
        newAlt.setData("addAltID", controlId);
    }


    public Composite getComposite() {
        return this.mainComposite;
    }

    public void removeControl(Control ctr) {
        //mainComposite.pack();
        ctr.dispose();
    }

	

}

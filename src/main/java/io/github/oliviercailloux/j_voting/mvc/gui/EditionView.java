package io.github.oliviercailloux.j_voting.mvc.gui;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class EditionView {
    private TabFolder tabfolder;
    private TabItem editionTab;
   
    public static EditionView create(TabFolder mainTabFolder) {
        return new EditionView(mainTabFolder);
    }

    private EditionView(TabFolder mainTabFolder) {
        this.tabfolder = mainTabFolder;
        initEditionTab();
    }

    // ca va évoluer ça (peut etre passer dans le controller)
    private void initEditionTab() {
        this.editionTab = new TabItem(this.tabfolder, SWT.NONE);
        editionTab.setText("Edition");
    }
    
    public void displayVoters(String voterName) {
    	Composite voters = new Composite(this.tabfolder,SWT.NONE);
    	Text voter = new Text(voters, SWT.BORDER);
    	voter.setText(voterName);
    	voter.setBounds(10,10,100,25);
    	editionTab.setControl(voters);
    }
    
    public void displayAlternatives() {
    	//
    }

}
package io.github.oliviercailloux.j_voting.mvc.gui;


import java.util.ArrayList;

public class EditionController {
    private EditionView editionView;
    private Controller controller;

    public static EditionController create(EditionView editionView, Controller mainController) {
        return new EditionController(editionView, mainController);
    }

    private EditionController(EditionView editionView, Controller mainController) {
        this.editionView = editionView;
        this.controller = mainController;
        initEditionView();
    }

    // A voir jsp c'est ok comme logique
    private void initEditionView() {
        String voterName = this.controller.getModel().getVoter().toString();
        editionView.displayVoters(voterName);
        // Work in progress on donnera la list d'alternative du model
        editionView.displayAlternatives(new ArrayList<>());
    }


}

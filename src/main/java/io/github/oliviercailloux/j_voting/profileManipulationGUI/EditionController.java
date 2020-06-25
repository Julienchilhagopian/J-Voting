package io.github.oliviercailloux.j_voting.profileManipulationGUI;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import io.github.oliviercailloux.j_voting.Alternative;

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

	/**
	 * Display the default edition view when user enters the gui
	 */
	private void initEditionView() {
		String voterName = this.controller.getModel().getVoter().toString();
		editionView.displayVoters(voterName);

		Set<Alternative> a = this.controller.getModel().getAlternatives();
		editionView.displayAlternatives(a);
		editionView.attachAddAlternativeListener(this.buildAddAlternativeBehavior());
	}

	/**
	 * Builds the behavior to add an alternative in the view
	 * 
	 * @return the behavior to attach to a SelectionListener
	 */
	private SelectionAdapter buildAddAlternativeBehavior() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button btnData = (Button) e.getSource();
				handleAddAlternative(btnData);
			}
		};
	}

	/**
	 * Definition of the add alternative behavior to execute when the corresponding
	 * button is clicked.
	 * 
	 * @param btn the button clicked by the user.
	 */
	private void handleAddAlternative(Button btn) {
		String textFieldContent = editionView.getTextFieldContent(btn);

		// Method found on stackoverflow to check whether the provided string is a
		// number
		Matcher matcher = Pattern.compile("[0-9]*+$").matcher(textFieldContent);
		if (!matcher.matches()) {
			editionView.setUserIndicationText("Not a number");
			return;
		}

		Integer textFieldId = Integer.parseInt(textFieldContent);
		Alternative newAlt = Alternative.withId(textFieldId);

		if (controller.getModel().getAlternatives().contains(newAlt)) {
			editionView.setUserIndicationText("Alternative already exists");
			return;
		}

		editionView.cleanAltContent();
		controller.getModel().addAlternative(newAlt);
		editionView.displayAlternatives(controller.getModel().getAlternatives());
		editionView.attachAddAlternativeListener(this.buildAddAlternativeBehavior());
	}

}
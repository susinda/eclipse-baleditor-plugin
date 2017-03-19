package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class BALEditor extends TextEditor {

	private ColorManager colorManager;

	public BALEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new BALSourceConfiguration(colorManager));
		setDocumentProvider(new BALDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}

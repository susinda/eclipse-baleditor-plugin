package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;


public class BALSourceConfiguration extends SourceViewerConfiguration {
	
	private CodeScanner codeScanner;
	private AnnotationScanner annotationScanner;
	private CommentScanner commentScanner;
	private ColorManager colorManager;

	public BALSourceConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			BALPartitionScanner.ANNOTATION,
			BALPartitionScanner.COMMENT };
	}

	protected CodeScanner getBALCodeScanner() {
		if (codeScanner == null) {
			codeScanner = new CodeScanner(colorManager);
			codeScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IBALColorConstants.DEFAULT_BLACK))));
		}
		return codeScanner;
	}
	
	protected AnnotationScanner getBALAnotationScanner() {
		if (annotationScanner == null) {
			annotationScanner = new AnnotationScanner(colorManager);
			annotationScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IBALColorConstants.ANNOTATION_GREY))));
		}
		return annotationScanner;
	}
	
	protected CommentScanner getBALCommentScanner() {
		if (commentScanner == null) {
			commentScanner = new CommentScanner(colorManager);
			commentScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IBALColorConstants.GREEN_COMMENT))));
		}
		return commentScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getBALCodeScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		dr = new DefaultDamagerRepairer(getBALAnotationScanner());
		reconciler.setDamager(dr, BALPartitionScanner.ANNOTATION);
		reconciler.setRepairer(dr, BALPartitionScanner.ANNOTATION);
		
		dr = new DefaultDamagerRepairer(getBALCommentScanner());
		reconciler.setDamager(dr, BALPartitionScanner.COMMENT);
		reconciler.setRepairer(dr, BALPartitionScanner.COMMENT);

		return reconciler;
	}

}
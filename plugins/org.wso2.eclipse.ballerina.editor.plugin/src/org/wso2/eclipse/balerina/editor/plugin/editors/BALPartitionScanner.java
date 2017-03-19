package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.rules.*;


public class BALPartitionScanner extends RuleBasedPartitionScanner {
	public final static String ANNOTATION = "__bal_annotation";
	public final static String COMMENT = "__bal_comment";

	public BALPartitionScanner() {
		
		IPredicateRule[] rules = new IPredicateRule[2];	
		
		IToken annotation = new Token(ANNOTATION);
		rules[0] = new EndOfLineRule("@", annotation);
		
		IToken comment = new Token(COMMENT);
		rules[1] = new EndOfLineRule("//", comment); 
		
		setPredicateRules(rules);
	}
}
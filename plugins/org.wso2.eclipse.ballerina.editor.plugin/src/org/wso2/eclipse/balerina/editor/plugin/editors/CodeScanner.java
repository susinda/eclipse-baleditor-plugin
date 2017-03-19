package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class CodeScanner extends RuleBasedScanner {

	public CodeScanner(ColorManager manager) {
	    
		IToken procInstr = new Token(new TextAttribute(manager.getColor(IBALColorConstants.KEYWORD_PURPLE)));
		IToken stringToken = new Token(new TextAttribute(manager.getColor(IBALColorConstants.STRUNG_BLUE)));

		IRule[] rules = new IRule[4];
		
		// Add rule for processing string
		rules[0] = new SingleLineRule("\"", "\"", stringToken, '\\');
		// Add rule for processing string
		rules[1] = new SingleLineRule("\'", "\'", stringToken, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new BALWhitespaceDetector());

		// Add rule for keywords
		WordRule wordRule = new WordRule(new WordDetector());
		wordRule.addWord("import", procInstr);
		wordRule.addWord("service", procInstr);
		wordRule.addWord("resource", procInstr);
		rules[3] = wordRule;

		setRules(rules);
	}

	class WordDetector implements IWordDetector {
		@Override
		public boolean isWordStart(final char c) {
			return Character.isLetter(c);
		}

		@Override
		public boolean isWordPart(final char c) {
			return Character.isLetter(c);
		}
	}
}

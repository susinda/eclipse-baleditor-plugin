package org.wso2.eclipse.ballerina.editor.plugin.editors;

public class TextInfo {
	
	String susbString;
	int offset;
	boolean found;
	
	public TextInfo(String susbString, int offset, boolean val) {
		this.susbString = susbString;
		this.offset = offset;
		this.found = val;
	}

	public String getSusbString() {
		return susbString;
	}
	
	public void setSusbString(String susbString) {
		this.susbString = susbString;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public boolean isFound() {
		return found;
	}
	
	public void setFound(boolean found) {
		this.found = found;
	}
}

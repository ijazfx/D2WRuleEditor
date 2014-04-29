package net.openkoncept.d2w;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The data structure that holds single rule.
 * 
 * @author ijazfx
 */
public class D2WRule {

	public static final Pattern RULE_PATTERN = Pattern.compile("(\\d+)\\s*:\\s*(.+)\\s*=>\\s*(.+)\\s*=\\s*(.+)\\s*\\[([\\w\\d_.-]+)]\\s*");

	private int priority;
	private String condition;
	private String assignmentType;
	private String key;
	private String value;

	public D2WRule() {
	}

	public D2WRule(int priority, String condition, String assignmentType, String key, String value) {
		setPriority(priority);
		setCondition(condition);
		setAssignmentType(assignmentType);
		setKey(key);
		setValue(value);
	}

	public static D2WRule parse(String text) {
		if (text == null)
			return null;
		Matcher m = RULE_PATTERN.matcher(text);
		if (m.find()) {
			D2WRule rule = new D2WRule();
			rule.setPriority(Integer.parseInt(m.group(1).trim()));
			rule.setCondition(m.group(2).trim());
			rule.setKey(m.group(3).trim());
			rule.setValue(m.group(4).trim());
			rule.setAssignmentType(m.group(5).trim());
			System.err.println(rule);
		}
		return null;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("%d : %s => %s = %s [%s]", getPriority(), getCondition(), getKey(), getValue(), getAssignmentType());
	}

}

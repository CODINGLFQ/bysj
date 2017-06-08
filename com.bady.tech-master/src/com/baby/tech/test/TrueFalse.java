package com.baby.tech.test;

public class TrueFalse {
	private int mQuestion;
	private boolean mTrueQuestion;
	private boolean ischeater;
	
	public boolean isIscheater() {
		return ischeater;
	}
	public void setIscheater(boolean ischeater) {
		this.ischeater = ischeater;
	}
	public TrueFalse(int question, boolean trueQuestion) {
		super();
		mQuestion = question;
		mTrueQuestion = trueQuestion;
	}
	public int getQuestion() {
		return mQuestion;
	}
	public void setQuestion(int question) {
		mQuestion = question;
	}
	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}
	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}
	
}

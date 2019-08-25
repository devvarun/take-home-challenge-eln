/**
 * 
 */
package com.labforward.eln.model;

import java.util.List;

/**
 * @author Dev
 * Response model
 *
 */
public class DataUtilResModel {

	private String keyword;
	private int frequency;
	private List<String> similarWords;
	
	public DataUtilResModel(){
		
	}

	public DataUtilResModel(String keyword, List<String> similarWords, int frequency){
		this.frequency = frequency;
		this.keyword = keyword;
		this.similarWords = similarWords;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public List<String> getSimilarWords() {
		return similarWords;
	}

	public void setSimilarWords(List<String> similarWords) {
		this.similarWords = similarWords;
	}
	
	
	
}

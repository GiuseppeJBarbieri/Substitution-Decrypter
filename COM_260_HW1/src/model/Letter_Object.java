package model;

public class Letter_Object {

	private String letter;
	private Double frequency;
	private Integer count;

	public Letter_Object(String letter, Double frequency, Integer count) {
		super();
		this.letter = letter;
		this.frequency = frequency;
		this.count = count;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}

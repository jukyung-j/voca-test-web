package voca_test;

public class VocaTestDO {
	private int idx;
	private String eng;
	private String kor;
	
	public VocaTestDO() {
		
	}
	public VocaTestDO(int idx, String eng, String kor) {
		super();
		this.idx = idx;
		this.eng = eng;
		this.kor = kor;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getEng() {
	
		return eng;
	}
	public void setEng(String eng) {
		
		this.eng = eng;
	}
	public String getKor() {
		return kor;
	}
	public void setKor(String kor) {
		this.kor = kor;
	}
	
}

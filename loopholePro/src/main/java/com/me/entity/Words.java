package com.me.entity;

public class Words {

	private Integer id;
	private String word; 		//单词
	private String prop;		//词性
	private String meaning;		//词义
	private String root;		//词根
	private String rootMeaning;	//词根含义
	private String handoutPage;	//讲义页码
	private String pronunciation;//美音音标
	private String maleVoice;	//美音男声
	private String antonym;		//反义词
	private String synonym;		//相近词
	private Integer bookTag;	//相近词
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getAntonym() {
		return antonym;
	}

	public void setAntonym(String antonym) {
		this.antonym = antonym;
	}

	public String getSynonym() {
		return synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	public Words() {
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getRootMeaning() {
		return rootMeaning;
	}
	public void setRootMeaning(String rootMeaning) {
		this.rootMeaning = rootMeaning;
	}
	public String getHandoutPage() {
		return handoutPage;
	}
	public void setHandoutPage(String handoutPage) {
		this.handoutPage = handoutPage;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getMaleVoice() {
		return maleVoice;
	}
	public void setMaleVoice(String maleVoice) {
		this.maleVoice = maleVoice;
	}

	public Integer getBookTag() {
		return bookTag;
	}

	public void setBookTag(Integer bookTag) {
		this.bookTag = bookTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((antonym == null) ? 0 : antonym.hashCode());
		result = prime * result + ((bookTag == null) ? 0 : bookTag.hashCode());
		result = prime * result
				+ ((handoutPage == null) ? 0 : handoutPage.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((maleVoice == null) ? 0 : maleVoice.hashCode());
		result = prime * result + ((meaning == null) ? 0 : meaning.hashCode());
		result = prime * result
				+ ((pronunciation == null) ? 0 : pronunciation.hashCode());
		result = prime * result + ((prop == null) ? 0 : prop.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result
				+ ((rootMeaning == null) ? 0 : rootMeaning.hashCode());
		result = prime * result + ((synonym == null) ? 0 : synonym.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Words other = (Words) obj;
		if (antonym == null) {
			if (other.antonym != null)
				return false;
		} else if (!antonym.equals(other.antonym))
			return false;
		if (bookTag == null) {
			if (other.bookTag != null)
				return false;
		} else if (!bookTag.equals(other.bookTag))
			return false;
		if (handoutPage == null) {
			if (other.handoutPage != null)
				return false;
		} else if (!handoutPage.equals(other.handoutPage))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maleVoice == null) {
			if (other.maleVoice != null)
				return false;
		} else if (!maleVoice.equals(other.maleVoice))
			return false;
		if (meaning == null) {
			if (other.meaning != null)
				return false;
		} else if (!meaning.equals(other.meaning))
			return false;
		if (pronunciation == null) {
			if (other.pronunciation != null)
				return false;
		} else if (!pronunciation.equals(other.pronunciation))
			return false;
		if (prop == null) {
			if (other.prop != null)
				return false;
		} else if (!prop.equals(other.prop))
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (rootMeaning == null) {
			if (other.rootMeaning != null)
				return false;
		} else if (!rootMeaning.equals(other.rootMeaning))
			return false;
		if (synonym == null) {
			if (other.synonym != null)
				return false;
		} else if (!synonym.equals(other.synonym))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	
	
	
}

package com.mario.diary.model;

public class Diary {
	private long lDiaryId;
	private String strContentDiary;
	private String iEventId;
	private long lCreatedDate;

	public Diary() {
	}

	public Diary(long diaryId, String content, String eventId, long createdDate) {
		this.lDiaryId = diaryId;
		this.strContentDiary = content;
		this.iEventId = eventId;
		this.lCreatedDate = createdDate;
	}

	public String getContentDiary() {
		return strContentDiary;
	}

	public void setContentDiary(String strContentDiary) {
		this.strContentDiary = strContentDiary;
	}

	public String getEventId() {
		return iEventId;
	}

	public void setEventId(String iEventId) {
		this.iEventId = iEventId;
	}

	public long getCreatedDate() {
		return lCreatedDate;
	}

	public void setCreatedDate(long lCreatedDate) {
		this.lCreatedDate = lCreatedDate;
	}

	public long getDiaryId() {
		return lDiaryId;
	}

	public void setDiaryId(long lDiaryId) {
		this.lDiaryId = lDiaryId;
	}

}

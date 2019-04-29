package com.github.domain.repository.entity;


import java.io.Serializable;

public class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String appuUid;
	private String language;
	private String name;
	private String showName;
	private String groupUuid;
	private int importance;
	private String path;

	public String getGroupUuid() {
		return groupUuid;
	}

	public void setGroupUuid(String groupUuid) {
		this.groupUuid = groupUuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppuUid() {
		return appuUid;
	}

	public void setAppuUid(String appuUid) {
		this.appuUid = appuUid;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

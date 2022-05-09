package com.goodee.ex01.java01;

public class Singer {

	private String name;
	private Song song;

	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}

	public void info() {
		System.out.println("name : " + name);
		System.out.println("title : " + song.getTitle());
		System.out.println("genre : " + song.getGenre());
	}

}
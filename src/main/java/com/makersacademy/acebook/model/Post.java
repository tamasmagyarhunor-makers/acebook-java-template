package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int likes;
    private int dislikes;

    public Post() {}

    public Post(String content) {
        this.content = content;
        this.likes = 0;
        this.dislikes = 0;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

}

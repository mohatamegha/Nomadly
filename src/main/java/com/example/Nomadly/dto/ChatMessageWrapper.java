package com.example.Nomadly.dto;

public class ChatMessageWrapper {

    private Long id;

    private Long travelId;

    private Long userId;


    private String content;


    public ChatMessageWrapper() {
    }

    public ChatMessageWrapper(Long id, Long travelId, Long userId, String content) {
        this.id = id;
        this.travelId = travelId;
        this.userId = userId;
        this.content = content;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travel) {
        this.travelId = travel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}


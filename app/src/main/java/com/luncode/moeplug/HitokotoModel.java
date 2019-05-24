package com.luncode.moeplug;

public class HitokotoModel {
    private String id;
    private String hitokoto;
    private String from;
    private String creator;

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public String getId() {
        return id;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public String getFrom() {
        return from;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}

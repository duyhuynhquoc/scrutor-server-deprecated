package dtos;

import java.util.UUID;

public class Tag {
    private String tagId;
    private String name;

    public Tag() {
        this.tagId = UUID.randomUUID().toString();
        this.name = "";
    }

    public Tag(String name) {
        this.tagId = UUID.randomUUID().toString();
        this.name = name;
    }

    public Tag(String tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

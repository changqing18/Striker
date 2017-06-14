package model;

/**
 * Created by 28713 on 2017/6/10.
 */
public class Article {
    private int id;
    private int type;
    private String head;
    private String cover;
    private String content;

    public Article(int id, int type, String head, String cover, String content) {
        this.id = id;
        this.type = type;
        this.head = head;
        this.cover = cover;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getHead() {
        return head;
    }

    public String getCover() {
        return cover;
    }

    public String getContent() {
        return content;
    }
}

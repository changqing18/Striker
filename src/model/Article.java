package model;

/**
 * Created by 28713 on 2017/6/10.
 */
public class Article {
    private int id;
    private String head;
    private String content;
    private String cover;

    public Article(int id, String head, String content, String cover) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public String getHead() {
        return head;
    }

    public String getContent() {
        return content;
    }

    public String getCover() {
        return cover;
    }
}

package model;

/**
 * Created by 28713 on 2017/6/13.
 */
public class ArticleInfo {
    private int id;
    private String head;
    private String cover;

    public ArticleInfo(int id, String head, String cover) {
        this.id = id;
        this.head = head;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public String getHead() {
        return head;
    }

    public String getCover() {
        return cover;
    }
}

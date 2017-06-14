package model;

/**
 * Created by 28713 on 2017/6/13.
 */
public class ArticleType {
    private int type;
    private int start;

    public ArticleType(int type, int start) {
        this.type = type;
        this.start = start;
    }

    public int getType() {
        return type;
    }

    public int getStart() {
        return start;
    }
}

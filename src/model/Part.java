package model;

public class Part {

    private int partId;
    private String partName;
    private String articleNumber;

    public Part() {
    }

    public Part(int partId, String partName, String articleNumber) {
        this.partId = partId;
        this.partName = partName;
        this.articleNumber = articleNumber;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }
}
package concur.hack.policyaudit.activity;

/**
 * Created by RatanK on 17/07/2014.
 */
public class ImageItem
{
    private String imageName;
    private int drawableId;

    public ImageItem(String imageName, int drawableId)
    {
        this.imageName = imageName;
        this.drawableId = drawableId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
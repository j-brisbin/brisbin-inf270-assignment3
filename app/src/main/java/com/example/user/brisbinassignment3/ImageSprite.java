package com.example.user.brisbinassignment3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by gibsond on 9/17/2015.
 */
public class ImageSprite extends Sprite
{
    private Bitmap image;
    private boolean isVisible;
    private Resources resources;

    public ImageSprite(Resources resources, int resourceId, int x, int y)
    {
        super(x,y,0,0);
        this.resources = resources;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        image = BitmapFactory.decodeResource(resources, resourceId,options);


        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());
        this.isVisible = true;
        this.setIsSpriteVisible(false);

    }

    protected Bitmap getImage()
    {
        return image;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }



    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        if(isVisible)
        {
            canvas.drawBitmap(image,this.getX(),this.getY(),this.getPaint());
        }

    }
}

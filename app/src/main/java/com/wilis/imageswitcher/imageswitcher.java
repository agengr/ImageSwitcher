package com.wilis.imageswitcher;
 
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
 
public class imageswitcher extends Activity 
implements ViewFactory
{    
    //---the images to display---
    Integer[] imageIDs = {
            R.drawable.bandung1,
            R.drawable.bandung2,
            R.drawable.bandung3,
            R.drawable.bandung4,
            R.drawable.bandung5,
            R.drawable.bandung6,
            R.drawable.bandung7                    
    };
 
    private ImageSwitcher imageSwitcher;
 
    @Override    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayview);
 
        imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher1);
        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
 
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
            {                
            	imageSwitcher.setImageResource(imageIDs[position]);
            }
        });  
    }
 
    public View makeView() 
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(0xFF000000);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new 
                ImageSwitcher.LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.FILL_PARENT));
        return imageView;
    }
 
    public class ImageAdapter extends BaseAdapter 
    {
        private Context context;
        private int itemBackground;
 
        public ImageAdapter(Context c) 
        {
            context = c;
 
                                                           
        }
 
        //---returns the number of images---
        public int getCount() 
        {
            return imageIDs.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position) 
        {
            return position;
        }
 
        public long getItemId(int position) 
        {
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
   }    
    }
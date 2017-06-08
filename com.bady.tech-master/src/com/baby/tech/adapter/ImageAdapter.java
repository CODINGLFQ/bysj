/**
 * ImageAdapter.java V1.0 2016-4-24 上午8:56:34
 *
 * Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By Time Reason):
 *
 * Description:
 */

package com.baby.tech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baby.tech.R;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    private Integer[] images = {
            // 九宫格图片的设置
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher,

            R.drawable.ic_launcher,

    };

    private String[] texts = {
            // 九宫格图片下方文字的设置
            "儿童音乐", "动漫视频", "儿童故事",

             "看图识字", };

    // get the number
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    // get the current selector's id number
    @Override
    public long getItemId(int position) {
        return position;
    }

    // create view method
    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {
        ImgTextWrapper wrapper;
        if (view == null) {
            wrapper = new ImgTextWrapper();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item, null);
            view.setTag(wrapper);
            view.setPadding(15, 15, 15, 15); // 每格的间距
        } else {
            wrapper = (ImgTextWrapper) view.getTag();
        }

        wrapper.imageView = (ImageView) view
                .findViewById(R.id.MainActivityImage);
        wrapper.imageView.setBackgroundResource(images[position]);
        wrapper.textView = (TextView) view.findViewById(R.id.MainActivityText);
        wrapper.textView.setText(texts[position]);

        return view;
    }
}

class ImgTextWrapper {
    ImageView imageView;
    TextView textView;

}

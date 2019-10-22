package com.geektech.androidthird.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.geektech.androidthird.R;
import com.geektech.androidthird.data.ViewpagerData;
import java.util.ArrayList;

public class OnBoardAdapter extends PagerAdapter {

    private ArrayList<ViewpagerData> data;

    OnBoardAdapter(ArrayList<ViewpagerData> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_view_pager, container, false);
        ImageView onBoardImage = layout.findViewById(R.id.it_imageView);
        TextView textViewItem = layout.findViewById(R.id.textView);

        ViewpagerData item = data.get(position);
        onBoardImage.setImageDrawable(container.getContext().getResources().getDrawable(item.getImage()));
        textViewItem.setText(item.getTitle());
        container.addView(layout);
        return layout;
    }
}



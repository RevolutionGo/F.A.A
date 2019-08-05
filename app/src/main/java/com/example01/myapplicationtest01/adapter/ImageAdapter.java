package com.example01.myapplicationtest01.adapter;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example01.myapplicationtest01.R;
import com.example01.myapplicationtest01.domain.Image;
import com.example01.myapplicationtest01.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Image> datas = new ArrayList<Image>();
    private final Context context;
    private final LayoutInflater inflater;

    public ImageAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //此处一定要将parent传递到inflate方法
        return new ViewHolder(inflater.inflate(R.layout.item_image,parent,false));
    }

    public void onBindViewHolder(ViewHolder holder,int position) {
        holder.bindData(datas.get(position));
       if (onItemClickListener != null) {
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemClickListener.onItemClick(position);
               }
           });
       }
    }

    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<Image> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        //刷新数据
        notifyItemRangeInserted(0,this.datas.size());
    }

    public Image getData(int position) {
        return datas.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }

        public void bindData(Image image) {
            //显示图片
            ImageUtil.show((Activity) context,iv,image.getUri());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

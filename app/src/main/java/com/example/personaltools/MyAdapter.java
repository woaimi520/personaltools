package com.example.personaltools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 *
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnTouchListener{
    private List<Book>mBookList;
    private OnClickListener listener;
    /**
     * ttep 2
     * 加载item 的布局 创建viewHolder实例
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_layout, viewGroup, false);
        MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
        return viewHolder;
    }

    /**
     * ttep 4
     * 为子项赋值
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Book book = mBookList.get(position);
        viewHolder.textView.setText(book.getName());
        viewHolder.itemView.setTag(position);
        viewHolder.itemView.setOnTouchListener(this);
        viewHolder.button.setTag(position);

    }

    /**
     * step 1
     *
     * 返回子项个数
     * 调用顺序
     *
     * getItemCount
     *
     * getItemViewType
     *
     * onCreateViewHolder
     *
     * onBindViewHolde
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    /**
     * ttep 0
     * @param mBookList
     */
    public MyAdapter(List<Book> mBookList) {
        this.mBookList = mBookList;
    }





    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int i = event.getAction();
        if(i==MotionEvent.ACTION_DOWN) {
            Toast.makeText(v.getContext(), "onTouch", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * ttep 3
     */
      class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
         TextView textView;
        @BindView(R.id.button)
        Button button;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick({R.id.button,R.id.item})
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            switch (v.getId()) {
                case R.id.button:
                    Toast.makeText(v.getContext(), "点击 button position="+position, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    listener.OnClick(mBookList, position);
                    break;
            }
        }
        @OnLongClick({R.id.button,R.id.item})
        public boolean onLongClick(View v){
            int position = (Integer) v.getTag();
            switch (v.getId()) {
                case R.id.button:
                    Toast.makeText(v.getContext(), "长点击 button position="+position, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    listener.OnClick(mBookList, position);
                    break;
            }
            return false;
        }
    }












    public interface OnClickListener {

        void OnClick(List<Book>mBookList,int position);

        void OnLongClick(List<Book>mBookList,int position);

    }

    public void setOnclckListener(OnClickListener listener){
        this.listener = listener;
    }





}

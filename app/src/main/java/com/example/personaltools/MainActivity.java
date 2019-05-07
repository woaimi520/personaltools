package com.example.personaltools;

import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements MyAdapter.OnClickListener{

    @BindView(R.id.recy_01)
    RecyclerView mRecytclerView01;
    @BindView(R.id.recy_02)
    RecyclerView mRecytclerView02;

    private List<Book> mList01 = new ArrayList<Book>();
    private List<Book> mList02 = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBooks();
        initRecyclerView();

    }

    public void initRecyclerView() {
        try {
            LinearLayoutManager lin01 = new LinearLayoutManager(this);
            lin01.setOrientation(LinearLayoutManager.VERTICAL);   //这里是设置里面内容怎么排列的

            LinearLayoutManager lin02 = new LinearLayoutManager(this);
            lin02.setOrientation(LinearLayoutManager.VERTICAL);

            mRecytclerView01.setLayoutManager(lin01);
            mRecytclerView01.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mRecytclerView02.setLayoutManager(lin02);
            mRecytclerView02.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


            MyAdapter adapter01 = new MyAdapter(mList01);
            MyAdapter adapter02 = new MyAdapter(mList02);


            adapter01.setOnclckListener(this);
            mRecytclerView01.setAdapter(adapter01);
            mRecytclerView01.addOnScrollListener(new RecyclerView.OnScrollListener() {

                                                     /**
                                                      *
                                                      * @param recyclerView
                                                      * @param newState 当前滚动状态  SCROLL_STATE_IDLE 0：没滚动 ，SCROLL_STATE_DRAGGING 1：手指拉着滚动 ，CROLL_STATE_SETTLING 2 自动惯性滚动
                                                      */
                                                     @Override
                                                     public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                                         super.onScrollStateChanged(recyclerView, newState);

//                                                        int ii= recyclerView.computeVerticalScrollExtent();//显示的
//                                                         int iii=recyclerView.computeVerticalScrollOffset();//偏移位置 测试最小为0
//                                                         int iiii= recyclerView.computeVerticalScrollRange();//整个高度 包括没有显示的

                                                         //1 表示手指向上滑动（屏幕向下滑动）   到底返回false
                                                         //-1 表示手指向下滑动（屏幕向上滑动）  到顶返回false
//                                                         例如：
//                                                         RecyclerView.canScrollVertically(1)的值表示是否能向下滚动，false表示已经滚动到底部
//                                                         RecyclerView.canScrollVertically(-1)的值表示是否能向上滚动，false表示已经滚动到顶部
                                                         if(newState==RecyclerView.SCROLL_STATE_IDLE) {
                                                             if (!recyclerView.canScrollVertically(1)) {
                                                                 if(recyclerView.computeVerticalScrollExtent()==recyclerView.computeVerticalScrollRange()){
                                                                     Toast.makeText(MainActivity.this, "已到顶" + newState, Toast.LENGTH_SHORT).show();
                                                                 } else {
                                                                     Toast.makeText(MainActivity.this, "已经到达底部=" + newState, Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }

                                                             if (!recyclerView.canScrollVertically(-1)) {
                                                                 if(recyclerView.computeVerticalScrollExtent()==recyclerView.computeVerticalScrollRange()){
                                                                     Toast.makeText(MainActivity.this, "已到顶" + newState, Toast.LENGTH_SHORT).show();
                                                                 } else {
                                                                     Toast.makeText(MainActivity.this, "已经到达顶部=" + newState, Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }
                                                         }



                                                     }

                                                     /**
                                                      * @param recyclerView
                                                      * @param dx  >0 向左拖动 《0 享有拖动
                                                      * @param dy  >0 向上拖动 《0 向下拖动
                                                      */
                                                     @Override
                                                     public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                                         super.onScrolled(recyclerView, dx, dy);





                                                     }
                                                 }




            );


            adapter02.setOnclckListener(this);
            mRecytclerView02.setAdapter(adapter02);
            mRecytclerView02.addOnScrollListener(new RecyclerView.OnScrollListener() {

                                                     /**
                                                      *
                                                      * @param recyclerView
                                                      * @param newState 当前滚动状态  SCROLL_STATE_IDLE 0：没滚动 ，SCROLL_STATE_DRAGGING 1：手指拉着滚动 ，CROLL_STATE_SETTLING 2 自动惯性滚动
                                                      */
                                                     @Override
                                                     public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                                         super.onScrollStateChanged(recyclerView, newState);

//                                                        int ii= recyclerView.computeVerticalScrollExtent();//显示的
//                                                         int iii=recyclerView.computeVerticalScrollOffset();//偏移位置 测试最小为0
//                                                         int iiii= recyclerView.computeVerticalScrollRange();//整个高度 包括没有显示的

                                                         //1 表示手指向上滑动（屏幕向下滑动）   到底返回false
                                                         //-1 表示手指向下滑动（屏幕向上滑动）  到顶返回false
//                                                         例如：
//                                                         RecyclerView.canScrollVertically(1)的值表示是否能向下滚动，false表示已经滚动到底部
//                                                         RecyclerView.canScrollVertically(-1)的值表示是否能向上滚动，false表示已经滚动到顶部
                                                         if(newState==RecyclerView.SCROLL_STATE_IDLE) {
                                                             if (!recyclerView.canScrollVertically(1)) {
                                                                 if(recyclerView.computeVerticalScrollExtent()==recyclerView.computeVerticalScrollRange()){
                                                                     Toast.makeText(MainActivity.this, "已到顶" + newState, Toast.LENGTH_SHORT).show();
                                                                 } else {
                                                                     Toast.makeText(MainActivity.this, "已经到达底部=" + newState, Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }

                                                             if (!recyclerView.canScrollVertically(-1)) {
                                                                 if(recyclerView.computeVerticalScrollExtent()==recyclerView.computeVerticalScrollRange()){
                                                                     Toast.makeText(MainActivity.this, "已到顶" + newState, Toast.LENGTH_SHORT).show();
                                                                 } else {
                                                                     Toast.makeText(MainActivity.this, "已经到达顶部=" + newState, Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }
                                                         }



                                                     }

                                                     /**
                                                      * @param recyclerView
                                                      * @param dx  >0 向左拖动 《0 享有拖动
                                                      * @param dy  >0 向上拖动 《0 向下拖动
                                                      */
                                                     @Override
                                                     public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                                         super.onScrolled(recyclerView, dx, dy);





                                                     }
                                                 }




            );






        }catch(Exception e){
            System.out.println("e=" + e);
        }
    }

    public void initBooks(){
        for (int i=0;i<100;i++){
            Book book01 = new Book(ChineseName.getName());
            mList01.add(book01);
        }

        for (int i=0;i<30;i++){
            Book book02 = new Book(ChineseName.getName());
            mList02.add(book02);
        }

    }

    @Override
    public void OnClick(List<Book>mBookList,int position) {
        Toast.makeText(MainActivity.this, "OnClick other 点击了 =" + position+"  name="+mBookList.get(position).getName(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void OnLongClick(List<Book>mBookList,int position) {
        Toast.makeText(MainActivity.this, "OnLongClick other 点击了 =" + position+"  name="+mBookList.get(position).getName(), Toast.LENGTH_LONG).show();
    }
}

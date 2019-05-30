package com.example.personaltools;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDialogFragment extends DialogFragment {
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;
    Myadapter myadapter;
    private List<Book> books;


    public static  MyDialogFragment newInstance( List<Book> books) {

        Bundle args = new Bundle();

        MyDialogFragment fragment = new MyDialogFragment();

//        Gson gson = new Gson();
//        String jsonBooks =gson.toJson(books);
//        args.putString("jsonBooks",jsonBooks);
//
//
//        String jsonBook =gson.toJson(books.get(0));
//        args.putString("jsonBook",jsonBook);

        args.putSerializable("books", (Serializable)books);
//        args.putSerializable("book0", (Serializable)books.get(0));

        fragment.setArguments(args);






        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {

            books = (List<Book>)bundle.getSerializable("books");
//
//            Book book = books.get(0);
//            book.setName("renyu");
//            Book book0=(Book) bundle.getSerializable("book0");


//            String booksJson = bundle.getString("jsonBooks");
//            Gson gson = new Gson();
//            List<Book> bellsGroupsList = gson.fromJson(booksJson, new TypeToken<List<Book>>(){}.getType());
//            Book book1=bellsGroupsList.get(0);

//
//            String bookJson = bundle.getString("jsonBook");
//            Book book2 = gson.fromJson(bookJson, Book.class);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog, null);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);   //这里是设置里面内容怎么排列的
        mRecyclerView.setLayoutManager(linearLayoutManager);

        myadapter = new Myadapter(books);

        mRecyclerView.setAdapter(myadapter);

        return view;

    }




    private class Myadapter extends RecyclerView.Adapter<MyViewHolder>{

        private List<Book> books;

        public Myadapter(List<Book> books) {
            this.books = books;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_layout, viewGroup, false);
            MyViewHolder holder = new MyViewHolder(view);



            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int positon) {

            viewHolder.button.setTag(positon);
            viewHolder.textView.setTag(positon);
            Book book = books.get(positon);
            viewHolder.textView.setText(book.getName());
            viewHolder.textView.setOnClickListener(v-> {

                    int position = (Integer) v.getTag();
                    switch (v.getId()) {
                        case R.id.button_open:
                            Toast.makeText(getContext(),"onclick + " + position, Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.textView:

                            Toast toast=  Toast.makeText(getActivity(),"onclick + " + position, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 100, 0);
                            toast.show();

                            break;
                    }




            }


            );

        }

        @Override
        public int getItemCount() {
            return books.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.button_open)
        Button button;
        @BindView(R.id.textView)
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick({R.id.button_open})
        public void  onClick(View view){
            int position = (Integer) view.getTag();
            switch (view.getId()) {
                case R.id.button_open:
                    Toast.makeText(getContext(),"onclick + " + position, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView:

                    Toast toast=  Toast.makeText(getActivity(),"onclick + " + position, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 100, 0);
                    toast.show();

                    break;
            }

        }




    }



}

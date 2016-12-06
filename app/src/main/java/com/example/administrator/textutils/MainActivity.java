package com.example.administrator.textutils;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText medZhi;
    PopupWindow popupWindow = null;
    private List<String> mList;
    private Button mBtn;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mBtn = (Button) findViewById(R.id.btn);
        lv = new ListView(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("111",i+"");

            }
        });
        lv.setAdapter(new MyAdapter());

    }

    private void initData() {
        mList = new ArrayList<String>();
        for (int i = 0; i < 100; i++){
            mList.add(i + "");
        }
    }

    public void showpopu(View view){




    if (popupWindow != null && popupWindow.isShowing()){
        popupWindow = null;
        popupWindow.dismiss();

    }//lv.listview
        popupWindow = new PopupWindow(lv,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * 以下3行直接抄上否则可能有问题
         */
        //要让其中的view获取焦点，必须设置为true
        popupWindow.setFocusable(true);
        //还必须设置一个背景图片
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击外部消失
        popupWindow.setOutsideTouchable(true);
        //在mbtn下面左上角坐标为0,0
        popupWindow.showAsDropDown(mBtn,  0, 0);


    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            Viewholder viewholder;
            if (view == null){
               viewholder = new Viewholder();
                view = View.inflate(MainActivity.this,R.layout.items,null);
                viewholder.tv = (TextView) view.findViewById(R.id.tvResult);
                view.setTag(viewholder);
            }else {
            viewholder = (Viewholder) view.getTag();
            }
            viewholder.tv .setText(mList.get(i));
            viewholder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("edit",i+"");
                }
            });

            return view;
        }
    }
    static class Viewholder{
        TextView tv;
    }
}

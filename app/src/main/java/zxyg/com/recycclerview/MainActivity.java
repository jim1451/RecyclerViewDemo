package zxyg.com.recycclerview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import zxyg.com.recycclerview.Dialog.CDialog;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView  recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = this.findViewById(R.id.recyclerView);
        ititData();
        adapter = new MyRecyclerViewAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter.setOnItemClickKistener(new MyRecyclerViewAdapter.OnItemClickKistener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "data=="+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view,final int position) {
             //   Toast.makeText(MainActivity.this, "hello"+data, Toast.LENGTH_SHORT).show();

               // AlertDialog.Builder  builder =
                CDialog.Builder builder = new CDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle("提示");
                builder.setMessage("你确定要删除该联系人吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeData(position);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }


        });


    }

    private void ititData() {
        data = new ArrayList<>();
        for (int i = 0;i < 50;i++){
            data.add("content"+i);
        }
    }
}

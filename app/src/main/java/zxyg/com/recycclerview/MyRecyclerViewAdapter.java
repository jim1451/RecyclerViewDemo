package zxyg.com.recycclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private final Context  context;
    private ArrayList<String> datas;

    public MyRecyclerViewAdapter(Context context,ArrayList<String> datas) {

        this.context = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View itemView  = View.inflate(context,R.layout.intem_recyclerview,null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String data = datas.get(i);
        viewHolder.tv_title.setText(data);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 删除数据
     * @param position
     */
    public  void  removeData(int position){
        datas.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * 添加shuju
     */

    public  void  addData(int position,String data){
        datas.add(position,data);
        notifyItemInserted(position);
        }





    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_icon;
        private TextView tv_title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title  = itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(new View.OnClickListener(){

                                            @Override
                                            public void onClick(View view) {

                                                if (onItemClickKistener != null){
                                              //      onItemClickKistener.onItemClick(view,datas.get(getLayoutPosition()));
                                                 //   int pos = holder.getLayoutPosition();
                                                        onItemClickKistener.onItemClick(view,getLayoutPosition());


                                                }
                                            }
                                        });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickKistener.onItemLongClick(view,getLayoutPosition());

                    return false;
                }
            });


            iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"ok!!!!",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


    public  interface  OnItemClickKistener{
        public  void onItemClick(View view,int position);
        public  void onItemLongClick(View view,int position);
    }
    private OnItemClickKistener onItemClickKistener;

    /**
     * 设置RecyckerView某个监听
     * @param onItemClickKistener
     */

    public void setOnItemClickKistener(OnItemClickKistener onItemClickKistener){
        this.onItemClickKistener = onItemClickKistener;
    }
}

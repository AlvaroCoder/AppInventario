package com.example.appinventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Mov extends RecyclerView.Adapter<Adapter_Mov.ViewHolder>{
    private List<Movimientos> listamovimientos;
    private Context context;
    private LayoutInflater mInflater;
    public Adapter_Mov(Context context, List<Movimientos> listamovimientos){
        this.mInflater = LayoutInflater.from(context);
        this.listamovimientos = listamovimientos;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtfec,txtproducto,txtmov;
        public ViewHolder(@NonNull View view) {
            super(view);
            txtfec = view.findViewById(R.id.txtfec);
            txtproducto = view.findViewById(R.id.txtproducto);
            txtmov = view.findViewById(R.id.txtmov);
        }
        void bindData(Movimientos movimientos){
            txtfec.setText(movimientos.getFecha());
            txtproducto.setText(movimientos.getInsumo());
            txtmov.setText(movimientos.getTipo());
        }

    }
    @NonNull
    @Override
    public Adapter_Mov.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.modelo_movimiento, parent, false);

        return new Adapter_Mov.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movimientos movimientos = listamovimientos.get(position);
        holder.txtfec.setText(movimientos.getFecha());
        holder.txtproducto.setText(movimientos.getInsumo());
        holder.txtmov.setText(movimientos.getTipo());
    }

    @Override
    public int getItemCount() {
        return listamovimientos.size();
    }
    public  void setmovimientos(List<Movimientos> listamovimientos){
        this.listamovimientos = listamovimientos;
    }

}

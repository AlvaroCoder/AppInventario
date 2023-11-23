package com.example.appinventario;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterLocales extends RecyclerView.Adapter<AdapterLocales.ViewHolder> {
    private List<Local> listaLocales;
    private  Context context;
    private LayoutInflater mInflater;
    public AdapterLocales(Context context, List<Local> listaLocales){
        this.mInflater = LayoutInflater.from(context);
        this.listaLocales = listaLocales;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtNombreLocal;
        public final LinearLayout llBoxLocales;
        public ViewHolder(@NonNull View view) {
            super(view);
            txtNombreLocal = view.findViewById(R.id.textViewLocales);
            llBoxLocales = view.findViewById(R.id.ll_boxLocales);
        }
        void bindData(Local local){
            txtNombreLocal.setText(local.getNombreLocal());
        }

    }
    @NonNull
    @Override
    public AdapterLocales.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.box_locales, parent, false);
        return new AdapterLocales.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Local local = listaLocales.get(position);
        holder.llBoxLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, menu_seleccionar_accion.class);
                intent.putExtra("idLocales", String.valueOf(local.getIdLocal()));
                context.startActivity(intent);
            }
        });
        holder.txtNombreLocal.setText(local.getNombreLocal());
    }

    @Override
    public int getItemCount() {
        return listaLocales.size();
    }
    public  void setLocal(List<Local> listaLocales){
        this.listaLocales = listaLocales;
    }


}

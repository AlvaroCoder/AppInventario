package com.example.appinventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public ViewHolder(@NonNull View view) {
            super(view);
            txtNombreLocal = view.findViewById(R.id.textViewLocales);
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

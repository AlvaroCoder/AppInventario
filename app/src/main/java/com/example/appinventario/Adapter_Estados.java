package com.example.appinventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Estados extends RecyclerView.Adapter<Adapter_Estados.ViewHolder> {
    private List<Estados> listaestados;
    private Context context;
    private LayoutInflater mInflater;
    public Adapter_Estados(Context context, List<Estados> listaestados){
        this.mInflater = LayoutInflater.from(context);
        this.listaestados = listaestados;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtmodelo1,txtmodelo2;
        public ViewHolder(@NonNull View view) {
            super(view);
            txtmodelo1 = view.findViewById(R.id.txtinsumofec);
            txtmodelo2 = view.findViewById(R.id.txtproducto2);
        }
        void bindData(Estados estados){
            txtmodelo1.setText(estados.getInsumo());
            txtmodelo2.setText(estados.getStock_actual());
        }

    }
    @NonNull
    @Override
    public Adapter_Estados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.modelo_estado, parent, false);

        return new Adapter_Estados.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Estados estados = listaestados.get(position);
        holder.txtmodelo1.setText(estados.getInsumo());
        holder.txtmodelo2.setText(String.valueOf(estados.getStock_actual()));
    }

    @Override
    public int getItemCount() {
        return listaestados.size();
    }
    public  void setestados(List<Estados> listaestados){
        this.listaestados = listaestados;
    }
}

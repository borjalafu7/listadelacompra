package com.bolapa.listadelacompra;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {
    Context context;
    ArrayList<Producto> productos;
    TextView compratotal;


    public myAdapter(Context context, ArrayList<Producto> productos,TextView compratotal) {
        this.context = context;
        this.productos = productos;
        this.compratotal = compratotal;
    }
    public void setData(Producto p){
        productos.add(0,p);

        notifyDataSetChanged();
        Log.i("array",String.valueOf(productos));
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        holder.tvPreciototal.setText(String.valueOf(productos.get(i).getCantidad()*productos.get(i).getPreciounitario()+"€"));
        holder.tvProducto.setText(productos.get(i).getProducto());
        holder.tvcantidad.setText(String.valueOf(productos.get(i).getCantidad()));
        holder.tvPrecioUnitario.setText(String.valueOf(productos.get(i).getPreciounitario()+"€"));


              holder.tvProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t = (TextView)view;
                //Toast.makeText(context,"has pulsado nombre : " + t.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.deleteproduct.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(productos.get(i).getCantidad()<=1){
                    productos.remove(i);
                    notifyDataSetChanged();
                }else{
                    productos.get(i).setCantidad(productos.get(i).getCantidad()-1);
                    holder.tvcantidad.setText(String.valueOf(productos.get(i).getCantidad()));
                    holder.tvPreciototal.setText(String.valueOf(productos.get(i).getCantidad()*productos.get(i).getPreciounitario())+"€");
                }

                Double preciototal=0.0;
                for(int i=0;i<productos.size();i++){
                    preciototal = preciototal+productos.get(i).getCantidad()*productos.get(i).getPreciounitario();
                }

                compratotal.setText(preciototal + "€");
            }
        });

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        //Creamos tantas variables como elementos tenga la vista que queramos vincular
        public TextView tvProducto, tvcantidad, tvPrecioUnitario, tvPreciototal, totalcompra;
        public ImageButton deleteproduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProducto = (TextView)itemView.findViewById(R.id.nombreproducto);
            tvcantidad = (TextView)itemView.findViewById(R.id.cantidadproducto);
            tvPrecioUnitario = (TextView)itemView.findViewById(R.id.preciounitarioproducto);
            tvPreciototal = (TextView)itemView.findViewById(R.id.preciototal);
            totalcompra = (TextView)itemView.findViewById(R.id.totalcompras);
            deleteproduct = (ImageButton)itemView.findViewById(R.id.deleteproduct);


        }
    }
}

package com.example.logind.ui.empalme;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.logind.DetallesEmpalmes;
import com.example.logind.R;
import com.example.logind.db.entidades.EmpalmeEntity;

import java.util.List;

import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_DISTANCIA;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_ID_E;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_NOMBRE_EMPALME;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_NUM_POSTE;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_OBSERVACION;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_TRAMO;


public class MyEmpalmesRecyclerViewAdapter extends RecyclerView.Adapter<MyEmpalmesRecyclerViewAdapter.ViewHolder>{

    private List<EmpalmeEntity> mValues;
    private Context ctx;
    private View.OnClickListener mListener;

    //metodos para mandar datos a detallesActivity


    public MyEmpalmesRecyclerViewAdapter(List<EmpalmeEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_empalme, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewempalme.setText(holder.mItem.getNombre_e());
        holder.textViewtramo.setText("TRAMO:   " + holder.mItem.getTramo());
        holder.textViewdistancia.setText("DISTANCIA:               " + Double.toString(holder.mItem.getDistancia()) + " Km.");
        holder.textViewnumPoste.setText("NUMERO DE POSTE:              " + holder.mItem.getNumero_poste());
        holder.textViewObservacion.setText(holder.mItem.getObservacion());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(ctx,ActivityActualizarEmpalme.class);
                ctx.startActivity(i);*/
                Toast.makeText(ctx, "Actualiza o Elimina", Toast.LENGTH_SHORT).show();
                Intent detailIntent = new Intent(ctx, DetallesEmpalmes.class);
                EmpalmeEntity clickedItem = mValues.get(position);
                detailIntent.putExtra(EXTRA_ID_E,String.valueOf(clickedItem.getId_e()));
                detailIntent.putExtra(EXTRA_NOMBRE_EMPALME,clickedItem.getNombre_e());
                detailIntent.putExtra(EXTRA_TRAMO,clickedItem.getTramo());
                detailIntent.putExtra(EXTRA_DISTANCIA,String.valueOf(clickedItem.getDistancia()));
                detailIntent.putExtra(EXTRA_NUM_POSTE,clickedItem.getNumero_poste());
                detailIntent.putExtra(EXTRA_OBSERVACION,clickedItem.getObservacion());

               ctx.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevoEmpalme(List<EmpalmeEntity> nuevoEmpalme) {
        this.mValues = nuevoEmpalme;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewempalme;
        public final TextView textViewtramo;
        public final TextView textViewdistancia;
        public final TextView textViewnumPoste;
        public final TextView textViewObservacion;

        public EmpalmeEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewempalme = view.findViewById(R.id.txtEmpalme);
            textViewtramo = view.findViewById(R.id.txtTramo);
            textViewdistancia = view.findViewById(R.id.txtDistancia);
            textViewnumPoste = view.findViewById(R.id.txtNumposte);
            textViewObservacion = view.findViewById(R.id.txtObservacion);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }

                    }
                }
            });*/
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewempalme.getText() + "'";
        }
    }
}

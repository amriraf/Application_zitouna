package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.VersionViewHolder> {

    public static class VersionViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView cotation;
        TextView NCotation;
        TextView Agence;
        TextView CodeAgence;

        TextView Inter;

        TextView Nom_client;
        TextView Num_tele;
        TextView CIN;
        TextView Adresse_client;
        TextView code_postal;

        TextView Gouvernanrt;
        TextView Ville;
        TextView Etat;
        VersionViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardview);
            cotation = (TextView)view.findViewById(R.id.title);
            NCotation = (TextView)view.findViewById(R.id.txtName);
            Agence = (TextView)view.findViewById(R.id.txtSurname);
            CodeAgence = (TextView)view.findViewById(R.id.txtEmail);

            Inter = (TextView)view.findViewById(R.id.Inter);
            Nom_client = (TextView)view.findViewById(R.id.Nom_client);
            Num_tele = (TextView)view.findViewById(R.id.N_tele);
            CIN = (TextView)view.findViewById(R.id.NUm_CIN);

            Adresse_client = (TextView)view.findViewById(R.id.Adresse_client);
            code_postal = (TextView)view.findViewById(R.id.code_postal);
            Gouvernanrt = (TextView)view.findViewById(R.id.Gouvernorat);
            Ville = (TextView)view.findViewById(R.id.Ville);
            Etat = (TextView)view.findViewById(R.id.Etat);

        }
    }

    List<Mes_taches> mes_taches_List;

    public RecyclerViewAdapter(List<Mes_taches> mes_taches_List) {
        this.mes_taches_List = mes_taches_List;
    }


    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.androidversionitem,viewGroup,false);
        VersionViewHolder holder = new VersionViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mes_taches_List.size();
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        //versionViewHolder.imgView.setImageResource(mes_taches_List.get(i).imgID);
        versionViewHolder.cotation.setText(mes_taches_List.get(i).cotation);
        versionViewHolder.NCotation.setText(mes_taches_List.get(i).NCotation);
        versionViewHolder.Agence.setText(mes_taches_List.get(i).Agence);
        versionViewHolder.CodeAgence.setText(mes_taches_List.get(i).CodeAgence);

        versionViewHolder.Inter.setText(mes_taches_List.get(i).Inter);
        versionViewHolder.Nom_client.setText(mes_taches_List.get(i).Nom_client);
        versionViewHolder.Num_tele.setText(mes_taches_List.get(i).Num_tele);
        versionViewHolder.CIN.setText(mes_taches_List.get(i).CIN);

        versionViewHolder.Adresse_client.setText(mes_taches_List.get(i).Adresse_client);
        versionViewHolder.code_postal.setText(mes_taches_List.get(i).code_postal);
        versionViewHolder.Gouvernanrt.setText(mes_taches_List.get(i).Gouvernanrt);
        versionViewHolder.Ville.setText(mes_taches_List.get(i).Ville);
        versionViewHolder.Etat.setText(mes_taches_List.get(i).Etat);

    }
}
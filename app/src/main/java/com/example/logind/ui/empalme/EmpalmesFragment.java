package com.example.logind.ui.empalme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.logind.Acitvity_MenuPrincipal;
import com.example.logind.DetallesEmpalmes;
import com.example.logind.R;
import com.example.logind.db.entidades.EmpalmeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmpalmesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    public static final String EXTRA_ID_E = "id_empalme";
    public static final String EXTRA_NOMBRE_EMPALME = "nombre_empalme";
    public static final String EXTRA_TRAMO="tramo";
    public static final String EXTRA_DISTANCIA ="distancia";
    public static final String EXTRA_NUM_POSTE="numero_poste";
    public static final String EXTRA_OBSERVACION = "observaciones";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<EmpalmeEntity> empalmeEntityList;
    private MyEmpalmesRecyclerViewAdapter adapterEmpalmes;
    private EmpalmeFDialogViewModel mViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EmpalmesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EmpalmesFragment newInstance(int columnCount) {
        EmpalmesFragment fragment = new EmpalmesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //indicamos que el fragmento tiene su propio menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empalmes_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if(view.getId()== R.id.listportreit){
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else if(view.getId()==R.id.landscape){
                //class para sacar el tamaño de la pantalla
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels/displayMetrics.density;
                int numerocolumnas =(int)(dpWidth/250);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager( numerocolumnas,StaggeredGridLayoutManager.VERTICAL));
            }
            empalmeEntityList = new ArrayList<>();

            adapterEmpalmes = new MyEmpalmesRecyclerViewAdapter(empalmeEntityList, getActivity());
            recyclerView.setAdapter(adapterEmpalmes);
            lanzarViewModel();

        }
        return view;
    }

    private void lanzarViewModel() {
        mViewModel = ViewModelProviders.of(getActivity()).get(EmpalmeFDialogViewModel.class);
        mViewModel.getAllEmpalmes().observe(getActivity(), new Observer<List<EmpalmeEntity>>() {
            @Override
            public void onChanged(List<EmpalmeEntity> empalmeEntities) {
                adapterEmpalmes.setNuevoEmpalme(empalmeEntities);

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.options_menu_empalme_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add_empalme){
            mostrarDialogoNuevoEmpalme();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarDialogoNuevoEmpalme() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        EmpalmeFragmentDialog dealogNuevoEmpalme = new EmpalmeFragmentDialog();
        dealogNuevoEmpalme.show(fm,"NuevoEmpalmeFragment");
    }
}

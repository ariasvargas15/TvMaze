package com.bsav157.tvmaze.view.fragments;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Person;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.ListCastsPresenter;
import com.bsav157.tvmaze.presenter.interfaces.IListCasts;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.bsav157.tvmaze.view.adapters.ShowAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class InfoPeopleFragment extends Fragment implements IListCasts.View {

    private Person person;
    private static final String ARG_PARAM1 = "person";
    @BindView(R.id.name_person) TextView name;
    @BindView(R.id.image_person) ImageView image;
    @BindView(R.id.shows_person_acts) RecyclerView recycler;
    private AlertDialog dialog;

    public InfoPeopleFragment() {
        // Required empty public constructor
    }

    public static InfoPeopleFragment newInstance(Person person) {
        InfoPeopleFragment fragment = new InfoPeopleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, person);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            person = (Person)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_people, container, false);
        ButterKnife.bind(this, view);


        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();

        dialog.show();
        ListCastsPresenter presenter = new ListCastsPresenter(this);
        presenter.makeQuery(person.getId());

        setViews();
        return view;    }

    private void setViews(){
        name.setText(Html.fromHtml("<b>" + person.getName() +"</b>", Html.FROM_HTML_MODE_COMPACT));

        if (person.getImage() != null) {
            if(person.getImage().getMedium() != null) {
                Picasso.get().load(person.getImage().getMedium()).into(image);
            } else if(person.getImage().getOriginal() != null){
                Picasso.get().load(person.getImage().getOriginal()).into(image);
            }
        }
    }

    @Override
    public void showCasts(final ArrayList<Show> show) {
        GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(grid);
        ShowAdapter adapter = new ShowAdapter(show, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.content, InfoShowFragment.newInstance(show.get(position))).addToBackStack(null).commit();
            }
        });
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }
}
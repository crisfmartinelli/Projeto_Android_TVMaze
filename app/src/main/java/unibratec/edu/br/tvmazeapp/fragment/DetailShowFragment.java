package unibratec.edu.br.tvmazeapp.fragment;


import android.os.Build;
import  android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import unibratec.edu.br.tvmazeapp.R;
import unibratec.edu.br.tvmazeapp.database.DatabaseEvent;
import unibratec.edu.br.tvmazeapp.database.ShowDao;
import unibratec.edu.br.tvmazeapp.http.ShowByIdTask;
import unibratec.edu.br.tvmazeapp.model.Show;

import static android.R.attr.data;
import static android.R.attr.id;

public class DetailShowFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Show>{

    TextView txtId        ;
    TextView txtName      ;
    TextView txtLanguage  ;
    TextView txtPremiered ;
    TextView txtStatus    ;
    TextView txtSummary   ;
    ImageView imageView   ;

    CollapsingToolbarLayout collapsing;
    FloatingActionButton fab;
    Show show;
    ShowDao showDao;

    public static DetailShowFragment newInstance(String id){
        Bundle bundle = new Bundle();
        bundle.putString("idShow", id);
        DetailShowFragment dsf = new DetailShowFragment();
        dsf.setArguments(bundle);
        return dsf;
    }

    public DetailShowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_show, container, false);

        if(getResources().getBoolean(R.bool.phone)) {
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            CollapsingToolbarLayout collapsing = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrRemoveFavorite();
            }
        });


        txtId       = (TextView) view.findViewById(R.id.show_id);
        txtName     = (TextView) view.findViewById(R.id.show_name);
        txtLanguage = (TextView) view.findViewById(R.id.show_language);
        txtPremiered= (TextView) view.findViewById(R.id.show_premiered);
        txtStatus   = (TextView) view.findViewById(R.id.show_status);
        txtSummary  = (TextView) view.findViewById(R.id.show_summary);
        imageView  = (ImageView) view.findViewById(R.id.show_image);

        showDao = ShowDao.getInstance(getActivity().getApplication().getApplicationContext());

        String idShow = getArguments().getString("idShow");
        show = showDao.getShow(idShow);
        if(show == null) {
            getLoaderManager().initLoader(1, getArguments(), this);
        }else{
            updateUI(show, true);
        }

        return view;
    }

    @Override
    public Loader<Show> onCreateLoader(int id, Bundle args) {
        String idShow = args.getString("idShow");
        return new ShowByIdTask(getActivity(), idShow);
    }

    @Override
    public void onLoadFinished(Loader<Show> loader, Show data) {
        if(data != null){
            show = data;
            updateUI(data, false);
        }else{
            Toast.makeText(getActivity(), "Problema em ler o layout", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(Show data, boolean isFavorite){
        show = data;

        // TODO (Baixar titulo)
       /* if(getResources().getBoolean(R.bool.phone)) {
            collapsing.setTitle(data.name);
        }*/


        if (data.images != null && data.images.medium != null) {
            Glide
                    .with(getActivity())
                    .load(data.images.medium)
                    .into(imageView);
        }else{
            imageView.setImageResource(R.drawable.nophoto);
        }

        if (Build.VERSION.SDK_INT >= 24)
        {
            txtId.setText(Html.fromHtml("<b>Id: </b>" + String.valueOf(data.id),Html.FROM_HTML_MODE_LEGACY));
            txtName.setText(Html.fromHtml("<b>Name: </b>" + data.name,Html.FROM_HTML_MODE_LEGACY));
            txtLanguage.setText(Html.fromHtml("<b>Language: </b>" + data.language,Html.FROM_HTML_MODE_LEGACY));
            txtPremiered.setText(Html.fromHtml("<b>Premiered: </b>" + data.premiered,Html.FROM_HTML_MODE_LEGACY));
            txtStatus.setText(Html.fromHtml("<b>Status: </b>" + data.status,Html.FROM_HTML_MODE_LEGACY));
            txtSummary.setText(Html.fromHtml("<b>Summary:</b> \n" + data.summary,Html.FROM_HTML_MODE_LEGACY));
        }
        else
        {
            txtId.setText(Html.fromHtml("<b>Id: </b>" + String.valueOf(data.id)));
            txtName.setText(Html.fromHtml("<b>Name: </b>" + data.name));
            txtLanguage.setText(Html.fromHtml("<b>Language: </b>" + data.language));
            txtPremiered.setText(Html.fromHtml("<b>Premiered: </b>" + data.premiered));
            txtStatus.setText(Html.fromHtml("<b>Status: </b>" + data.status));
            txtSummary.setText(Html.fromHtml("<b>Summary:</b> \n" + data.summary));
        }
         changeFloatingButton(isFavorite);
    }

    @Override
    public void onLoaderReset(Loader<Show> loader) {

    }

    public void saveOrRemoveFavorite(){
        Show tempShow = showDao.getShow(String.valueOf(show.id));
        if(tempShow != null){
            showDao.deleteShow(show);
            changeFloatingButton(false);
        }else{
            showDao.insertShow(show);
            changeFloatingButton(true);
        }
        EventBus.getDefault().post(new DatabaseEvent());
    }

    public void changeFloatingButton(boolean isFavorite){
        int resource = isFavorite ? R.drawable.ic_favorite_white_24dp : R.drawable.ic_favorite_border_black_24dp;
        fab.setImageResource(resource);
    }

}

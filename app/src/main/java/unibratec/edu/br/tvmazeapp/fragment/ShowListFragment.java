package unibratec.edu.br.tvmazeapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import unibratec.edu.br.tvmazeapp.OnResultClick;

import java.util.List;

import unibratec.edu.br.tvmazeapp.R;
import unibratec.edu.br.tvmazeapp.http.ShowsSearchTask;
import unibratec.edu.br.tvmazeapp.model.Result;
import unibratec.edu.br.tvmazeapp.ui.ShowAdapter;

public class ShowListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Result>>,
        SearchView.OnQueryTextListener, AdapterView.OnItemClickListener{

    ListView mListShows;
    LoaderManager mLoaderManager;

    public ShowListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_list, container, false);

        mListShows = (ListView)view.findViewById(R.id.list_item);
        mListShows.setOnItemClickListener(this);

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(getActivity() instanceof OnResultClick){
            Result result = (Result) mListShows.getItemAtPosition(position);
            ((OnResultClick)getActivity()).onResultClick(result);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public Loader<List<Result>> onCreateLoader(int id, Bundle args) {
        String q = args != null ? args.getString("q") : null;
        return new ShowsSearchTask(getActivity(), q);
    }

    @Override
    public void onLoadFinished(Loader<List<Result>> loader, List<Result> data) {
        if(data != null){
            mListShows.setAdapter(
                    new ShowAdapter(getActivity(), data));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Result>> loader) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("q", query);
        mLoaderManager.restartLoader(0, bundle, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

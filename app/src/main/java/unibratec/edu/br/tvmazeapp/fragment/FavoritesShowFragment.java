package unibratec.edu.br.tvmazeapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import unibratec.edu.br.tvmazeapp.OnResultClick;
import unibratec.edu.br.tvmazeapp.R;
import unibratec.edu.br.tvmazeapp.database.DatabaseEvent;
import unibratec.edu.br.tvmazeapp.database.ShowDao;
import unibratec.edu.br.tvmazeapp.model.Show;
import unibratec.edu.br.tvmazeapp.ui.ShowAdapter;
import unibratec.edu.br.tvmazeapp.ui.ShowFavoriteAdapter;

public class FavoritesShowFragment extends Fragment {

    ListView mListViewShows;
    List<Show> mListShows;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public FavoritesShowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_list, container, false);

        mListViewShows = (ListView) view.findViewById(R.id.list_item);
        mListViewShows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(getActivity() instanceof OnResultClick){
                    Show show = (Show) mListViewShows.getItemAtPosition(position);
                    ((OnResultClick) getActivity()).onShowClick(show);
                }
            }
        });

        updatelist();

        return view;
    }

    private void updatelist(){
        mListShows = ShowDao.getInstance(getActivity()
                .getApplication()
                .getApplicationContext()).getFavoriteShows();

        mListViewShows.setAdapter(new ShowFavoriteAdapter(getActivity(), mListShows));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DatabaseEvent event){
        updatelist();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

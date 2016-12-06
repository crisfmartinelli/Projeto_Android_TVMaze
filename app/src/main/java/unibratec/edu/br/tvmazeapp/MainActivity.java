package unibratec.edu.br.tvmazeapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import unibratec.edu.br.tvmazeapp.fragment.DetailShowFragment;
import unibratec.edu.br.tvmazeapp.fragment.FavoritesShowFragment;
import unibratec.edu.br.tvmazeapp.fragment.ShowListFragment;
import unibratec.edu.br.tvmazeapp.model.Result;


public class MainActivity extends AppCompatActivity
                          implements OnResultClick {

    Toolbar mToolbar;
    ShowListFragment mShowList;
    FavoritesShowFragment mFavorites;
    ViewPager mViewPager;
    SelectorPageAdapter mSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        buildViewPager();
    }

    public void buildViewPager(){

        mViewPager = (ViewPager)findViewById(R.id.container);
        mSelector = new SelectorPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSelector);

        TabLayout tab = (TabLayout)findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);

    }


    @Override
    public void onShowClick(Result result) {
        if(getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(MainActivity.this, DetailShowActivity.class);
            it.putExtra("idShow", String.valueOf(result.show.id));
            startActivity(it);
        }else{
            DetailShowFragment dsf = DetailShowFragment.newInstance(String.valueOf(result.show.id));

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_show_detail, dsf, "detail")
                    .commit();
        }
    }

    public class SelectorPageAdapter extends FragmentPagerAdapter {

        public SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if(mShowList == null){
                        mShowList = new ShowListFragment();
                    }
                    return mShowList;

                case 1:
                default:
                    if(mFavorites == null){
                        mFavorites = new FavoritesShowFragment();
                    }
                    return mFavorites;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Lista";
                case 1:
                default:
                    return "Favoritos";
            }
        }
    }

}

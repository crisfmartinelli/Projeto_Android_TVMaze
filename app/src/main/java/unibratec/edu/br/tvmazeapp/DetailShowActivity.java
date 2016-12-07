package unibratec.edu.br.tvmazeapp;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import unibratec.edu.br.tvmazeapp.fragment.DetailShowFragment;
import unibratec.edu.br.tvmazeapp.http.ShowByIdTask;
import unibratec.edu.br.tvmazeapp.model.Image;
import unibratec.edu.br.tvmazeapp.model.Show;

import static java.security.AccessController.getContext;
import static unibratec.edu.br.tvmazeapp.fragment.DetailShowFragment.newInstance;

public class DetailShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        String idShow = getIntent().getStringExtra("idShow");
        DetailShowFragment dsf = DetailShowFragment.newInstance(idShow);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_show_detail, dsf, "detail")
                .commit();


    }


}

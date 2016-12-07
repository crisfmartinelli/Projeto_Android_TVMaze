package unibratec.edu.br.tvmazeapp.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

import unibratec.edu.br.tvmazeapp.model.Result;
import unibratec.edu.br.tvmazeapp.model.Show;

public class ShowByIdTask extends AsyncTaskLoader<Show> {

    Show mShow;
    String mIdShow;

    public ShowByIdTask(Context context, String id) {
        super(context);
        mIdShow = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mIdShow == null) return;

        if(mShow == null){
            forceLoad();
        }else{
            deliverResult(mShow);
        }
    }

    @Override
    public Show loadInBackground() {
        try {
            mShow = TvParser.searchById(mIdShow);
            return mShow;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mShow;
    }
}

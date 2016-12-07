package unibratec.edu.br.tvmazeapp.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

import unibratec.edu.br.tvmazeapp.model.Result;

public class ShowsSearchTask extends AsyncTaskLoader<List<Result>> {

    List<Result> mResults;
    String mQuery;

    public ShowsSearchTask(Context context, String q) {
        super(context);
        mQuery = q;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mQuery == null) return;

        if(mResults == null){
            forceLoad();
        }else{
            deliverResult(mResults);
        }
    }

    @Override
    public List<Result> loadInBackground() {
        try {
            mResults = TvParser.searchByName(mQuery);
            return mResults;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResults;
    }
}

package unibratec.edu.br.tvmazeapp;


import unibratec.edu.br.tvmazeapp.model.Result;
import unibratec.edu.br.tvmazeapp.model.Show;

public interface OnResultClick {

    void onResultClick(Result result);
    void onShowClick(Show show);
}

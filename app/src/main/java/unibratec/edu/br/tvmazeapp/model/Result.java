package unibratec.edu.br.tvmazeapp.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("score")
    public double score;

    @SerializedName("show")
    public Show show;
}

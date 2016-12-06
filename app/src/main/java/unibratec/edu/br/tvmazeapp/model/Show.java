package unibratec.edu.br.tvmazeapp.model;

import com.google.gson.annotations.SerializedName;

public class Show {

    public int id;
    public String name;
    public String language;
    public String premiered;
    public String status;
    public String summary;
    @SerializedName("image")
    public Image images;
}

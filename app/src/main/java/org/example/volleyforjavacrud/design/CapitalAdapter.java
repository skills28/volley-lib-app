package org.example.volleyforjavacrud.design;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import org.example.volleyforjavacrud.R;
import org.example.volleyforjavacrud.entity.Country;

import java.util.List;

public class CapitalAdapter extends RecyclerView.Adapter<CapitalAdapter.CapitalHolder> {

    private final List<Country> countryList;
    private final Context context;
    private final LayoutInflater inflater;
    private ColorGenerator mColorGenerator;

    public CapitalAdapter(Context context , List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CapitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.country_list_layout, parent, false);
        CapitalHolder itemHolder = new CapitalHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CapitalHolder holder, int position) {
        Country country = countryList.get(position);
        holder.countryTextView.setText(country.getCountryName());
        holder.capitalTextView.setText(country.getCapitalName());
        mColorGenerator = ColorGenerator.MATERIAL;
        String countryName = String.valueOf(country.getCountryName().toUpperCase().charAt(0));
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.BLACK)
                .useFont(Typeface.SANS_SERIF)
                .withBorder(4)
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRect(countryName, mColorGenerator.getRandomColor());
        holder.countryFlag.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class CapitalHolder extends RecyclerView.ViewHolder{
        private final TextView countryTextView;
        private final TextView capitalTextView;
        private final ImageView countryFlag;

        public CapitalHolder(@NonNull View itemView) {
            super(itemView);
            countryTextView = itemView.findViewById(R.id.country_name_TextView);
            capitalTextView = itemView.findViewById(R.id.capital_name_TextView);
            countryFlag = itemView.findViewById(R.id.countryFlags);
        }
    }
}

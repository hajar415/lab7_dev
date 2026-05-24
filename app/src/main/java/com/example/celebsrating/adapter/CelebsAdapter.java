package com.example.celebsrating.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.celebsrating.R;
import com.example.celebsrating.beans.Celeb;
import com.example.celebsrating.service.CelebsService;
import java.util.ArrayList;
import java.util.List;

public class CelebsAdapter extends RecyclerView.Adapter<CelebsAdapter.CelebHolder>
        implements Filterable {

    private List<Celeb> celebList;
    private List<Celeb> filteredList;
    private Context context;
    private CelebsFilter celebsFilter;

    public CelebsAdapter(Context context, List<Celeb> celebList) {
        this.context      = context;
        this.celebList    = celebList;
        this.filteredList = new ArrayList<>(celebList);
        this.celebsFilter = new CelebsFilter(this);
    }

    @Override
    public CelebHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.celeb_item, parent, false);
        final CelebHolder holder = new CelebHolder(v);

        holder.itemView.setOnClickListener(view -> {
            View popup = LayoutInflater.from(context)
                    .inflate(R.layout.celeb_edit_popup, null, false);

            final ImageView popupImg      = popup.findViewById(R.id.popupImg);
            final RatingBar popupRatingBar = popup.findViewById(R.id.popupRatingBar);
            final TextView  popupId       = popup.findViewById(R.id.popupId);
            final TextView  popupName     = popup.findViewById(R.id.popupName);

            try {
                Bitmap bitmap = ((BitmapDrawable)
                        ((ImageView) view.findViewById(R.id.celebImg))
                                .getDrawable()).getBitmap();
                popupImg.setImageBitmap(bitmap);
            } catch (Exception e) {
                popupImg.setImageResource(android.R.mipmap.sym_def_app_icon);
            }

            popupRatingBar.setRating(
                    ((RatingBar) view.findViewById(R.id.celebRating)).getRating());
            popupId.setText(
                    ((TextView) view.findViewById(R.id.celebId)).getText().toString());
            popupName.setText(
                    ((TextView) view.findViewById(R.id.celebName)).getText().toString());

            new AlertDialog.Builder(context)
                    .setTitle(R.string.popup_title)
                    .setMessage(R.string.popup_msg)
                    .setView(popup)
                    .setPositiveButton(R.string.btn_valider, (dialog, which) -> {
                        float newRating = popupRatingBar.getRating();
                        int id = Integer.parseInt(popupId.getText().toString());
                        Celeb celeb = CelebsService.getInstance().findById(id);
                        if (celeb != null) {
                            celeb.setRating(newRating);
                            CelebsService.getInstance().update(celeb);
                            notifyItemChanged(holder.getAdapterPosition());
                        }
                    })
                    .setNegativeButton(R.string.btn_annuler, null)
                    .show();
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(CelebHolder holder, int position) {
        Celeb c = filteredList.get(position);
        holder.celebId.setText(String.valueOf(c.getId()));
        holder.celebName.setText(c.getFullName().toUpperCase());
        holder.celebRating.setRating(c.getRating());
        Glide.with(context)
                .asBitmap()
                .load(c.getPhotoUrl())
                .apply(new RequestOptions().override(100, 100))
                .placeholder(android.R.mipmap.sym_def_app_icon)
                .into(holder.celebImg);
    }

    @Override
    public int getItemCount() { return filteredList.size(); }

    @Override
    public Filter getFilter() { return celebsFilter; }

    public static class CelebHolder extends RecyclerView.ViewHolder {
        TextView  celebId, celebName;
        ImageView celebImg;
        RatingBar celebRating;

        CelebHolder(View itemView) {
            super(itemView);
            celebId     = itemView.findViewById(R.id.celebId);
            celebImg    = itemView.findViewById(R.id.celebImg);
            celebName   = itemView.findViewById(R.id.celebName);
            celebRating = itemView.findViewById(R.id.celebRating);
        }
    }

    public class CelebsFilter extends Filter {
        public RecyclerView.Adapter mAdapter;

        public CelebsFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence sequence) {
            List<Celeb> result = new ArrayList<>();
            if (sequence == null || sequence.length() == 0) {
                result.addAll(celebList);
            } else {
                String pattern = sequence.toString().toLowerCase().trim();
                for (Celeb c : celebList) {
                    if (c.getFullName().toLowerCase().startsWith(pattern))
                        result.add(c);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = result;
            filterResults.count  = result.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence sequence,
                                      FilterResults filterResults) {
            filteredList = (List<Celeb>) filterResults.values;
            mAdapter.notifyDataSetChanged();
        }
    }
}
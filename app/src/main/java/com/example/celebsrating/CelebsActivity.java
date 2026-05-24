package com.example.celebsrating.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.celebsrating.R;
import com.example.celebsrating.adapter.CelebsAdapter;
import com.example.celebsrating.service.CelebsService;

public class CelebsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CelebsAdapter celebsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebs);

        getSupportActionBar().setElevation(8f);

        recyclerView = findViewById(R.id.celebsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        celebsAdapter = new CelebsAdapter(this,
                CelebsService.getInstance().findAll());
        recyclerView.setAdapter(celebsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);

        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (celebsAdapter != null)
                            celebsAdapter.getFilter().filter(newText);
                        return true;
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType("text/plain")
                    .setChooserTitle(getString(R.string.share_title))
                    .setText(getString(R.string.share_text))
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}
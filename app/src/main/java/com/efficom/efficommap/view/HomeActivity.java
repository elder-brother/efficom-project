package com.efficom.efficommap.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.efficom.efficommap.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jourda_c on 2/20/17.
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.listview) ListView listView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToogle;

    private DataAdapter dataAdapter;
    private ArrayList<Item> listItems;

    @Override public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.home);
        initDrawerLayout();

        listItems = new ArrayList<>();
        for (int i=0;i<20;i++)
            listItems.add(new Item("Title"+i, "Subtitle"+i));

        dataAdapter = new DataAdapter(this, listItems);
        listView.setAdapter(dataAdapter);
    }

    @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToogle.syncState();
    }

    @Override protected void onPause() {
        super.onPause();
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToogle.onConfigurationChanged(newConfig);
    }

    private void initDrawerLayout(){
        drawerToogle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                0, 0);
        drawerLayout.addDrawerListener(drawerToogle);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_helloworld:
                Toast.makeText(this, "Menu helloworld clicked !!",
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class Item {
        public Item(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
        }

        String title;
        String subtitle;
        boolean isChecked;
    }

    public class DataAdapter extends ArrayAdapter<Item> {

        public DataAdapter(Context context, List<Item> objects) {
            super(context, R.layout.list_item, objects);
        }

        @NonNull @Override public View getView(int position,
                                               View convertView,
                                               ViewGroup parent) {
            View v = convertView;
            ViewHolder vh;
            Item item = getItem(position);
            if (v == null) {
                // Create view
                v = LayoutInflater.from(HomeActivity.this)
                    .inflate(R.layout.list_item, parent, false);
                // Create viewHolder
                vh = new ViewHolder(v);
                // Associate ViewHolder with view
                v.setTag(vh);
            }
            else {
                // View is not null / Recycle view
                vh = (ViewHolder) v.getTag();
            }
            // ViewHolder is not null
            vh.checkbox.setTag(item);
            vh.title.setText(item.title);
            vh.subtitle.setText(item.subtitle);
            vh.checkbox.setChecked(item.isChecked);
            return v;
        }

        class ViewHolder {
            @BindView(R.id.title) TextView title;
            @BindView(R.id.subtitle) TextView subtitle;
            @BindView(R.id.checkbox) CheckBox checkbox;
            public ViewHolder(View v){
                ButterKnife.bind(this, v);
            }

            @OnClick(R.id.checkbox)
            public void onCheckboxClick(CheckBox checkbox){
                // Set item state
                Item item = (Item) checkbox.getTag();
                item.isChecked = checkbox.isChecked();
            }
        }
    }
}

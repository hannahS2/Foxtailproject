package com.example.hannah.foxtailproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.hannah.foxtailproject.R.styleable.MenuItem;

public class ListActivity extends AppCompatActivity {

    private ItemModel model;
    private List<Item> items = new ArrayList<>();

    private RecyclerView list;
    private FloatingActionButton add;
    private ImageButton back;
    private ImageButton search;
    private ImageButton start;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (RecyclerView) findViewById(R.id.recyclerview);
        add = (FloatingActionButton) findViewById(R.id.add);
        layout = (RelativeLayout) findViewById(R.id.layout);

        model = new ItemModel();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        list.setLayoutManager(layoutManager);

        list.setAdapter(new RecyclerView.Adapter<ItemHolder>() {
            @Override
            public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.list_item, parent, false);
                return new ItemHolder(view);
            }

            @Override
            public void onBindViewHolder(ItemHolder holder, final int position) {
                holder.setItem(items.get(position));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListActivity.this, InfoActivity.class);
                        intent.putExtra("item", items.get(position));
                        intent.putExtra("id", items.get(position).id);
                        startActivity(intent);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        AlertDialog.Builder ad = new AlertDialog.Builder(layout.getContext());
                        ad.setTitle(items.get(position).name + " 를(을) 삭제하시겠습니까?");
                        ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                model.deleteItem(items.get(position).id);
                            }
                        });
                        ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        ad.show();
                        return false;
                    }
                });

            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        });

        model.setOnItemChangedListener(new OnItemChangedListener() {
            @Override
            public void onDataChanged(List<Item> item) {
                items = item;
                list.getAdapter().notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.list_custom_actionbar, null);

        actionBar.setCustomView(actionbar);

        back = (ImageButton) actionbar.findViewById(R.id.back);
        search = (ImageButton) actionbar.findViewById(R.id.search);
        start = (ImageButton) actionbar.findViewById(R.id.start);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView location;

        public ItemHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);
            location = (TextView) itemView.findViewById(R.id.place);
        }

        public void setItem(Item item) {
            name.setText(item.name);
            location.setText(item.location);
        }
    }


}



package com.pryangan.pranstore;

import android.content.SharedPreferences;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_plus,fab_text,fab_photo;
    Animation fabOpen,fabClose,fabRClockwise,fabRanticlockwise;
    boolean isOpen = false;


    //For listView
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    String json_string;
    String userName;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab_text = (FloatingActionButton) findViewById(R.id.fab_text);
        fab_photo = (FloatingActionButton) findViewById(R.id.fab_photo);
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_closewise);
        fabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOpen)
                {
                    fab_photo.startAnimation(fabClose);
                    fab_text.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRanticlockwise);
                    fab_text.setClickable(false);
                    fab_photo.setClickable(false);
                    isOpen = false;
                }
                else
                {
                    fab_photo.startAnimation(fabOpen);
                    fab_text.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabRClockwise);
                    fab_text.setClickable(true);
                    fab_photo.setClickable(true);
                    isOpen = true;
                }
            }
        });

        fab_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,PhotoActivity.class));
            }
        });

        fab_text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,TextActivity.class));
            }
        });

        stubList = (ViewStub)findViewById(R.id.stub_list);
        stubGrid = (ViewStub)findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);



        //get list of product
        getProductList();

        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();
    }

    private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList() {
        //pseudo code to get product, replace your code to get real product here
        json_string = getIntent().getExtras().getString("json_data");
        userName = getIntent().getExtras().getString("user_name");
        productList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");
            int count = 0;
            String title,description,type,cost,mfg,exp,com;
            while(count<jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                title = jo.getString("title");
                description = jo.getString("description");
                type = jo.getString("type");
                cost = jo.getString("cost");
                mfg = jo.getString("mfg");
                exp = jo.getString("exp");
                com = jo.getString("com");

                productList.add(new Product(R.drawable.logo,title,description,type,cost,mfg,exp,com));
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productList;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
//            Intent intent = new Intent(MainActivity.this,Medicine_Detail.class);
//            intent.putExtra("user_name",userName);
//            intent.putExtra("Title",productList.get(position).getTitle());
//            intent.putExtra("Description",productList.get(position).getDescription());
//            intent.putExtra("cost",productList.get(position).getCost());
//            intent.putExtra("type",productList.get(position).getType());
//            intent.putExtra("mfg",productList.get(position).getMfg());
//            intent.putExtra("exp",productList.get(position).getExp());
//            intent.putExtra("com",productList.get(position).getCom());
//            startActivity(intent);

            Product model = productList.get(position);
            if (model.isSelected()) {
                model.setSelected(false);
                Toast.makeText(getApplicationContext(), productList.get(position).getTitle() + " is Unselected ", Toast.LENGTH_SHORT).show();
            }
            else {
                model.setSelected(true);
                Toast.makeText(getApplicationContext(), productList.get(position).getTitle() + " is Selected ", Toast.LENGTH_SHORT).show();
            }
            //now update adapter
           listViewAdapter.updateRecords();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.Switch_view:
                if(VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();
                return true;
            case R.id.login:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.sign_up:
                Intent intent2 = new Intent(this,SignUpActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_share:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT,"nfgjnf");
                String chooserTitle = getString(R.string.chooser);
                Intent chooserIntent = Intent.createChooser(intent1,chooserTitle);
                startActivity(chooserIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.example.ricoramars.numberstrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TriviaAdapter mAdapter;

    List<NumberQuoteItem> numberQuoteItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberQuoteItemList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.triviaRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TriviaAdapter(this, numberQuoteItemList);
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData(){
        NumberApiService service = NumberApiService.retrofit.create(NumberApiService.class);
        Call<NumberQuoteItem> call = service.getRandomNumber();
        call.enqueue(new Callback<NumberQuoteItem>() {
            @Override
            public void onResponse(Call<NumberQuoteItem> call, Response<NumberQuoteItem> response) {
                NumberQuoteItem item = response.body();
                if(item != null) {
                    Log.d("onResponse", item.getText());
                    addItem(item);
                }
            }
            @Override
            public void onFailure(Call<NumberQuoteItem> call, Throwable t) {
                Log.e("requestData Failed",t.toString());
            }
        });
    }

    public void addItem(NumberQuoteItem item){
        numberQuoteItemList.add(item);
        mAdapter.notifyItemInserted(numberQuoteItemList.size()-1);
        Log.d("adapterSize", mAdapter.getItemCount() + "");
    }

}

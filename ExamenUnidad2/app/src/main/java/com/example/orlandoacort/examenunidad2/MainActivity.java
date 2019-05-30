package com.example.orlandoacort.examenunidad2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   private ProgressDialog mProgressDialog;
    private String url = "https://www.upt.edu.pe/upt/web/home/noticias/100000000/";
    private ArrayList<String> mTitulo = new ArrayList<>();
    private ArrayList<String> mFecha = new ArrayList<>();
    private ArrayList<String> mDescripcion = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Upt");
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document mBlogDocument = Jsoup.connect(url).get();
                Elements mElementDataSize = mBlogDocument.select("div[class=col-md-2]");
                int mElementSize = mElementDataSize.size();

                for (int i = 0; i < mElementSize; i++) {
                    Elements mElementNoticiaName = mBlogDocument.select("div[class=by-author]").select("a").eq(i);
                    String mNoticiaName = mElementNoticiaName.text();

                    Elements mElementFecha = mBlogDocument.select("div[class=by-author]").eq(i);
                    String mBlogUploadDate = mElementFecha.text();

                    Elements mElementDescripcion = mBlogDocument.select("div[class=by-author]").select("a").eq(i);
                    String mBlogDescripcion = mElementDescripcion.text();

                    mTitulo.add(mNoticiaName);
                    mFecha.add(mBlogUploadDate);
                    mDescripcion.add(mBlogDescripcion);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

            Adaptador mDataAdapter = new Adaptador(MainActivity.this, mTitulo, mFecha, mDescripcion);
            RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mDataAdapter);

            mProgressDialog.dismiss();
        }
    }


}

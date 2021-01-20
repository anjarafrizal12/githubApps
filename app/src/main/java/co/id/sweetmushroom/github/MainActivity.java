package co.id.sweetmushroom.github;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Objects;

import co.id.sweetmushroom.github.adapter.ListUserAdapter;
import co.id.sweetmushroom.github.model.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_user_main;
    private ArrayList<User> listUser = new ArrayList<>();
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
        }

        rv_user_main    = findViewById(R.id.rv_main);
        rv_user_main.setHasFixedSize(true);

        searchView  = findViewById(R.id.search_view);

        listUser.addAll(getAllListUser());
        showRecyclerViewUser();

    }

    // fungsi untuk merubah data berupa array-string sebagai resource aplikasi pada string.xml
    // untuk selanjutnya diubah jadi berupa object arraylist class User
    private ArrayList<User> getAllListUser() {
        String[] arrayUserame           = getResources().getStringArray(R.array.username);
        String[] arrayName              = getResources().getStringArray(R.array.name);
        String[] arrayRepository        = getResources().getStringArray(R.array.repository);
        String[] arrayCompany           = getResources().getStringArray(R.array.company);
        String[] arrayLocation          = getResources().getStringArray(R.array.location);
        String[] arrayFollowers         = getResources().getStringArray(R.array.followers);
        String[] arrayFollowing         = getResources().getStringArray(R.array.following);
        String[] arrayAvatar            = getResources().getStringArray(R.array.avatar);

        ArrayList<User> listUser = new ArrayList<>();
        for (int i = 0; i < arrayUserame.length; i++) {
            User user = new User();
            user.setUsername(arrayUserame[i]);
            user.setName(arrayName[i]);
            user.setRepository(arrayRepository[i]);
            user.setLocation(arrayLocation[i]);
            user.setCompany(arrayCompany[i]);
            user.setFollowers(arrayFollowers[i]);
            user.setFollowing(arrayFollowing[i]);
            user.setAvatar(arrayAvatar[i]);
            listUser.add(user);
        }
        return listUser;
    }

    // prosedur/fungsi untuk menampilkan recyclerview, dengan data berupa arraylist yang telah
    // di buat terlebih dahulu pada fungsi getAllListUser()
    private void showRecyclerViewUser() {
        rv_user_main.setLayoutManager(new LinearLayoutManager(this));
        ListUserAdapter listHeroAdapter = new ListUserAdapter(listUser);
        rv_user_main.setAdapter(listHeroAdapter);


        // fungsi setOnItemClickCallback di implementasi pada class adapter, untuk mengembalikan
        // object user yang di click pada item recyclerview
        listHeroAdapter.setOnItemClickCallback(new ListUserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(User user) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_USER, user);
                startActivity(intent);
            }
        });
    }

    // deklarasi untuk menampilkan menu, item menu yang ditampilkan hanya searchview
    // menu searchview belum berjalan, masih berupa widget, sebagai pengembangan module berikutnya
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    // handle aksi ketika menekan back button, jika searchview terbuka maka akan menutup
    // widget searchview, sebaliknya akan menutup aplikasi
    @Override
    public void onBackPressed(){
        if (searchView.isSearchOpen()){
            searchView.closeSearch();
        } else {
            finish();
        }
    }


}

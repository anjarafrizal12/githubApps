package co.id.sweetmushroom.github;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

import co.id.sweetmushroom.github.model.User;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // ## start code kebutuhan toolbar dan collapsing toolbar
        // ## collapsing toolbar didapat dari sumber web dicoding

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_toolbar_detail);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimaryDark));
        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.colorTextWhite));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.transparent));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // ## end code kebutuhan toolbar dan collapsing toolbar

        ImageView ivAvatar = findViewById(R.id.iv_detail_avatar);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvRepository = findViewById(R.id.tv_repository_value);
        TextView tvFollowers = findViewById(R.id.tv_followers_value);
        TextView tvFollowing = findViewById(R.id.tv_following_value);
        TextView tvLocation = findViewById(R.id.tv_location_value);
        TextView tvCompany = findViewById(R.id.tv_company_value);


        User user = getIntent().getParcelableExtra(EXTRA_USER);


        // Proses parsing string to int resource image drawable
        // hal ini dikarenakan terjadi kegagalan ketika load gambar
        // menggunakan glide jika berupa string dengan error path not found.
        String[] nameAvatar = user != null ? user.getAvatar().split("/") : new String[0];
        String nameValue = nameAvatar[nameAvatar.length-1].replace(".png","").trim();
        int drawableId = getResources().getIdentifier(nameValue , "drawable", getPackageName());

        collapsingToolbarLayout.setTitle(user != null ? user.getName() : "data not loaded");


        Glide.with(this)
                .load(drawableId)
                .apply(new RequestOptions().override(100, 100))
                .into(ivAvatar);

        tvName.setText(user != null ? user.getName() : "data not loaded");
        tvUsername.setText(user != null ? user.getUsername() : "data not loaded");
        tvRepository.setText(user != null ? user.getRepository() : "data not loaded");
        tvFollowers.setText(user != null ? user.getFollowers() : "data not loaded");
        tvFollowing.setText(user != null ? user.getFollowing() : "data not loaded");
        tvLocation.setText(user != null ? user.getLocation() : "data not loaded");
        tvCompany.setText(user != null ? user.getCompany() : "data not loaded");
    }
}

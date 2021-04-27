package ybk.org.movieapp.data.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.App;

public class RetrofitClient {

    private static final String BASE_URL = App.getInstance().getString(R.string.base_url);

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}

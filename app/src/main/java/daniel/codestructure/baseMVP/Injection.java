package daniel.codestructure.baseMVP;

import android.content.Context;
import android.support.annotation.NonNull;

import daniel.codestructure.data.network.MyVolley;
import daniel.codestructure.data.remote.FeedsRemoteDataSource;
import daniel.codestructure.data.repository.FeedsRepository;

public class Injection {
    public static Context provideContext(@NonNull Context context) {
        return context;
    }

    public static FeedsRepository provideFeedsRepository(Context context) {
        MyVolley volley = new MyVolley(context);
        FeedsRemoteDataSource dataSource = FeedsRemoteDataSource.getInstance(volley);
        FeedsRepository repository = FeedsRepository.getInstance(dataSource);
        return repository;
    }
}

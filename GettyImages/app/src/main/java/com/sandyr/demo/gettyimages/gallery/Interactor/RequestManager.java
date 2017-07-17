package com.sandyr.demo.gettyimages.gallery.Interactor;

import com.sandyr.demo.gettyimages.common.network.RetrofitServiceManager;
import com.sandyr.demo.gettyimages.gallery.Interactor.Services.GettyImageService;
import com.sandyr.demo.gettyimages.gallery.Interactor.responses.GettyImageResponse;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RequestManager {
    public RequestManager() {

    }

    /***
     * Send network requests to an API, we need to use the Retrofit Builder class and specify the base URL for the service
     *
     * @return
     */

    private Retrofit getRetrofitClient() {
        return RetrofitServiceManager.getInstance().getRetrofitClient();
    }

    public Subscription retrieveGettyImages(Observer<GettyImageResponse> myObserver, int page,
                                            int pageSize, String phrase) {
        GettyImageService service = getRetrofitClient().create(GettyImageService.class);
        Subscription subscription = service.getGettyImages(page,pageSize,phrase/*,token*/)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
        return subscription;
    }

}
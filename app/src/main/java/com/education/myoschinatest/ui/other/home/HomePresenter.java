package com.education.myoschinatest.ui.other.home;

/**
 *  Created by Json on 2017/5/16.
 */

public class HomePresenter  {
//    private HomeContract.View view;
//    private HomeContract.Modle modle;
//
//    public HomePresenter(HomeContract.View view) {
//        this.view = view;
//        modle = new HomeModle();
//    }
//
//    @Override
//    public void getVertical(Map<String, String> params) {
//            modle.getVertical(params, new Callback<DataBean>() {
//                @Override
//                public void onResponse(Call<DataBean> call, Response<DataBean> response) {
//                    DataBean dataBean = response.body();
//                    final List<RoomBean> rooms = dataBean.getData();
//                    MyApp.getLiteOrm().insert(rooms);
//                    Handler handler = new Handler(Looper.getMainLooper());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.onGetVerticalSucess(rooms);
//                        }
//                    });
//                }
//
//                @Override
//                public void onFailure(Call<DataBean> call, Throwable t) {
//                    t.printStackTrace();
//                    Handler handler = new Handler(Looper.getMainLooper());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.onGetVerticalFail("网络连接失败");
//                        }
//                    });
//                }
//            });
//    }
}

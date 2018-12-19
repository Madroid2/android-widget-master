package com.udacity.horatio.widgetexample;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * WidgetDataProvider acts as the adapter for the collection view widget,
 * providing RemoteViews to the widget in the getViewAt method.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<StockDataItem> mStockCollection = new ArrayList<>();
    Context mContext = null;
    private ArrayList<String> mNewsArrayList;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        //initData();
        initStockData();
    }

    @Override
    public void onDataSetChanged() {
        //initData();
        initStockData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mStockCollection.size();
        //return Integer.MAX_VALUE;
//        return 1;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        //int positionInList = position % mStockCollection.size();
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                R.layout.row_layout_stock_market_item);
//        view.setTextViewText(R.id.row_layout_stock_type_name,mStockCollection.get(positionInList).getStockType());
        view.setTextViewText(R.id.row_layout_stock_change, mStockCollection.get(position).getStockChange());
//        view.setTextViewText(R.id.row_layout_stock_company_name,mStockCollection.get(positionInList).getStockName());
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initStockData() {
        mStockCollection.clear();
        initStockNewsData();
        for (int i = 1; i <= 20; i++) {
            StockDataItem stockDataItem = new StockDataItem();
            stockDataItem.setStockChange(mNewsArrayList.get(i-1));
//            stockDataItem.setStockPercentage((i+5));
//            stockDataItem.setStockName("Bajaj 120 Adidas 240 SBI 320 PHC 666 NIKE 890 Infosys 980 Facebook 990");
//            stockDataItem.setStockType("NASDAQ");
            mStockCollection.add(stockDataItem);
        }
    }

    private void initStockNewsData() {
        mNewsArrayList = new ArrayList<>();
        mNewsArrayList.add("Bitcoin fecha abaixo dos US$ 5 mil pela 1ª vez desde outubro de 2017");
        mNewsArrayList.add("Tech Slump Submerges Stocks In Red Sea (Wall Street Breakfast Podcast)");
        mNewsArrayList.add("Everything you need to know about bitcoin wallets");
        mNewsArrayList.add("Bitcoinin kurssi laski jo lähelle 4 000 dollarin rajaa");
        mNewsArrayList.add("Cryptocurrency price collapse: Why have bitcoin, ethereum and ripple all suddenly crashed?");
        mNewsArrayList.add("Market Snapshot: Ray Dalio says its just like the 1930s for investors right now");
        mNewsArrayList.add("Die großen Fünf in Amerika bleiben interessant");
        mNewsArrayList.add("Stocks are Now in ‘Complete Bitcoin Territory,’ Asset Manager Says");
        mNewsArrayList.add("A Sea Of Red: Global Stocks Plunge With Tech Shares In Freefall");
        mNewsArrayList.add("Euforie odumřela. Bitcoin se dostal pod 4500 dolarů,description : Cena digitální měny bitcoin pokračovala v poklesu a poprvé za více než rok se dostala pod 4500 dolarů (102 500 korun). Za týden se tak hodnota bitcoinu snížila zhruba o 30 procent.");
        mNewsArrayList.add("Influencers Weigh-In On Big 2019 Predictions For The CryptoBlock Arena");
        mNewsArrayList.add("10 things you need to know before the opening bell");
        mNewsArrayList.add("分裂した仮想通貨Bitcoin Cash騒動、空ブロックを掘ることで相手をつぶすとマイニングプールSharkPoolが宣戦布告");
        mNewsArrayList.add("Bitcoin Price Hits New Yearly Low at $4,280; Market Needs Quick Rebound");
        mNewsArrayList.add("Oil Prices Slide As Russia Says Wait And See on Production Cuts");
        mNewsArrayList.add("Bitcoin fecha abaixo dos US$ 5 mil pela 1ª vez desde outubro de 2017");
        mNewsArrayList.add("Bitcoin pokračuje v poklesu, cena digitální měny se poprvé dostala 4500 dolarů");
        mNewsArrayList.add("Tech pulls market lower | Bitcoin drops 15% | Target tumbles on earnings");
        mNewsArrayList.add("Ist Bitcoin tot? Das sagt die Szene");
        mNewsArrayList.add("Bitcoin plummets to its lowest value in over a year");
    }

}

package com.udacity.horatio.widgetexample;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class CollectionWidget extends AppWidgetProvider {

    private static final int FEED_UPDATE_TIMER = 6000;
    int[] mAppWidgetIds;
    Context mContext;
    AppWidgetManager mAppWidgetManager;
    RemoteViews mRemoteViews;
    int scrollCount = 0;
    int currentAppWidgetId;
    private ArrayList<String> mStockArrayList;
    private ArrayList<String> mNewsArrayList;
    private int mNewsArrayIndex = 0;
    Handler handler = new Handler();
    //
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            scrollContinous(currentAppWidgetId);
            scrollCount = scrollCount + 1;
        }
    };

    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            mAppWidgetManager = AppWidgetManager.getInstance(mContext);
//            mRemoteViews.setTextViewText(R.id.temp_widget_list,"Bitcoin 1234567890"+tempIncremen);
//            mAppWidgetManager.partiallyUpdateAppWidget(mAppWidgetIds[currentAppWidgetId], mRemoteViews);
            mNewsArrayIndex += 1;
            if(mNewsArrayList != null && mNewsArrayIndex < mNewsArrayList.size()){
                mRemoteViews.setTextViewText(R.id.temp_widget_list, mNewsArrayList.get(mNewsArrayIndex));
            }
            if (mStockArrayList != null && mStockArrayList.size() > 0) {
                String s = mStockArrayList.get(0);
                mStockArrayList.remove(0);
                mStockArrayList.add(s);
                StringBuilder stringBuilder = new StringBuilder();
                for (String string : mStockArrayList) {
                    stringBuilder.append(string);
                }
                Log.i("WidgetLogs", stringBuilder.toString());
                mRemoteViews.setTextViewText(R.id.widget_list_header, Html.fromHtml(stringBuilder.toString()));
            }
            if (mAppWidgetIds != null) {
                mAppWidgetManager.updateAppWidget(mAppWidgetIds[currentAppWidgetId], mRemoteViews);
            }

            handler.postDelayed(runnable1, FEED_UPDATE_TIMER);
        }
    };


    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of themz
        Log.i("WidgetLogs", "Inside onUpdate");
        ComponentName thisWidget = new ComponentName(context,
                WidgetDataProvider.class);
        mAppWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
//-------------------------------------------------------------------------------------------
        mAppWidgetIds = appWidgetIds;
        mContext = context;
        mAppWidgetManager = appWidgetManager;
        //scrollListItem(appWidgetIds, context, appWidgetManager);
    }


    private void scrollListItem(final int[] appWidgetIds, Context context, final AppWidgetManager appWidgetManager) {
        for (int i = 0; i < appWidgetIds.length; ++i) {
            final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
            mRemoteViews = views;
            //views.setRemoteAdapter(R.id.widget_list, new Intent(context, WidgetService.class));
            currentAppWidgetId = i;
            scrollContinous(i);
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
        }
        //handler.postDelayed(runnable,5000);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        mAppWidgetManager = AppWidgetManager.getInstance(context);
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
        Log.i("WidgetLogs", "Inside onReceive");
        //Red E53038
        //Green #72E530
        //Grey 8F958C
        //SBUX 37.28 +1.50 0.55 Apple 104.40 -3.40 0.88 Pepsico 123.23 +4.50 1.24 STIN 37.28 +1.50 0.55 Bharti 104.40 -3.40 0.88 AdityaBirla 123.23 +4.50 1.24
        mStockArrayList = new ArrayList<>();
        mStockArrayList.add("<font color=#000000> SBUX 37.28&emsp;</font> <font color=#72E530>+1.50% 0.55</font>");
        mStockArrayList.add("<font color=#000000> Apple 104.40&emsp;</font> <font color=#E53038>-3.40% 0.88</font>");
        mStockArrayList.add("<font color=#000000> Pepsico 123.23</font> <font color=#72E530>+4.50% 1.24</font>");
        mStockArrayList.add("<font color=#000000> Bharti 104.40&emsp;</font> <font color=#E53038>-3.40% 0.88</font>");
        mStockArrayList.add("<font color=#000000> ABirla 23.23&emsp;</font> <font color=#72E530>+4.50% 1&emsp;&emsp;</font>");
        int maxLength = 0;
        for (String s:mStockArrayList){
            if(s.length()> maxLength){
                maxLength = s.length();
            }
        }
        initStockNewsData();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : mStockArrayList) {
            stringBuilder.append(s);
        }
        ComponentName watchWidget = new ComponentName(context, CollectionWidget.class);
        mRemoteViews.setTextViewText(R.id.widget_list_header, Html.fromHtml(stringBuilder.toString()));
        if (mNewsArrayList != null) {
            mRemoteViews.setTextViewText(R.id.temp_widget_list, mNewsArrayList.get(mNewsArrayIndex));
        }
        mAppWidgetManager.updateAppWidget(watchWidget, mRemoteViews);
        mContext = context;
        handler.postDelayed(runnable1, FEED_UPDATE_TIMER);
    }

    private synchronized void scrollContinous(int i) {
        //mRemoteViews.setScrollPosition(R.id.widget_list, scrollCount);
        Log.i("Widget", "Update Position: " + scrollCount);
        mAppWidgetManager.partiallyUpdateAppWidget(mAppWidgetIds[i], mRemoteViews);
        //handler.postDelayed(runnable, 4000);
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


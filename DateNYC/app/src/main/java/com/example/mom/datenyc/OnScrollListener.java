package com.example.mom.datenyc;

import android.widget.AbsListView;

/**
 * Created by Ra on 4/4/16.
 */
public class OnScrollListener implements AbsListView.OnScrollListener {
    private int bufferItemCount = 20;
    private int currentPage = 0;
    private int itemCount = 0;
    private boolean isLoading = true;

    public OnScrollListener(int bufferItemCount) {
        this.bufferItemCount = bufferItemCount;
    }

    public void loadMore(int page, int totalItemsCount) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Do Nothing
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (totalItemCount < itemCount) {
            this.itemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.isLoading = true; }
        }

        if (isLoading && (totalItemCount > itemCount)) {
            isLoading = false;
            itemCount = totalItemCount;
            currentPage++;
        }

        if (!isLoading && (totalItemCount - visibleItemCount)<=(firstVisibleItem + bufferItemCount)) {
            loadMore(currentPage + 1, totalItemCount);
            isLoading = true;
        }
    }
}

package in.galaxyofandroid.spinerdialog;

import android.widget.AbsListView;

public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private OnLoadMore onLoadMore;

    public EndlessScrollListener(OnLoadMore onLoadMore, int visibleThreshold) {
        this.onLoadMore = onLoadMore;
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
//                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
            onLoadMore.onLoadMore(currentPage + 1);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}
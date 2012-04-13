package org.ebookdroid.core.curl;

import org.ebookdroid.core.Page;
import org.ebookdroid.core.SinglePageDocumentView;
import org.ebookdroid.core.ViewState;
import org.ebookdroid.core.bitmaps.BitmapManager;
import org.ebookdroid.core.bitmaps.BitmapRef;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class SinglePageSlider extends AbstractPageSlider {

    public SinglePageSlider(final SinglePageDocumentView singlePageDocumentView) {
        super(PageAnimationType.SLIDER, singlePageDocumentView);
    }

    /**
     * Draw the foreground
     *
     * @param canvas
     * @param rect
     * @param paint
     */
    @Override
    protected void drawForeground(final Canvas canvas, final ViewState viewState) {
        Page page = view.getBase().getDocumentModel().getPageObject(foreIndex);
        if (page == null) {
            page = view.getBase().getDocumentModel().getCurrentPageObject();
        }
        if (page != null) {
            final BitmapRef fore = getBitmap(canvas, viewState);
            final Canvas tmp = new Canvas(fore.getBitmap());
            page.draw(tmp, viewState, true);

            final Rect src = new Rect((int) mA.x, 0, (int) viewState.viewRect.width(),
                    (int) viewState.viewRect.height());
            final RectF dst = new RectF(0, 0, viewState.viewRect.width() - mA.x, viewState.viewRect.height());
            final Paint paint = new Paint();
            paint.setFilterBitmap(true);
            paint.setAntiAlias(true);
            paint.setDither(true);
            canvas.drawBitmap(fore.getBitmap(), src, dst, paint);
            BitmapManager.release(fore);
        }
    }

    /**
     * Draw the background image.
     *
     * @param canvas
     * @param rect
     * @param paint
     */
    @Override
    protected void drawBackground(final Canvas canvas, final ViewState viewState) {
        final Page page = view.getBase().getDocumentModel().getPageObject(backIndex);
        if (page != null) {
            final BitmapRef back = getBitmap(canvas, viewState);
            final Canvas tmp = new Canvas(back.getBitmap());
            page.draw(tmp, viewState, true);

            final Paint paint = new Paint();
            paint.setFilterBitmap(true);
            paint.setAntiAlias(true);
            paint.setDither(true);
            final Rect src = new Rect(0, 0, (int) mA.x, view.getHeight());
            final RectF dst = new RectF(viewState.viewRect.width() - mA.x, 0, viewState.viewRect.width(),
                    viewState.viewRect.height());
            canvas.drawBitmap(back.getBitmap(), src, dst, paint);
            BitmapManager.release(back);
        }

    }

}
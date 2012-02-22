package org.ebookdroid.core;

import android.graphics.RectF;

public class EventZoomOut extends AbstractEventZoom {

    public EventZoomOut(final AbstractViewController ctrl, final float oldZoom, final float newZoom,
            final boolean committed) {
        super(ctrl, oldZoom, newZoom, committed);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.ebookdroid.core.IEvent#process(org.ebookdroid.core.ViewState, org.ebookdroid.core.PageTreeNode)
     */
    @Override
    public boolean process(final PageTreeNode node) {

        final RectF pageBounds = viewState.getBounds(node.page);

        if (!viewState.isNodeKeptInMemory(node, pageBounds)) {
            node.recycle(bitmapsToRecycle);
            return false;
        }

        if (viewState.isNodeVisible(node, pageBounds) && (!node.holder.hasBitmaps() || committed)) {
            node.decodePageTreeNode(nodesToDecode, viewState);
        }

        return true;
    }
}
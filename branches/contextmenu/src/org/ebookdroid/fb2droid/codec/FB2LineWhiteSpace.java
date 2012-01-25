package org.ebookdroid.fb2droid.codec;

import android.graphics.Canvas;

public class FB2LineWhiteSpace extends AbstractFB2LineElement {

    public FB2LineWhiteSpace(final float width, final int height) {
        super(width, height);
    }

    @Override
    public float render(final Canvas c, final int y, final int x, final float additionalWidth) {
        return width + additionalWidth;
    }
}
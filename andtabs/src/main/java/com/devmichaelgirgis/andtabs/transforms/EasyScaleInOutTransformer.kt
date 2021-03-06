package com.devmichaelgirgis.andtabs.transforms

import android.view.View

class EasyScaleInOutTransformer : ABaseTransformer() {

    override fun onTransform(view: View, position: Float) {
        view.pivotX = (if (position < 0) 0 else view.width).toFloat()
        view.pivotY = view.height / 2f
        val scale = if (position < 0) 1f + position else 1f - position
        view.scaleX = scale
        view.scaleY = scale
    }

}

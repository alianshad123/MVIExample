package com.anshad.mvisampleproject.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class CustomBindingAdapter {
    companion object {

        /**
         * To enable data binding for ImageView with uri
         * only local uri
         */
        @BindingAdapter("imageUri")
        @JvmStatic
        fun setImageUri(imageView: ImageView, uri: String?) {
            Glide.with(imageView.context).load(uri).fitCenter()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .into(imageView)
        }




    }
}
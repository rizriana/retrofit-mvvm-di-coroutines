package id.rrlab.withkoin.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapter {
    companion object {

        @BindingAdapter("android:setImage")
        @JvmStatic
        fun setImage(
            imageView: ImageView,
            imageUrl: String,
        ) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}
package info.yazdan.githubresume.presentation.common.imageloader

import android.widget.ImageView

interface IImageLoader {

    fun bind(imageView: ImageView, url: String?)

}
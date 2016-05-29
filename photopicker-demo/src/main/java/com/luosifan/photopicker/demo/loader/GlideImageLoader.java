/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.luosifan.photopicker.demo.loader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.view.GFImageView;

import java.io.File;

import uk.co.senab.photoview.PhotoView;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/12/1 下午10:28
 * Updated by wzfu on 2016/5/22.
 */
public class GlideImageLoader extends ImageLoader {


    @Override
    public void displayImage(Context mCxt, String path, final GFImageView imageView, final int tagId, int placeholderResId, int errorResId, int width, int height) {

        Glide.with(mCxt)
                .load("file://" + path)
                .placeholder(ContextCompat.getDrawable(mCxt, placeholderResId))
                .error(ContextCompat.getDrawable(mCxt, errorResId))
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                .skipMemoryCache(false)
                .centerCrop()
                .into(new ImageViewTarget<GlideDrawable>(imageView) {
                    @Override
                    protected void setResource(GlideDrawable resource) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void setRequest(Request request) {
                        imageView.setTag(tagId, request);
                    }

                    @Override
                    public Request getRequest() {
                        return (Request) imageView.getTag(tagId);
                    }
                });
    }

    @Override
    public View instantiateItem(Context context, String imagePath) {
        PhotoView photoView = new PhotoView(context);

        Glide.with(context)
                .load(Uri.fromFile(new File(imagePath)))
                .crossFade()
                .into(photoView);

        return photoView;
    }

    @Override
    public void clearMemoryCache() {
    }

    @Override
    public void resumeRequests(Context mCxt, int tagId) {
        Glide.with(mCxt).resumeRequests();
    }

    @Override
    public void pauseRequests(Context mCxt, int tagId) {
        Glide.with(mCxt).pauseRequests();
    }
}

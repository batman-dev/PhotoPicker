package com.luosifan.photopicker.picker;

import com.luosifan.photopicker.ImageLoader;

/**
 * Created by wzfu on 16/5/21.
 */
public class Load {

    private PickerParams params;

    public Load(Class<? extends ImageLoader> imageLoaderClass, PickerTheme theme) {
        this.params = new PickerParams();
        this.params.imageLoaderClass = imageLoaderClass;
        this.params.theme = theme;
    }

    public Load filter(PhotoFilter filter) {
        this.params.filter = filter;
        return this;
    }

    public Load showCamera(boolean showCamera){
        this.params.showCamera = showCamera;
        return this;
    }

    public Load gridColumns(int columns){
        if(columns > 0) {
            this.params.gridColumns = columns;
        }
        return this;
    }

    public Load theme(PickerTheme theme) {
        this.params.theme = theme;
        return this;
    }

    public SingleSelect single() {
        return new SingleSelect(params);
    }

    public MultiSelect multi(){
        return new MultiSelect(params);
    }
}

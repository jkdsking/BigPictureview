package com.jkds.bigpictureview;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vans Z
 * @date 2018/9/26
 */
public class SourceConfig {
    public static List<String> getThumbSourceGroup() {
        List<String> thumbnailImageList = new ArrayList<>();
        thumbnailImageList.add("https://doctor-dev.metamedical.com.cn/api/ih-resources/images/1381457189083807744");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486263782969.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1485055822651.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486194909983.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486194996586.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486195059137.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486173497249.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486173526402.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486173639603.png@233w_160h_20q");
        thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486172566083.png@233w_160h_20q");
        return thumbnailImageList;
    }

    public static List<String> getOriginalSourceGroup() {
        List<String> sourceImageList = new ArrayList<>();
        sourceImageList.add("https://doctor-dev.metamedical.com.cn/api/ih-resources/images/1381457189083807744");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486263782969.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1485055822651.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486194909983.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486194996586.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486195059137.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173497249.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173526402.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173639603.png");
        sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486172566083.png");
        return sourceImageList;
    }

    public static List<String> getMixingSourceGroup() {
        List<String> sourceList = new ArrayList<>();
        sourceList.add("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4");
        sourceList.add("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4");
        sourceList.add("https://stream7.iqilu.com/10339/article/202002/18/2fca1c77730e54c7b500573c2437003f.mp4");
//        sourceList.add("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218025702PSiVKDB5ap.mp4");
//        sourceList.add("https://stream7.iqilu.com/10339/upload_transcode/202002/18/202002181038474liyNnnSzz.mp4");
        return sourceList;
    }

    public static List<String> getMixingSourceGroupGif() {
        List<String> sourceList = new ArrayList<>();
        sourceList.add("http://img3.duitang.com/uploads/item/201605/13/20160513144041_Ze3a4.gif");
        return sourceList;
    }




}

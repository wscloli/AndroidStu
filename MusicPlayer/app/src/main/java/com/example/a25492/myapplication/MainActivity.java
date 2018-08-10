package com.example.a25492.myapplication;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //加载媒体库里的音频
    public ArrayList<MusicMedia> scanAllAudioFiles(){
//生成动态数组，并且转载数据
        ArrayList<MusicMedia>mylist=new ArrayList<MusicMedia>();
       /* 查询媒体数据库
        参数分别为（路径，要查询的列名，条件语句，条件参数，排序）
        视频：MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        图片：MediaStore.Images.Media.EXTERNAL_CONTENT_URI*/
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        //遍历媒体数据库
        if (cursor.moveToFirst()){
        while (!cursor.isAfterLast()){
            //歌曲编号
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            //歌曲标题
            String title=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            //歌曲专辑名：MediaStore.Audio.Media.ALBUM
            String album=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));

            String albumId=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            //歌曲歌手名：MediaStore.Audio.Media.ARTIST
            String artist=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            //歌曲文件路径：MediaStore.Audio.Media.DATA
            String url=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            //歌曲总播放时长：MediaStore.Audio.Media.DURATION
            int duration=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            //歌曲文件大小： MediaStore.Audio.Media.SIZE
            Long size=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

            if(size>1024*800){   //大于800K
                MusicMedia musicMedia=new MusicMedia();
               musicMedia.setId(id);
               musicMedia.setArtist(artist);
               musicMedia.setSize(size);
               musicMedia.setTitle(title);
               musicMedia.setTime(duration);
               musicMedia.setUrl(url);
               musicMedia.setAlbum(album);
               musicMedia.setAlbumId(albumId);

               mylist.add(musicMedia);

            }
            cursor.moveToNext();
            }
        }

        return mylist;
    }
}

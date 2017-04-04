
" 这是我的第一个开源作品，一个很实用的自定义seekbar，希望获得大家的支持，里面可能也会有一些不足望大家指正" 
"还没学会做gif只能先上两张截图"

![run2](https://github.com/525642022/CustomSeekBar/blob/master/MyApplication/Screenshot/run1.png)
![run2](https://github.com/525642022/CustomSeekBar/blob/master/MyApplication/Screenshot/run2.png)  
使用方法
    横向
         <com.example.customseekbar.CustomVerticalSeekBar
        android:id="@+id/CustomVerticalSeekBar1"
        android:layout_width="75dp"
        android:layout_height="match_parent"
        android:paddingTop="20dp"
        android:paddingBottom="20dp"
        android:layout_below="@+id/customHorizontalSeekBar2"
        app:custom_complete_color="@color/customCompleteColor"
        app:custom_count="7"
        app:custom_down_move="true"
        app:custom_height="5dp"
        app:custom_incomplete_color="@color/customInCompleteColor"
        app:custom_max="50"
        app:custom_min="10"
        app:custom_text_color="@color/customTextColor"
        app:custom_text_size="18sp"
        app:custom_text_space="5dp"
        app:custom_text_style="top_text"
        app:custom_thumb_color="@color/customThumbColor"
        app:custom_thumb_radius="6dp"
        />

    纵向
    <com.example.customseekbar.CustomVerticalSeekBar
        android:id="@+id/CustomVerticalSeekBar2"
        android:layout_width="75dp"
        android:layout_height="match_parent"
        android:paddingTop="20dp"
        android:paddingBottom="20dp"
        android:layout_below="@+id/customHorizontalSeekBar2"
        android:layout_toRightOf="@+id/CustomVerticalSeekBar1"
        app:custom_complete_color="@color/customCompleteColor"
        app:custom_count="7"
        app:custom_down_move="false"
        app:custom_height="5dp"
        app:custom_incomplete_color="@color/customInCompleteColor"
        app:custom_max="50"
        app:custom_min="10"
        app:custom_text_color="@color/customTextColor"
        app:custom_text_size="18sp"
        app:custom_text_space="5dp"
        app:custom_text_style="bottom_text"
        app:custom_thumb_color="@color/customThumbColor"
        app:custom_thumb_radius="6dp"
        />

    圆形
     <com.example.customseekbar.CustomCircleSeekBar
        android:id="@+id/ff"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:padding="20dp"
        android:layout_toRightOf="@+id/CustomVerticalSeekBar2"
        android:layout_below="@+id/customHorizontalSeekBar2"
        app:custom_complete_color="@color/customCompleteColor"
        app:custom_down_move="true"
        app:custom_height="5dp"
        app:custom_incomplete_color="@color/customInCompleteColor"
        app:custom_text_color="@color/customTextColor"
        app:custom_text_size="18sp"
        app:custom_thumb_color="@color/customThumbColor"
        app:custom_thumb_radius="6dp"
        />

    在代码中设置
        customSeekbar.getConfigBuilder()
        .mMax(50)
        .mMin(10)
        .mHeight(8)
        .mInCompleteColor(ContextCompat.getColor(this,R.color.yellow))
        .mTextColor(ContextCompat.getColor(this,R.color.green))
        .mTextSize(18)
        .mTextSpace(10)
        .mTextStyle(BaseCustomSeekBar.TextStyle.TOP_TEXT)
        .isDownMove(true)
        .mThumbColor(ContextCompat.getColor(this,R.color.pink))
        .mCount(6)
        .mThumbRadius(9)
        .build()

    自定义属性和注释
     <declare-styleable name="CustomSeekBar">
        <!-- 最小值 默认为 0.0f-->
        <attr name="custom_min" format="float|reference" />
        <!-- 最大值 默认为 100.0f-->
        <attr name="custom_max" format="float|reference" />
        <!-- 线高度 默认为 10dp-->
        <attr name="custom_height" format="dimension|reference" />
        <!-- 拖动条半径 默认为 11dp-->
        <attr name="custom_thumb_radius" format="dimension|reference" />
        <!--间隔数 -->
        <attr name="custom_count" format="integer|reference" />
        <!--已完成颜色 -->
        <attr name="custom_complete_color" format="color|reference" />
        <!--未完成颜色  -->
        <attr name="custom_incomplete_color" format="color|reference" />
        <!--拖动条颜色  -->
        <attr name="custom_thumb_color" format="color|reference" />
        <!--字体颜色  -->
        <attr name="custom_text_color" format="color|reference" />
        <!--是否点击可以移动 默认为 false-->
        <attr name="custom_down_move" format="boolean" />
        <!-- 字体与拖动条距离 默认为 5dp-->
        <attr name="custom_text_space" format="dimension|reference" />
        <!-- 字体大小 默认为 14sp-->
        <attr name="custom_text_size" format="dimension|reference" />
        <!-- 进度 -->
        <attr name="custom_progress" format="float|reference" />
        <!--拖动条字体显示位置, default: bottom_text-->
        <attr name="custom_text_style">
            <enum name="bottom_text" value="0" />
            <enum name="top_text" value="1" />
        </attr>
    </declare-styleable>
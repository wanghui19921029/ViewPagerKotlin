package com.example.wh.viewpagerkotlin

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

class MainActivity : Activity(), ViewPager.OnPageChangeListener {
    private var mPrePos = 0
    private var mView: ArrayList<ImageView> = arrayListOf()
    private var mBitmap: ArrayList<Bitmap> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initPoint()
        viewpager.adapter = MyAdapter(this, mView)
        viewpager.setOnPageChangeListener(this)
    }

    private fun initData() {
        var bitmap: Bitmap? = readBitMap(R.drawable.ic_default1)
        if (bitmap != null) {
            mBitmap.add(bitmap!!)
        }
        bitmap = readBitMap(R.drawable.ic_default2)
        if (bitmap != null) {
            mBitmap.add(bitmap!!)
        }
        bitmap = readBitMap(R.drawable.ic_default3)
        if (bitmap != null) {
            mBitmap.add(bitmap!!)
        }
        bitmap = readBitMap(R.drawable.ic_default4)
        if (bitmap != null) {
            mBitmap.add(bitmap!!)
        }
        bitmap = readBitMap(R.drawable.ic_default5)
        if (bitmap != null) {
            mBitmap.add(bitmap!!)
        }

        for (i in 0..(mBitmap.size - 1)) {
            var iv: ImageView = ImageView(this)
            iv.setScaleType(ImageView.ScaleType.FIT_XY)
            iv.setImageBitmap(mBitmap.get(i))
            mView.add(iv)
        }
    }

    private fun initPoint() {
        var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(18, 18)
        params.leftMargin = 15
        params.rightMargin = 15
        for (i in 0..4) {
            var iv: ImageView = ImageView(this)
            iv.setBackgroundResource(R.drawable.pic_dot_normal)
            iv.layoutParams = params
            ll.addView(iv)
        }
        ll.getChildAt(0).setBackgroundResource(R.drawable.pic_dot_focus)
    }

    private fun readBitMap(resId: Int): Bitmap? {
        var opt: BitmapFactory.Options = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.RGB_565
        opt.inPurgeable = true
        opt.inInputShareable = true
        var inputStream: InputStream? = resources.openRawResource(resId)
        return BitmapFactory.decodeStream(inputStream, null, opt)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        ll.getChildAt(mPrePos).setBackgroundResource(R.drawable.pic_dot_normal)
        ll.getChildAt(position).setBackgroundResource(R.drawable.pic_dot_focus)
        mPrePos = position
    }
}

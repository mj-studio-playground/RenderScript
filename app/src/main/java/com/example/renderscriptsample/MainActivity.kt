package com.example.renderscriptsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.RenderScript
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b2= (imageView1.drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888,false)
        val b1=(imageView2.drawable as BitmapDrawable).bitmap

        imageView2.setImageBitmap(b2)
        imageView1.setImageBitmap(b1)
        convertHalf(applicationContext,b2)
    }

    private fun convertHalf(context : Context, src: Bitmap) {
        //Initialization of components
        val rs = RenderScript.create(context)
        val script = ScriptC_invert(rs)
        val inAllocation = Allocation.createFromBitmap(rs,src)

        //processing
        script.forEach_invert(inAllocation,inAllocation)

        //synchronization with source bitmap.
        inAllocation.syncAll(Allocation.USAGE_SHARED)

        //Free components
        inAllocation.destroy()
        script.destroy()
        rs.destroy()
    }

}

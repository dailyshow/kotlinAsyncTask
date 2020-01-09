package com.cis.kotlinasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { view ->
            val time = System.currentTimeMillis()
            tv1.text = "버튼 클릭 : ${time}"
        }

        val async = AsyncTaskClass()
        async.execute(10, 20)
    }

    inner class AsyncTaskClass : AsyncTask<Int, Long, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            tv2.text = "AsyncTask 실행"
        }

        override fun doInBackground(vararg params: Int?): String {
            var a1 = params[0]!!
            var a2 = params[1]!!

            for (idx in 0..4) {
                SystemClock.sleep(1000)

                a1++
                a2++

//                Log.d("params", "${idx} : ${a1}, ${a2}")

                var time = System.currentTimeMillis()
                publishProgress(time) // onProgressUpdate 를 호출해준다.
            }

            return "수행이 완료 되었습니다." // onPostExecute 메소드로 전달 되어진다.
        }

        override fun onProgressUpdate(vararg values: Long?) {
            super.onProgressUpdate(*values)

            tv2.text = "async : ${values[0]}"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            tv2.text = result
        }
    }
}

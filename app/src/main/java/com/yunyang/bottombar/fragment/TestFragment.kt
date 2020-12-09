package com.yunyang.bottombar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.yunyang.bottombar.R
import com.yunyang.bottombar.utils.ext.args

/**
 * Created by YunYang.
 * Date: 2020/12/9
 * Time: 下午 01:37
 * Des: TestFragment
 */
class TestFragment : Fragment() {

    companion object {
        fun newInstance(name: String) = TestFragment().apply { this.name = name }
    }

    private var name: String by args()

    private lateinit var viewTest: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewTest = inflater.inflate(R.layout.fg_text_name, container, false)
        return viewTest
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fgTextTvName: AppCompatTextView = viewTest.findViewById(R.id.fg_text_tv_name)
        fgTextTvName.text = name
    }

}
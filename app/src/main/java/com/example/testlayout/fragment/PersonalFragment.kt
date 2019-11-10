package com.example.testlayout.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.testlayout.R
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.title_bar.*
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * A simple [Fragment] subclass.
 */
class PersonalFragment : Fragment() {

    companion object {
        const val CONTENT_TEXT = "content_text"
        const val FOLDER_NAME = "folder_name"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val contentText = arguments?.getString(CONTENT_TEXT) ?: "0"
        val folderName = arguments?.getString(FOLDER_NAME)
        content_text.text = contentText
        title_text.text = "$folderName"
        if (title_text.text != "个人空间") {
            back_image.visibility = View.VISIBLE
        } else {
            back_image.visibility = View.GONE
        }
        todo_button.onClick {
            it?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_personalFragment_self, getToDoBundle((contentText.toInt() + 1).toString()))
            }
        }
        back_image.onClick {
            it?.let { it1 ->
                Navigation.findNavController(it1)
                    .popBackStack()
            }
        }
    }

    private fun getToDoBundle(contentText: String): Bundle {
        val bundle = Bundle()
        bundle.putString(CONTENT_TEXT, contentText)
        bundle.putString(FOLDER_NAME, "文件名称")
        return bundle
    }


}

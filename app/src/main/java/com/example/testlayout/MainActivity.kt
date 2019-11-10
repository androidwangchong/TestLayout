package com.example.testlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {


    private var usernamePattern: Pattern? = null
    private val pattern = "^[a-zA-Z0-9.@]{6,12}$"
    private var isUsernamePattern = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 创建 Pattern 对象
        usernamePattern = Pattern.compile(pattern)
        login_button.onClick {
           startActivity(intentFor<TwoActivity>())
        }
    }

    private fun checkUserNameAndPassword(username: String, password: String) {
        isUsernamePattern = usernamePattern?.matcher(username)?.find() ?: false
        if (isUsernamePattern) {
            toast("匹配成功")
        } else {
            toast("匹配不成功")
        }
    }

}

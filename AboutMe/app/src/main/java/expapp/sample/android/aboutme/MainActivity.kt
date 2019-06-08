package expapp.sample.android.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done_button.setOnClickListener { clickHandlerFunction(it) }
        nickname_text.setOnClickListener { updateNickname(it) }
    }

    private fun clickHandlerFunction(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        nickname_text.text = nickname_edit.text
        nickname_edit.visibility = View.GONE
        done_button.visibility = View.GONE
        nickname_text.visibility = View.VISIBLE
    }

    private fun updateNickname (view: View) {
        nickname_edit.visibility = View.VISIBLE
        done_button.visibility = View.VISIBLE
        view.visibility = View.GONE

        nickname_edit.requestFocus()

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(nickname_edit, 0)
    }
}

package expapp.sample.android.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import expapp.sample.android.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Bell Cranel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = this.myName

        binding.apply {
            doneButton.setOnClickListener { clickHandlerFunction(it) }
            nicknameText.setOnClickListener { updateNickname(it) }
        }

    }

    private fun clickHandlerFunction(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE

            invalidateAll()
        }
    }

    private fun updateNickname (view: View) {
        binding.apply {
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nicknameEdit.requestFocus()
        }

        view.visibility = View.GONE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.nicknameEdit, 0)
    }
}

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.doneButton.setOnClickListener { clickHandlerFunction(it) }
        binding.nicknameText.setOnClickListener { updateNickname(it) }

    }

    private fun clickHandlerFunction(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        binding.apply {
            nicknameText.text = nicknameEdit.text
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
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

package com.test.starwarsapp.presentation.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.test.starwarsapp.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

abstract class BaseActivity<T: BaseContract.Presenter<*>> :
        AppCompatActivity(), BaseContract.View {

    @Inject lateinit var presenter: T

    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        hideLoading()
        presenter.destroy()
        super.onDestroy()
    }

    override fun showLoading() {
        hideLoading()

        dialog = ProgressDialog(this).apply {
            this.setMessage(getString(R.string.progress_please_wait))
            this.setCancelable(false)
            this.show()
        }
    }

    override fun hideLoading() {
        dialog = dialog?.run {
            this.dismiss()
            null
        }
    }

    override fun showError(message: String?) {
        hideLoading()
        val error = if (!TextUtils.isEmpty(message)) message else getString(R.string.error_default)
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    protected fun setUpToolbar(enableBackButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)
    }
}

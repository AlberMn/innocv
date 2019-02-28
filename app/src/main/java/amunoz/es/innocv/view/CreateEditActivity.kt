package amunoz.es.innocv.view

import amunoz.es.innocv.InnocvApp
import amunoz.es.innocv.R
import amunoz.es.innocv.model.User
import amunoz.es.innocv.presenter.CreateEditPresenter
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_create_edit.*
import kotlinx.android.synthetic.main.content_create_edit.*
import org.jetbrains.anko.inputMethodManager
import java.util.*
import javax.inject.Inject

class CreateEditActivity : AppCompatActivity(), CreateEditView {

    companion object {
        const val EXTRA_EDIT_OR_CREATE_ACTION = "action"
        const val ACTION_EDIT = "edit"
        const val ID_USER_TO_EDIT = "user_id"
    }

    @Inject
    lateinit var presenter: CreateEditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_edit)
        setSupportActionBar(toolbar)
        inject()
        presenter.viewLoaded(this)
        val action = intent.extras?.get(EXTRA_EDIT_OR_CREATE_ACTION).toString()

        if (intent.hasExtra(ID_USER_TO_EDIT)){

            title = this.resources.getString(R.string.title_actvivity_edit)
            presenter.getUserById(intent.extras!!.getInt(ID_USER_TO_EDIT))

        } else {
            title = this.resources.getString(R.string.title_activity_add)
            editTextName.setOnClickListener { _: View? -> editTextName.text.clear() }
        }

        fab.setOnClickListener {
            val user: User
            if (action == ACTION_EDIT) {
                user = User(intent.extras?.get(ID_USER_TO_EDIT) as Int, editTextName.text.toString(), editTextDate.text.toString())
            } else {
                user = User(Int.MIN_VALUE, editTextName.text.toString(), editTextDate.text.toString())
            }
            presenter.fabClicked(user, intent.extras?.get(EXTRA_EDIT_OR_CREATE_ACTION).toString())
        }
    }

    override fun showUserDetails(user: User) {
        editTextName.setText(user.name)
        editTextDate.setText(user.birthdate)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            editTextDate.setText("""$dayOfMonth - ${monthOfYear + 1} - $year""")
            hideKeyboard()

        }, year, month, day)
        dpd.show()
    }

    override fun navigateToMainActivity() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun inject() {
        (applicationContext as InnocvApp).createCreateEditComponent().inject(this)
    }


    override fun showProgress() {
        progressBar2.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar2.visibility = View.GONE
    }

    override fun showAlertDialog(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alert_dialog_title))
        builder.setMessage(error)
        builder.setCancelable(false)
        builder.setPositiveButton("ok") { dialog, _ ->
            dialog.dismiss()
            navigateToMainActivity()
        }
        builder.show()
    }

    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(editTextName.windowToken, 0)
    }
}
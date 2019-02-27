package amunoz.es.innocv.view

import amunoz.es.innocv.InnocvApp
import amunoz.es.innocv.R
import amunoz.es.innocv.User
import amunoz.es.innocv.presenter.CreateEditPresenter
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_create_edit.*
import kotlinx.android.synthetic.main.content_create_edit.*
import java.util.*
import javax.inject.Inject

class CreateEditActivity : AppCompatActivity(), CreateEditView {


    companion object {
        const val EXTRA_EDIT_OR_CREATE_ACTION = "action"
        const val ACTION_EDIT = "edit"
        const val ACTION_CREATE = "create"
        const val ID_USER_TO_EDIT = "user_id"
        const val USER_NAME_TO_EDIT = "user_name"
        const val USER_DATE_TO_EDIT = "user_date"

    }

    @Inject
    lateinit var presenter: CreateEditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_edit)
        setSupportActionBar(toolbar)
        inject()
        val action = intent.extras?.get(EXTRA_EDIT_OR_CREATE_ACTION).toString()

        if (action == ACTION_EDIT) {

            title = "Edit User"
            editTextName.setText(intent.extras?.get(USER_NAME_TO_EDIT)?.toString())
            editTextDate.setText(intent.extras?.get(USER_DATE_TO_EDIT)?.toString())
        } else {
            title = "Add User"
        }

        fab.setOnClickListener {
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            val user: User
            if (action == ACTION_EDIT) {
                user = User(intent.extras?.get(ID_USER_TO_EDIT) as Int, editTextName.text.toString(), editTextDate.text.toString())
            } else {
                user = User(Int.MIN_VALUE,editTextName.text.toString(), editTextDate.text.toString())
            }
            presenter.fabClicked(user, intent.extras?.get(EXTRA_EDIT_OR_CREATE_ACTION).toString(), this)



        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            editTextDate.setText( """$dayOfMonth - ${monthOfYear + 1} - $year""")

        }, year, month, day)
        dpd.show()
    }

    override fun navigateToMainActivity() {

    }

    private fun inject () {
        (applicationContext as InnocvApp).createCreateEditComponent().inject(this)
    }



}

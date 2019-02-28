package amunoz.es.innocv.view

import amunoz.es.innocv.InnocvApp
import amunoz.es.innocv.R
import amunoz.es.innocv.adapter.SwipeToDeleteCallback
import amunoz.es.innocv.model.User
import amunoz.es.innocv.adapter.UsersAdapter
import amunoz.es.innocv.presenter.MainPresenter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.helper.ItemTouchHelper

import android.view.Menu
import android.view.MenuItem
import android.view.View


import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.content_main.*

import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {


    lateinit var adapter: UsersAdapter
    @Inject
    lateinit var presenter: MainPresenter

    companion object {
        const val ADD_TASK_REQUEST = 1
        const val EDIT_TASK_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Listado"
        inject()
        initAdapter()
        fab.setOnClickListener {
            navigateToCreate()
        }
        presenter.setView(this)
    }

    private fun initAdapter() {
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    private fun navigateToCreate() {
        val intent = Intent(this, CreateEditActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    private fun navigateToModification(user: User) {
        val intent = Intent(this, CreateEditActivity::class.java)
        intent.putExtra(CreateEditActivity.ID_USER_TO_EDIT, 5)
        startActivityForResult(intent, EDIT_TASK_REQUEST)
    }

    private fun inject() {
        (applicationContext as InnocvApp).createMainComponent().inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST || requestCode == EDIT_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            presenter.setView(this)
        }
    }

    override fun showUsers(users: ArrayList<User>?) {
        adapter = UsersAdapter(users!!) { navigateToModification(it) }
        rv_users.adapter = adapter
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter.deleteUser(adapter.usersList[viewHolder.adapterPosition].id!!)
                adapter.removeAt(viewHolder.adapterPosition)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_users)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showAlertDialog(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alert_dialog_title))
        builder.setMessage(error)
        builder.setCancelable(false)
        builder.setPositiveButton("ok") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}

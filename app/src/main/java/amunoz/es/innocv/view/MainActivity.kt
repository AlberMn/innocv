package amunoz.es.innocv.view

import amunoz.es.innocv.InnocvApp
import amunoz.es.innocv.R
import amunoz.es.innocv.SwipeToDeleteCallback
import amunoz.es.innocv.User
import amunoz.es.innocv.adapter.UsersAdapter
import amunoz.es.innocv.presenter.MainPresenter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log

import android.view.Menu
import android.view.MenuItem


import kotlinx.android.synthetic.main.activity_main2.*
import android.view.inputmethod.EditorInfo
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {
    lateinit var adapter : UsersAdapter
    @Inject
    lateinit var presenter: MainPresenter

    override fun showUsers(users: ArrayList<User>?) {
       // (rv_users.adapter as UsersAdapter).addUsers(movies)
        Log.d("main","shou")

        adapter = UsersAdapter(users!!) { navigateToModification(it) }
        rv_users.adapter = adapter


        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //val adapter = rv_users.adapter as SimpleAdapter
                presenter.deleteUser(adapter.usersList[viewHolder.adapterPosition].id)
                adapter.removeAt(viewHolder.adapterPosition)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_users)

        //adapter.addUsers(users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        setSupportActionBar(toolbar)
        inject()
        initAdapter()

        fab.setOnClickListener { view ->
           /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            navigateToCreate()
        }
        presenter.setView(this)
    }

    private fun initAdapter() {

        /*val user : User = User(2, "", "5432132")
        val user2 : User = User(1, "paco", "343424")
        val user3 : User = User(3, "pepe3", "5432132")
        val user4 : User = User(4, "paco3", "343424")
        val user5 : User = User(5, "pepe4", "5432132")
        val user6 : User = User(6, "paco4", "343424")
        var arry : ArrayList<User> = ArrayList<User>()
        arry.add(user)
        arry.add(user2)
        arry.add(user3)
        arry.add(user4)
        arry.add(user5)
        arry.add(user6)*/







        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.setHasFixedSize(true)


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem : MenuItem = menu.findItem(R.id.action_search)
        val searchView : SearchView = menuItem.actionView as SearchView
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

    private fun navigateToCreate(){
        startActivity<CreateEditActivity>(Pair(CreateEditActivity.EXTRA_EDIT_OR_CREATE_ACTION, "create"))

    }
    private fun navigateToModification (user: User) {
        startActivity<CreateEditActivity>(
                Pair(CreateEditActivity.EXTRA_EDIT_OR_CREATE_ACTION, "edit"),
                Pair(CreateEditActivity.ID_USER_TO_EDIT, user.id))
    }

    private fun inject () {
        (applicationContext as InnocvApp).createMainComponent().inject(this)
    }

}

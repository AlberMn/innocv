package amunoz.es.innocv.adapter


import amunoz.es.innocv.R
import amunoz.es.innocv.model.User
import amunoz.es.innocv.extensions.inflate
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import kotlinx.android.synthetic.main.user_item.view.*

class UsersAdapter (var usersList: ArrayList<User>, private val listener: (User) -> Unit)
    : RecyclerView.Adapter<UsersAdapter.ViewHolder>() , Filterable{

    var userFullList = ArrayList(usersList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.user_item))
    }

    override fun getItemCount(): Int {
       return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(usersList[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind (user: User, listener: (User) -> Unit) {
            itemView.text_view_name.text = user.name
            itemView.text_view_id.text = user.id.toString()
            itemView.text_view_date.text = user.birthdate.toString()
            itemView.setOnClickListener { listener (user)}

        }

    }

    fun addUsers(users: ArrayList<User>?) {
        if (users != null) {
            this.usersList = users
            this.userFullList = users
            notifyDataSetChanged()
        }
    }


    override fun getFilter(): Filter {
       return usersFilter
    }

     val usersFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val filteredList= ArrayList<User>()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(userFullList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }


                for (item in userFullList) {
                    if (item.name.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = Filter.FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            usersList.clear()
            usersList.addAll(results.values as ArrayList<User>)
            notifyDataSetChanged()
        }
    }

    fun removeAt(position: Int) {
        usersList.removeAt(position)
        userFullList.removeAt(position)
        notifyItemRemoved(position)
    }



}